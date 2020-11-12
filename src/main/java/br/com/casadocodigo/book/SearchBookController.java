package br.com.casadocodigo.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Intrinsic cognitive load: 4
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class SearchBookController {

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

    @GetMapping("/{id}/detail")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> detail(@PathVariable("id") Long id) {
        return bookRepository
                .findDetailById(id)
                .map(BookDetailProjection::toMap) /* 1 */
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); /* 1 */
    }
}
