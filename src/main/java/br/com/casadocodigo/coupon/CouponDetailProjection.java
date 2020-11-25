package br.com.casadocodigo.coupon;

import java.math.BigDecimal;

public interface CouponDetailProjection {

    public String getCode();

    public BigDecimal getDiscount();
}
