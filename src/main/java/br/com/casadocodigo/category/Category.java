package br.com.casadocodigo.category;

import br.com.casadocodigo.commons.contracts.CDCEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Category implements CDCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    private String name;

    public Map<String, Object> toMap() {
        return Map.of("id", id, "name", name);
    }
}
