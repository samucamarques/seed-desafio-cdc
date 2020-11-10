package br.com.casadocodigo.author;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Map;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotEmpty
    private String name;

    @NonNull
    @NotEmpty
    @Email
    private String mailAddress;

    @NonNull
    @NotEmpty
    @Length(max = 400)
    private String description;

    private Instant createdAt = Instant.now();

    public Map<String, Object> toMap() {
        return Map.of("id", id, "name", name, "createdAt", createdAt);
    }
}
