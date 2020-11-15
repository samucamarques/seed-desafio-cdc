package br.com.casadocodigo.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    boolean existsByName(String name);

    List<State> findByCountryId(Long countryId);
}
