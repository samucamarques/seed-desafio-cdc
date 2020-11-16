package br.com.casadocodigo.commons.validation;

import br.com.casadocodigo.commons.contracts.CDCEntity;
import br.com.casadocodigo.commons.util.SpringContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ExistsByIdValidator implements ConstraintValidator<ExistsById, Long> {

    private final SpringContext springContext;

    private JpaRepository<? extends CDCEntity, Long> repository;

    public void initialize(ExistsById constraint) {
        repository = springContext.getRepositoryBean(constraint.entityClass());
    }

    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || repository.existsById(value);
    }
}
