package com.example.Alquileres.Services.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTOWrite {

    @Builder.Default
    private int tipoTarifa = 1;
    @Builder.Default
    private String definicion = "S";
    private Integer diaSemana;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private double montoFijoAlquiler;
    private double montoMinutoFraccion;
    private double montoKm;
    private double montoHora;
}
