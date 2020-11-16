package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

//Intrinsic cognitive load: 7
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    //1
    private final BookRepository bookRepository;
    //1
    private final CategoryRepository categoryRepository;
    //1
    private final AuthorRepository authorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> create(
            /* 1 */ @RequestBody @Valid CreateBookRequest request) {

        //1
        final Book book =
                bookRepository.save(
                        request.toDomain(
                                /* 1 */ categoryRepository::getOne,
                                /* 1 */ authorRepository::getOne));

        return book.toMap();
    }
}
