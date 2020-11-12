package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.AnyFutureDate;
import br.com.casadocodigo.commons.UniquePredicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.function.LongFunction;

//Intrinsic cognitive load: 4
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

        final BindingResult errors = new BeanPropertyBindingResult(this, this.getClass().getSimpleName());
        final Optional<Category> possibleCategory = findCategoryById.apply(categoryId);
        if (possibleCategory.isEmpty()) {
            //1
            errors.rejectValue("categoryId", null, "not found");
        }
        final Optional<Author> possibleAuthor = findAuthorById.apply(authorId);
        if (possibleAuthor.isEmpty()) {
            //1
            errors.rejectValue("authorId", null, "not found");
        }
        if (errors.hasErrors()) {
            //1
            throw new MethodArgumentNotValidException(null, errors);
        }

        return Book.builder()
                .title(title)
                .briefing(briefing)
                .price(price)
                .pages(pages)
                .isbn(isbn)
                .releaseAt(releaseAt)
                .category(possibleCategory.get())
                .author(possibleAuthor.get())
                .summary(summary)
                .build();
    }

}
