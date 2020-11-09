package br.com.casadocodigo.book;

import br.com.casadocodigo.commons.AnyFutureDate;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

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
    private final String title;

    @NotEmpty
    @Length(min = 1, max = 500)
    private final String briefing;

    private final String summary;

    @NotNull
    @DecimalMin(value = "20.0")
    private final BigDecimal price;

    @NotNull
    @Min(100)
    private final Integer pages;

    @NotEmpty
    private final String isbn;

    @AnyFutureDate
    private final Instant releaseAt;

    public Book toDomain(
            Predicate<String> uniqueTitle, Predicate<String> uniqueIsbn) {

        if (uniqueTitle.test(title)) {
            throw new DuplicateKeyException("Title is in use for another book");
        }
        if (uniqueIsbn.test(isbn)) {
            throw new DuplicateKeyException("ISBN is in use for another book");
        }

        final Book book =
                new Book(
                        title,
                        briefing,
                        price,
                        pages,
                        isbn,
                        releaseAt,
                        null,
                        null);

        if (StringUtils.hasLength(summary)) {
            book.setSummary(summary);
        }

        return book;
    }
}
