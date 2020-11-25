package br.com.casadocodigo.acquisition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {

    Optional<AcquisitionDetailProjection> findDetailById(Long id);
}
