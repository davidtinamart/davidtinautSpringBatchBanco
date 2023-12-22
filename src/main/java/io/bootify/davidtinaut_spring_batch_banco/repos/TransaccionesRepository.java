package io.bootify.davidtinaut_spring_batch_banco.repos;

import io.bootify.davidtinaut_spring_batch_banco.model.TransaccionesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionesRepository extends JpaRepository<TransaccionesDTO,String> {

}
