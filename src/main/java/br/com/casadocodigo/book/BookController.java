package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Stream;

//Intrinsic cognitive load: 4
@RestController
@RequiredArgsConstructor
public class BookController {

    //1
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Map<String, Object> create(
            /* 1 */ @RequestBody @Valid CreateBookRequest request) {

        //2
        final Book book =
                bookRepository.save(
                        request.toDomain(
                                bookRepository::existsByTitle,
                                bookRepository::existsByIsbn,
                                categoryRepository::findById,
                                authorRepository::findById));

        return book.toMap();
    }
}
