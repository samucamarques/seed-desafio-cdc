package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorDetailProjection;
import br.com.casadocodigo.category.CategoryDetailProjection;

import java.math.BigDecimal;
import java.util.Map;

public interface BookDetailProjection {

    String getTitle();

    String getBriefing();

    String getSummary();

    BigDecimal getPrice();

    Integer getPages();

    String getIsbn();

    CategoryDetailProjection getCategory();

    AuthorDetailProjection getAuthor();

    default Map<String, Object> toMap() {
        return Map.of(
                "title", getTitle(),
                "briefing", getBriefing(),
                "summary", getSummary(),
                "price", getPrice(),
                "pages", getPages(),
                "isbn", getIsbn(),
                "author", getAuthor().toMap(),
                "category", getCategory().toMap());
    }
}
