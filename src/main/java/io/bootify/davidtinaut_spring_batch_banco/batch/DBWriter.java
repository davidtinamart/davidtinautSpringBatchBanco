package io.bootify.davidtinaut_spring_batch_banco.batch;

import io.bootify.davidtinaut_spring_batch_banco.repos.TransaccionesRepository;
import io.bootify.davidtinaut_spring_batch_banco.model.TransaccionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemWriter;


import java.util.List;

@Component
public class DBWriter implements ItemWriter<TransaccionesDTO> {

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    @Override
    public void write(List<? extends TransaccionesDTO> transacciones) throws Exception {

        System.out.println("Data Saved for Transacciones: " + transacciones);
        transaccionesRepository.saveAll(transacciones);
    }
}
