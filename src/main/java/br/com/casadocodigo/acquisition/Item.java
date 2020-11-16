package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.Book;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    @Deprecated
    protected Item() {
    }

    public Item(@NotNull int amount, @NotNull Book book) {
        this.amount = amount;
        this.book = book;
    }

    public BigDecimal totalPrice() {
        return book.priceOf(amount);
    }
}
