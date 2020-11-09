package br.com.casadocodigo.book;

import br.com.casadocodigo.commons.AnyFutureDate;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DuplicateKeyException;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Predicate;

@AllArgsConstructor
public class CreateBookRequest {
    @NotEmpty
    private String title;

    @NotEmpty
    @Length(min = 1, max = 500)
    private String briefing;

    private String summary;

    @NotNull
    @DecimalMin(value = "20.0", inclusive = true)
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pages;

    @NotEmpty
    private String isbn;

    @AnyFutureDate
    private Instant releaseAt;

    public Book toDomain(
            Predicate<String> uniqueTitle, Predicate<String> uniqueIsbn) {

        if (uniqueTitle.test(title)) {
            throw new DuplicateKeyException("Title is in use for another book");
        }
        if (uniqueIsbn.test(isbn)) {
            throw new DuplicateKeyException("ISBN is in use for another book");
        }

        return new Book(
                this.title,
                this.briefing,
                this.price,
                pages,
                isbn,
                releaseAt,
                null,
                null);
    }
}
