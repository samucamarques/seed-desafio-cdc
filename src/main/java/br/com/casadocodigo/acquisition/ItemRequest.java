package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.Book;
import br.com.casadocodigo.commons.validation.ExistsById;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

//Intrinsic cognitive load: 1
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class ItemRequest {

    @NotNull
    @Positive
    private Integer ammount;

    @NotNull
    @ExistsById(entityClass = Book.class)
    private Long bookId;
}
