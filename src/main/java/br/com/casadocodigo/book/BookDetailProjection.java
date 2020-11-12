package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorDetailProjection;
import br.com.casadocodigo.category.CategoryDetailProjection;

import java.math.BigDecimal;

public interface BookDetailProjection {

    String getTitle();

    String getBriefing();

    String getSummary();

    BigDecimal getPrice();

    Integer getPages();

    String getIsbn();

    CategoryDetailProjection getCategory();

    AuthorDetailProjection getAuthor();
}
