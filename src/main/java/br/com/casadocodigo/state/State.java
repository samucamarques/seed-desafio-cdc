package br.com.casadocodigo.state;

import br.com.casadocodigo.commons.contracts.CDCEntity;
import br.com.casadocodigo.country.Country;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
public class State implements CDCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    @Deprecated
    protected State() {

    }

    public State(String name, @NotNull Country country) {
        Assert.hasLength(name, "there is no state without name");
        Assert.notNull(country, "there is no state without country");

        this.name = name;
        this.country = country;
    }

    public Map<String, Object> toMap() {
        return Map.of("id", id, "name", name);
    }

    public boolean hasId(Long stateId) {
        Assert.notNull(id, "the entity id can't be null here");
        Assert.notNull(stateId, "stateId can't be null here");

        return id.equals(stateId);
    }
}
