package io.bootify.davidtinaut_spring_batch_banco.repos;

import io.bootify.davidtinaut_spring_batch_banco.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
        // Puedes agregar consultas específicas si es necesario
    }


