package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.AnyFutureDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
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

    @AnyFutureDate(fieldName = "releaseAt")
    private final Instant releaseAt;

    @NotNull
    @Min(1)
    private final Long categoryId;

    @NotNull
    @Min(1)
    private final Long authorId;

    public Book toDomain(
            Predicate<String> uniqueTitle,
            Predicate<String> uniqueIsbn,
            Function<Long, Optional<Category>> findCategoryById,
            Function<Long, Optional<Author>> findAuthorById) {

        if (uniqueTitle.test(title)) {
            throw new IllegalArgumentException("Title is in use for another book");
        }
        if (uniqueIsbn.test(isbn)) {
            throw new IllegalArgumentException("ISBN is in use for another book");
        }

        final Category category =
                findCategoryById.apply(categoryId)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        final Author author =
                findAuthorById.apply(authorId)
                        .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        final Book book =
                new Book(
                        title,
                        briefing,
                        price,
                        pages,
                        isbn,
                        releaseAt,
                        category,
                        author);

        if (StringUtils.hasLength(summary)) {
            book.setSummary(summary);
        }

        return book;
    }

}
