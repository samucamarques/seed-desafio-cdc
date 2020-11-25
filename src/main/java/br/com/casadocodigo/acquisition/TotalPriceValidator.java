package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.BookRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TotalPriceValidator implements Validator {
    private final BookRepository bookRepository;

    public TotalPriceValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AcquisitionRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        final AcquisitionRequest acquisitionRequest = (AcquisitionRequest) target;

        final List<Long> bookIds =
                acquisitionRequest.getItems()
                        .stream()
                        .map(ItemRequest::getBookId)
                        .collect(Collectors.toList());

        final BigDecimal internalTotalPrice =
                bookRepository.findAllById(bookIds)
                        .stream()
                        .map(book ->
                                acquisitionRequest.getItems()
                                        .stream()
                                        .filter(item -> book.hasId(item.getBookId()))
                                        .map(item -> book.priceOf(item.getAmmount()))
                                        .findFirst()
                                        .orElse(BigDecimal.ZERO))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (acquisitionRequest.getTotalPrice().compareTo(internalTotalPrice) != 0) {
            errors.rejectValue("totalPrice", null, "has wrong value");
        }
    }
}
