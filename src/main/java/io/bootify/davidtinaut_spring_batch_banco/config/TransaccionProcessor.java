package io.bootify.davidtinaut_spring_batch_banco.config;

import io.bootify.davidtinaut_spring_batch_banco.domain.Transacciones;
import org.springframework.batch.item.ItemProcessor;

public class TransaccionProcessor implements ItemProcessor<Transacciones, Transacciones> {

    @Override
    public Transacciones process(Transacciones item) throws Exception {
        return item;
    }
}
