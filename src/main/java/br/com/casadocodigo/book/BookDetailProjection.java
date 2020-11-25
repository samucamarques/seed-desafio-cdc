package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorDetailProjection;
import br.com.casadocodigo.category.CategoryDetailProjection;

import java.math.BigDecimal;
import java.util.HashMap;
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
        return new HashMap<>() {{
            put("title", getTitle());
            put("briefing", getBriefing());
            put("summary", getSummary());
            put("price", getPrice());
            put("pages", getPages());
            put("isbn", getIsbn());
            put("author", getAuthor() == null ? null : getAuthor().toMap());
            put("category", getCategory() == null ? null : getCategory().toMap());
        }};
    }
}
