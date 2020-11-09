package br.com.casadocodigo.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

//Intrinsic cognitive load: 4
@RestController
@RequiredArgsConstructor
public class CategoryController {

    //1
    private final CategoryRepository categoryRepository;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Map<String, Object> create(
            //1
            @RequestBody @Valid CreateCategoryRequest request) {

        //2
        final Category category =
                categoryRepository.save(
                        request.toDomain(categoryRepository::existsByName));


        return category.toMap();
    }
}
