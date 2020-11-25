package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.coupon.Coupon;
import br.com.casadocodigo.coupon.CouponRepository;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CouponExpirationValidator implements Validator {
    private CouponRepository couponRepository;

    public CouponExpirationValidator(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AcquisitionRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        final AcquisitionRequest acquisition = (AcquisitionRequest) target;
        if (StringUtils.hasLength(acquisition.getCouponCode())) {
            final Coupon coupon = couponRepository.findByCode(acquisition.getCouponCode());
            if (coupon == null) {
                errors.rejectValue("couponCode", null, "Coupon not found");
                return;
            }
            if (!coupon.isValid()) {
                errors.rejectValue("couponCode", null, "Coupon has expired");
            }
        }
    }
}
