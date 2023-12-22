package io.bootify.davidtinaut_spring_batch_banco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TransaccionesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fecha;
    private Double cantidad;
    private String tipotrans;
    private String cuentaorigen;
    private String cuentadestino;


    public TransaccionesDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipotrans() {
        return tipotrans;
    }

    public void setTipotrans(String tipotrans) {
        this.tipotrans = tipotrans;
    }

    public String getCuentaorigen() {
        return cuentaorigen;
    }

    public void setCuentaorigen(String cuentaorigen) {
        this.cuentaorigen = cuentaorigen;
    }

    public String getCuentadestino() {
        return cuentadestino;
    }

    public void setCuentadestino(String cuentadestino) {
        this.cuentadestino = cuentadestino;
    }

    @Override
    public String toString() {
        return "TransaccionesDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", tipotrans='" + tipotrans + '\'' +
                ", cuentaorigen='" + cuentaorigen + '\'' +
                ", cuentadestino='" + cuentadestino + '\'' +
                '}';
    }
}

