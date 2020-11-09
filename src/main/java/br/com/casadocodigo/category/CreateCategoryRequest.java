package br.com.casadocodigo.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;

import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public class CreateCategoryRequest {
    @NotEmpty
    private final String name;

    public Category toDomain(Predicate<String> uniqueNamePredicate) {
        if (uniqueNamePredicate.test(name)) {
            throw new DuplicateKeyException("Name in use for another category");
        }

        return new Category(this.name);
    }
}
