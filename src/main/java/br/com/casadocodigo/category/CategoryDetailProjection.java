package br.com.casadocodigo.category;

import java.util.Map;

public interface CategoryDetailProjection {

    String getName();

    default Map<String, Object> toMap() {
        return Map.of("name", getName());
    }
}
