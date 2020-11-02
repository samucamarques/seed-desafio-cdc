package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Long id;
    private String nome;
    private String createdAt;

    public static AuthorResponse of(Author author) {
        return new AuthorResponse(author.getId(), author.getName(), author.getCreatedAt().toString());
    }
}