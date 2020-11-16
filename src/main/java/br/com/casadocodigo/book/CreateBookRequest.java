package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.validation.AnyFutureDate;
import br.com.casadocodigo.commons.validation.ExistsById;
import br.com.casadocodigo.commons.validation.UniquePredicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.LongFunction;

//Intrinsic cognitive load: 7
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateBookRequest {

    @NotEmpty
    @UniquePredicate(category = "book", property = "title")
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
    @UniquePredicate(category = "book", property = "isbn")
    private final String isbn;

    @AnyFutureDate(fieldName = "releaseAt")
    private final Instant releaseAt;

    @NotNull
    @ExistsById(entityClass = Category.class)
    private final Long categoryId;

    @NotNull
    @ExistsById(entityClass = Author.class)
    private final Long authorId;

    //1
    public Book toDomain(
            LongFunction<Category> getCategoryReference,
            LongFunction<Author> getAuthorReference) {

        //1
        final Category category = getCategoryReference.apply(categoryId);//1
        if (category == null) {
            //1
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //1
        final Author author = getAuthorReference.apply(authorId); //1
        if (author == null) {
            //1
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

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
