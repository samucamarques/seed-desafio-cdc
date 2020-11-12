package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.AnyFutureDate;
import br.com.casadocodigo.commons.UniquePredicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.function.LongFunction;

//Intrinsic cognitive load: 5
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateBookRequest {
    @NotEmpty
    @UniquePredicate(property = "title")
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
    @UniquePredicate(property = "isbn")
    private final String isbn;

    @AnyFutureDate(fieldName = "releaseAt")
    private final Instant releaseAt;

    @NotNull
    @Min(1)
    private final Long categoryId;

    @NotNull
    @Min(1)
    private final Long authorId;

    @SneakyThrows
    //1
    public Book toDomain(
            LongFunction<Optional<Category>> findCategoryById,
            LongFunction<Optional<Author>> findAuthorById) {

        //1
        final Category category =
                findCategoryById.apply(categoryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //1

        //1
        final Author author =
                findAuthorById.apply(authorId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //1

        return Book.builder()
                .title(title)
                .briefing(briefing)
                .price(price)
                .pages(pages)
                .isbn(isbn)
                .releaseAt(releaseAt)
                .category(category)
                .author(author)
                .summary(summary)
                .build();
    }

}
