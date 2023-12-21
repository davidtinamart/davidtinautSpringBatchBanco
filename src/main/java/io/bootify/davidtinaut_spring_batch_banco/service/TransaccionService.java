package io.bootify.davidtinaut_spring_batch_banco.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.bootify.davidtinaut_spring_batch_banco.domain.Transaccion;
import io.bootify.davidtinaut_spring_batch_banco.repos.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
    public class TransaccionService {
        @Autowired
        private TransaccionRepository transaccionRepository;

        public void cargarDesdeCSV(String rutaArchivo) throws IOException {
            try (Reader reader = new FileReader(rutaArchivo)) {
                CsvToBean<Transaccion> csvToBean = new CsvToBeanBuilder<Transaccion>(reader)
                        .withType(Transaccion.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Transaccion> transacciones = csvToBean.parse();

                transaccionRepository.saveAll(transacciones);
            }
        }

        public void guardarEnCSV(String rutaArchivo, Transaccion transaccion) throws IOException {
            try (Writer writer = new FileWriter(rutaArchivo, true)) {
                StatefulBeanToCsv<Transaccion> beanToCsv = new StatefulBeanToCsvBuilder<Transaccion>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();

                beanToCsv.write(transaccion);
            } catch (CsvRequiredFieldEmptyException e) {
                throw new RuntimeException(e);
            } catch (CsvDataTypeMismatchException e) {
                throw new RuntimeException(e);
            }
        }
    }

