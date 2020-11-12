package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.AnyFutureDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;
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
import java.util.function.Function;
import java.util.function.Predicate;

//Intrinsic cognitive load: 7
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

    @SneakyThrows
    //1
    public Book toDomain(
            Predicate<String> uniqueTitle,
            Predicate<String> uniqueIsbn,
            Function<Long, Optional<Category>> findCategoryById,
            Function<Long, Optional<Author>> findAuthorById) {

        final BindingResult errors = new BeanPropertyBindingResult(this, this.getClass().getSimpleName());
        if (uniqueTitle.test(title)) {
            //1
            errors.rejectValue("title", null, "is in use for another book");
        }
        if (uniqueIsbn.test(isbn)) {
            //1
            errors.rejectValue("isbn", null, "is in use for another book");
        }
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

        final Book book =
                new Book(
                        title,
                        briefing,
                        price,
                        pages,
                        isbn,
                        releaseAt,
                        possibleCategory.get(),
                        possibleAuthor.get());

        if (StringUtils.hasLength(summary)) {
            //1
            book.setSummary(summary);
        }

        return book;
    }

}
