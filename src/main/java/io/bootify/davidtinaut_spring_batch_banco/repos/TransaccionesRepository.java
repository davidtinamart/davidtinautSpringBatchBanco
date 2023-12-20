package io.bootify.davidtinaut_spring_batch_banco.repos;

import io.bootify.davidtinaut_spring_batch_banco.domain.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransaccionesRepository extends JpaRepository<Transacciones, Long> {
}
