package br.com.casadocodigo.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxPaymentRepository extends JpaRepository<FluxPayment, Long> {
}
