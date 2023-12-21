package io.bootify.davidtinaut_spring_batch_banco.controller;

import io.bootify.davidtinaut_spring_batch_banco.domain.Transaccion;
import io.bootify.davidtinaut_spring_batch_banco.service.TransaccionService;
import io.bootify.davidtinaut_spring_batch_banco.repos.TransaccionRepository; // Importa el repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @Autowired  // Autowire el repositorio
    private TransaccionRepository transaccionRepository;

    @PostMapping("/crear")
    public ResponseEntity<String> crearTransaccion(@RequestBody Transaccion transaccion) {
        transaccionRepository.save(transaccion);

        try {
            transaccionService.guardarEnCSV("src/main/resources/transacciones_4.csv", transaccion);
            return ResponseEntity.ok("Transacción creada y guardada en el archivo CSV.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar en el archivo CSV.");
        }
    }
}
