package br.com.casadocodigo.commons.validation;

import br.com.casadocodigo.country.CountryRepository;
import br.com.casadocodigo.state.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class CountryOwnershipValidator
        implements ConstraintValidator<CountryOwnership, Object> {

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    private String countryField;
    private String stateField;

    @Override
    public void initialize(CountryOwnership annotation) {
        countryField = annotation.countryField();
        stateField = annotation.stateField();

        Assert.hasLength(countryField, "countryField can't be null");
        Assert.hasLength(stateField, "stateField can't be null");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final BeanWrapper bean = new BeanWrapperImpl(value);

        final Long countryId = (Long) bean.getPropertyValue(countryField);
        final Long stateId = (Long) bean.getPropertyValue(stateField);

        return stateRepository
                .findByCountryId(countryId)
                .stream()
                .anyMatch(state -> state.hasId(stateId));
    }
}
