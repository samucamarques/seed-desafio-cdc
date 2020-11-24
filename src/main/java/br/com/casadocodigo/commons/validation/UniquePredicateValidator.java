package br.com.casadocodigo.commons.validation;

import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.book.BookRepository;
import br.com.casadocodigo.category.CategoryRepository;
import br.com.casadocodigo.country.CountryRepository;
import br.com.casadocodigo.coupon.CouponRepository;
import br.com.casadocodigo.state.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class UniquePredicateValidator implements ConstraintValidator<UniquePredicate, String> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CouponRepository couponRepository;

    private Map<String, Map<String, Predicate<String>>> predicates;
    private String category;
    private String predicate;

    private Map<String, Predicate<String>> getCouponPredicatesMap() {
        return Map.of(
                "existsByCode", couponRepository::existsByCode);
    }

    private Map<String, Predicate<String>> getBookPredicatesMap() {
        return Map.of(
                "existsByTitle", bookRepository::existsByTitle,
                "existsByIsbn", bookRepository::existsByIsbn);
    }

    private Map<String, Predicate<String>> getAuthorPredicatesMap() {
        return Map.of("existsByMailAddress", authorRepository::existsByMailAddress);
    }

    private Map<String, Predicate<String>> getCategoryPredicatesMap() {
        return Map.of("existsByName", categoryRepository::existsByName);
    }

    private Map<String, Predicate<String>> getStatePredicatesMap() {
        return Map.of("existsByName", stateRepository::existsByName);
    }

    private Map<String, Predicate<String>> getCountryPredicatesMap() {
        return Map.of("existsByName", countryRepository::existsByName);
    }

    @Override
    public void initialize(UniquePredicate constraintAnnotation) {
        predicates =
                Map.of("book", getBookPredicatesMap(),
                        "author", getAuthorPredicatesMap(),
                        "category", getCategoryPredicatesMap(),
                        "country", getCountryPredicatesMap(),
                        "state", getStatePredicatesMap(),
                        "coupon", getCouponPredicatesMap());

        category = constraintAnnotation.category();
        predicate = String.format("existsBy%s", StringUtils.capitalize(constraintAnnotation.property()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (predicates.containsKey(category)
                && predicates.get(category).containsKey(predicate)) {

            return !predicates.get(category).get(predicate).test(value);
        }

        return Boolean.FALSE;
    }
}
