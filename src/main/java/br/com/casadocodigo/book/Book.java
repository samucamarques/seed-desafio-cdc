package br.com.casadocodigo.book;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.category.Category;
import br.com.casadocodigo.commons.AnyFutureDate;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotEmpty
    private String title;

    @NonNull
    @NotEmpty
    @Length(min = 1, max = 500)
    private String briefing;

    private String summary;

    @NonNull
    @NotNull
    @DecimalMin(value = "20.0", inclusive = true)
    private BigDecimal price;

    @NonNull
    @NotNull
    @Min(100)
    private Integer pages;

    @NonNull
    @NotEmpty
    private String isbn;

    @NonNull
    @NotNull
    @AnyFutureDate
    private Instant releaseAt;

    @NonNull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @NonNull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Author author;

}
