package com.example.Alquileres.Services.DTOS;

import com.example.Alquileres.Entities.Alquiler;
import com.example.Alquileres.Entities.Tarifa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlquilerDTOFinAlquiler {
    private Long id;
    private String idCliente;
    private Integer estado;
    private Long estacionRetiroObj;
    private Long estacionDevolucionObj;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private Double monto;
    private String moneda;
    private Tarifa tarifa;
    public AlquilerDTOFinAlquiler(Alquiler alq, Double montoConvertida, String moneda){
        this.id = alq.getId();
        this.idCliente = alq.getIdCliente();
        this.estado = alq.getEstado();
        this.estacionRetiroObj = alq.getEstacionRetiroObj();
        this.estacionDevolucionObj = alq.getEstacionDevolucionObj();
        this.fechaHoraRetiro = alq.getFechaHoraRetiro();
        this.fechaHoraDevolucion = alq.getFechaHoraDevolucion();
        this.monto = montoConvertida;
        this.moneda= moneda;
        this.tarifa = alq.getTarifa();
    }
}
