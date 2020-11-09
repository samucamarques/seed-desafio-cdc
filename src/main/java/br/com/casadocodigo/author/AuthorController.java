package br.com.casadocodigo.author;

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
public class AuthorController {

    //1
    private final AuthorRepository authorRepository;

    @PostMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Map<String, Object> create(
            //1
            @RequestBody @Valid CreateAuthorRequest request) {

        //2
        final Author author = request.toDomain(authorRepository::existsByMailAddress);
        authorRepository.save(author);
        
        return author.toMap();
    }
}
