package br.com.casadocodigo.author;

import java.util.Map;

public interface AuthorDetailProjection {

    String getName();

    String getDescription();

    default Map<String, Object> toMap() {
        return Map.of(
                "name", getName(),
                "description", getDescription());
    }
}
