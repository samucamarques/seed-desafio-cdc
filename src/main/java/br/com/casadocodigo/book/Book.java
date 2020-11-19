package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.contracts.CDCEntity;
import br.com.casadocodigo.commons.validation.AnyFutureDate;
import lombok.Builder;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Entity
@Builder
public class Book implements CDCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    @Length(min = 1, max = 500)
    private String briefing;

    @Setter
    private String summary;

    @NotNull
    @DecimalMin(value = "20.0")
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pages;

    @NotEmpty
    private String isbn;

    @NotNull
    @AnyFutureDate
    private Instant releaseAt;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Author author;

    @Deprecated
    protected Book() {

    }

    private Book(@NotEmpty String title,
                 @NotEmpty @Length(min = 1, max = 500) String briefing,
                 @NotNull @DecimalMin(value = "20.0") BigDecimal price,
                 @NotNull @Min(100) Integer pages,
                 @NotEmpty String isbn,
                 @NotNull Instant releaseAt,
                 @NotNull Category category,
                 @NotNull Author author) {

        Assert.hasLength(title, "there is no book without title");
        Assert.hasLength(briefing, "there is no book without briefing");
        Assert.state(briefing.length() < 500, "max length of a book's briefing is 500");
        Assert.notNull(price, "there is no book without price");
        Assert.state(price.compareTo(BigDecimal.valueOf(20)) >= 0, "min price of a book is 20.0");
        Assert.notNull(pages, "there is no book without pages");
        Assert.state(pages >= 100, "min number of pages is 100");
        Assert.hasLength(isbn, "there is no book without isbn");
        Assert.notNull(releaseAt, "there is no book without release date");
        Assert.notNull(category, "there is no book without category");
        Assert.notNull(author, "there is no book without author");

        this.title = title;
        this.briefing = briefing;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.releaseAt = releaseAt;
        this.category = category;
        this.author = author;
    }

    public Map<String, Object> toMap() {
        return Map.of("id", id, "title", title);
    }

    public boolean hasId(Long bookId) {
        Assert.notNull(id, "the entity id can't be null here");
        Assert.notNull(bookId, "bookId can't be null here");

        return id.equals(bookId);
    }

    public BigDecimal priceOf(int amount) {
        return price.multiply(BigDecimal.valueOf(amount));
    }

    public static class BookBuilder {
        public Book build() {
            final Book book = new Book(title, briefing, price, pages, isbn, releaseAt, category, author);
            book.setSummary(summary);
            return book;
        }
    }
}
