package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.BookDetailProjection;

import java.util.Map;

public interface ItemDetailProjection {

    int getAmount();

    BookDetailProjection getBook();

    default Map<String, Object> toMap() {
        return Map.of("amount", getAmount(), "book", getBook().toMap());
    }
}
