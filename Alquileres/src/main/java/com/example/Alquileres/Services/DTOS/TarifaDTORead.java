package com.example.Alquileres.Services.DTOS;


import com.example.Alquileres.Entities.Tarifa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTORead {

    private Long id;
    private int tipoTarifa;
    private String definicion;
    private Integer diaSemana;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private double montoFijoAlquiler;
    private double montoMinutoFraccion;
    private double montoKm;
    private double montoHora;

    public TarifaDTORead(Tarifa tar){
        this.id = tar.getId();
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
}
