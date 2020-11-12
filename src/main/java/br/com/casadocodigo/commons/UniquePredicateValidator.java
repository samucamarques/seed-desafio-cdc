package br.com.casadocodigo.commons;

import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.book.BookRepository;
import br.com.casadocodigo.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

@RequiredArgsConstructor
public class UniquePredicateValidator implements ConstraintValidator<UniquePredicate, String> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    private Map<String, java.util.function.Predicate<String>> predicates;
    private String predicate;

    @Override
    public void initialize(UniquePredicate constraintAnnotation) {
        predicates =
                Map.of(
                        "existsByTitle", bookRepository::existsByTitle,
                        "existsByIsbn", bookRepository::existsByIsbn,
                        "existsByMailAddress", authorRepository::existsByMailAddress,
                        "existsByName", categoryRepository::existsByName);

        predicate = String.format("existsBy%s", StringUtils.capitalize(constraintAnnotation.property()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return predicates.containsKey(predicate) && !predicates.get(predicate).test(value);
    }
}
