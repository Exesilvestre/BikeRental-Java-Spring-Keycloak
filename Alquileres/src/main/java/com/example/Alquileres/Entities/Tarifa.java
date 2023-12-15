package com.example.Alquileres.Entities;


import com.example.Alquileres.Services.DTOS.TarifaDTOWrite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TARIFAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_TARIFA")
    private int tipoTarifa;

    @Column(name = "DEFINICION")
    private String definicion;

    @Column(name = "DIA_SEMANA")
    private Integer diaSemana;

    @Column(name = "DIA_MES")
    private Integer diaMes;

    @Column(name = "MES")
    private Integer mes;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private double montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private double montoKm;

    @Column(name = "MONTO_HORA")
    private double montoHora;

    public Tarifa(TarifaDTOWrite tar){
        this.tipoTarifa = tar.getTipoTarifa();
        this.definicion = tar.getDefinicion();
        this.diaSemana = tar.getDiaSemana();
        this.diaMes = tar.getDiaMes();
        this.mes = tar.getMes();
        this.anio = tar.getAnio();
        this.montoFijoAlquiler = tar.getMontoFijoAlquiler();
        this.montoMinutoFraccion = tar.getMontoMinutoFraccion();
        this.montoKm = tar.getMontoKm();
        this.montoHora = tar.getMontoHora();
    }


    public Tarifa update(Long id, TarifaDTOWrite tarifaDTO) {
        this.id = id;
        this.tipoTarifa = tarifaDTO.getTipoTarifa();
        this.definicion = tarifaDTO.getDefinicion();
        this.diaSemana = tarifaDTO.getDiaSemana();
        this.diaMes = tarifaDTO.getDiaMes();
        this.mes = tarifaDTO.getMes();
        this.anio = tarifaDTO.getAnio();
        this.montoFijoAlquiler = tarifaDTO.getMontoFijoAlquiler();
        this.montoMinutoFraccion = tarifaDTO.getMontoMinutoFraccion();
        this.montoKm = tarifaDTO.getMontoKm();
        this.montoHora = tarifaDTO.getMontoHora();
        return this;
    }
}
