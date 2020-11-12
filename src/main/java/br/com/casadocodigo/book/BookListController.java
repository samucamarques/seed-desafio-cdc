package br.com.casadocodigo.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Intrinsic cognitive load: 2
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookListController {

    //1
    private final BookRepository bookRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> list() {
        return bookRepository
                .findAll()
                .stream()
                .map(Book::toMap) /* 1 */
                .collect(Collectors.toList());
    }
}
