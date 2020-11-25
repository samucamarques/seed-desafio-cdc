package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.country.CountryDetailProjection;
import br.com.casadocodigo.coupon.CouponDetailProjection;
import br.com.casadocodigo.state.StateDetailProjection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface AcquisitionDetailProjection {

    String getMailAccount();

    String getFirstName();

    String getLastName();

    String getDocId();

    String getAddress();

    String getAddressComplement();

    String getCity();

    CountryDetailProjection getCountry();

    StateDetailProjection getState();

    String getPhoneNumber();

    String getZipCode();

    BigDecimal getTotalPrice();

    List<ItemDetailProjection> getItems();

    CouponDetailProjection getCoupon();

    default Map<String, Object> toMap() {
        return new HashMap<>() {{
            put("mailAccount", getMailAccount());
            put("firstName", getFirstName());
            put("lastName", getLastName());
            put("docId", getDocId());
            put("address", getAddress());
            put("addressComplement", getAddressComplement());
            put("city", getCity());
            put("phoneNumber", getPhoneNumber());
            put("zipCode", getZipCode());
            put("addressComplement", getAddressComplement());
            put("country", getCountry().toMap());
            put("items", getItems().stream().map(ItemDetailProjection::toMap).collect(Collectors.toList()));
            put("totalPrice", getTotalPrice());
            put("hasCoupon", getCoupon() != null);
            put("finalPrice", finalPrice());
        }};
    }

    default BigDecimal finalPrice() {
        if (getCoupon() == null) {
            return null;
        }
        final BigDecimal discount = getCoupon().getDiscount().divide(BigDecimal.valueOf(100));
        return getTotalPrice().subtract(getTotalPrice().multiply(discount));
    }
}
