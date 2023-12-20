package io.bootify.davidtinaut_spring_batch_banco.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransaccionesDTO {

    private Long id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Double cantidad;

    @NotNull
    @Size(max = 255)
    private String tipotrans;

    @NotNull
    @Size(max = 255)
    private String cuentaorigen;

    @Size(max = 255)
    private String cuentadestino;

}
