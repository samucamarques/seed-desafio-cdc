package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.commons.contracts.CDCEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
public class Acquisition implements CDCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotEmpty
    @Email
    private String mailAccount;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String docId;

    @NotEmpty
    private String address;

    @NotEmpty
    private String addressComplement;

    @NotEmpty
    private String city;

    @NotNull
    private Long countryId;

    @Setter
    private Long stateId;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String zipCode;

    @NotNull
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @Deprecated
    protected Acquisition() {
    }

    private Acquisition(
            @NotEmpty @Email String mailAccount,
            @NotEmpty String firstName,
            @NotEmpty String lastName,
            @NotEmpty String docId,
            @NotEmpty String address,
            @NotEmpty String addressComplement,
            @NotEmpty String city,
            @NotNull Long countryId,
            @NotEmpty String phoneNumber,
            @NotEmpty String zipCode,
            @NotNull BigDecimal totalPrice,
            @NotEmpty List<Item> items) {

        Assert.hasLength(mailAccount, "there is no acquisition without mailAccount");
        Assert.hasLength(firstName, "there is no acquisition without firstName");
        Assert.hasLength(lastName, "there is no acquisition without lastName");
        Assert.hasLength(docId, "there is no acquisition without docId");
        Assert.hasLength(address, "there is no acquisition without address");
        Assert.hasLength(addressComplement, "there is no acquisition without addressComplement");
        Assert.hasLength(city, "there is no acquisition without city");
        Assert.notNull(countryId, "there is no acquisition without countryId");
        Assert.hasLength(phoneNumber, "there is no acquisition without phoneNumber");
        Assert.hasLength(zipCode, "there is no acquisition without zipCode");
        Assert.notNull(totalPrice, "there is no acquisition without totalPrice");
        Assert.state(items != null && !items.isEmpty(), "there is no acquisition without items");

        this.mailAccount = mailAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.docId = docId;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.countryId = countryId;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public boolean checkTotalPrice() {
        final BigDecimal internalTotalPrice =
                items.stream()
                        .map(Item::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice.compareTo(internalTotalPrice) == 0;
    }

    public static class AcquisitionBuilder {
        public Acquisition build() {
            final Acquisition fluxPayment =
                    new Acquisition(
                            mailAccount,
                            firstName,
                            lastName,
                            docId,
                            address,
                            addressComplement,
                            city,
                            countryId,
                            phoneNumber,
                            zipCode,
                            totalPrice,
                            items);

            fluxPayment.setStateId(stateId);
            return fluxPayment;
        }
    }

}
