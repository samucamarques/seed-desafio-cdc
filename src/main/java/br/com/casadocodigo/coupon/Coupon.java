package br.com.casadocodigo.coupon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private BigDecimal discount;

    private Instant expirationDate;

    @Deprecated
    public Coupon() {
    }

    public Coupon(String code, BigDecimal discount, Instant expirationDate) {
        this.code = code;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public Map<String, Object> toMap() {
        return Map.of("id", id, "code", code, "discount", discount, "expires", expirationDate);
    }
}
