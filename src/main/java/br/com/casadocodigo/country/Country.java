package br.com.casadocodigo.country;

import br.com.casadocodigo.commons.contracts.CDCEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Country implements CDCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Deprecated
    protected Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Map<String, Object> toMap() {
        return Map.of("id", id, "name", name);
    }
}
