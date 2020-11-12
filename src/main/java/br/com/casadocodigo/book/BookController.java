package br.com.casadocodigo.book;

import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Intrinsic cognitive load: 9
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
    @Transactional
    public Map<String, Object> create(
            /* 1 */ @RequestBody @Valid CreateBookRequest request) {

        //1
        final Book book =
                bookRepository.save(
                        request.toDomain(
                                /* 1 */ categoryRepository::findById,
                                /* 1 */ authorRepository::findById));

        return book.toMap();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> list() {
        // quebrou a coesao do meu controller =(
        return bookRepository
                .findAll()
                .stream()
                .map(Book::toMap)
                .collect(Collectors.toList());
    }
}
