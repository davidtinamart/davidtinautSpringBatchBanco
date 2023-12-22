package io.bootify.davidtinaut_spring_batch_banco.batch;

import io.bootify.davidtinaut_spring_batch_banco.model.TransaccionesDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<TransaccionesDTO,TransaccionesDTO> {

    @Override
    public TransaccionesDTO process(TransaccionesDTO transaccion) throws Exception {
        System.out.println("Insertando informacion de transaccion: " + transaccion);
        return transaccion;
    }
}