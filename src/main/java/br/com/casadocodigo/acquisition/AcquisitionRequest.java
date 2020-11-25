package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.Book;
import br.com.casadocodigo.commons.validation.CountryOwnership;
import br.com.casadocodigo.commons.validation.DocId;
import br.com.casadocodigo.commons.validation.ExistsById;
import br.com.casadocodigo.country.Country;
import br.com.casadocodigo.coupon.Coupon;
import br.com.casadocodigo.state.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Collectors;

//Intrinsic cognitive load: 8
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
@CountryOwnership(countryField = "countryId", stateField = "stateId")
public class AcquisitionRequest {

    @NotEmpty
    @Email
    private final String mailAccount;

    @NotEmpty
    private final String firstName;

    @NotEmpty
    private final String lastName;

    @NotEmpty
    @DocId(cpf = @CPF, cnpj = @CNPJ)
    private final String docId;

    @NotEmpty
    private final String address;

    @NotEmpty
    private final String addressComplement;

    @NotEmpty
    private final String city;

    @NotNull
    @ExistsById(entityClass = Country.class)
    private final Long countryId;

    @ExistsById(entityClass = State.class)
    private final Long stateId;

    @NotEmpty
    private final String phoneNumber;

    @NotEmpty
    private final String zipCode;

    @NotNull
    @Positive
    private final BigDecimal totalPrice;

    @Valid
    @NotEmpty
    private final List<ItemRequest> items;

    private final String couponCode;

    //1
    public Acquisition toDomain(
            Function<List<Long>, List<Book>> findBookByIdIn,
            Function<String, Coupon> findCouponByCode,
            LongFunction<Country> findCountryById,
            LongFunction<State> findStateById) {
        //1
        final List<Book> booksOnShoppingCart = findBookByIdIn.apply(getBookIds());
        //1
        final Coupon coupon = findCouponByCode.apply(couponCode);

        final Country country = findCountryById.apply(getCountryId());
        State state = null;
        if (getStateId() != null) {
            state = findStateById.apply(getStateId());
        }

        return Acquisition.builder()
                .mailAccount(mailAccount)
                .firstName(firstName)
                .lastName(lastName)
                .docId(docId)
                .address(address)
                .addressComplement(addressComplement)
                .city(city)
                .country(country)
                .state(state)
                .phoneNumber(phoneNumber)
                .zipCode(zipCode)
                .totalPrice(totalPrice)
                .items(items.stream()
                        //1
                        .map(item -> new Item(item.getAmmount(), findMyBook(booksOnShoppingCart, item.getBookId())))
                        //1
                        .collect(Collectors.toList()))
                .coupon(coupon)
                .build();
    }

    private Book findMyBook(List<Book> books, Long bookId) {
        //1
        return books.stream().filter(book -> book.hasId(bookId)).findFirst().orElse(null);
    }

    private List<Long> getBookIds() {
        //1
        return items.stream()
                .map(ItemRequest::getBookId)
                .collect(Collectors.toList()); //1
    }
}
