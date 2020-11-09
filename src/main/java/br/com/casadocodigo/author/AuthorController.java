package br.com.casadocodigo.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @PostMapping("/author/create")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public AuthorResponse create(
            @RequestBody @Valid CreateAuthorRequest request) {

        final Author author = request.toDomain(authorRepository::existsByMailAddress);
        authorRepository.save(author);
        
        return AuthorResponse.of(author);
    }
}
