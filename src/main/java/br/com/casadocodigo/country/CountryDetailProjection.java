package br.com.casadocodigo.country;

import java.util.Map;

public interface CountryDetailProjection {

    String getName();

    default Map<String, Object> toMap() {
        return Map.of("name", getName());
    }
}
