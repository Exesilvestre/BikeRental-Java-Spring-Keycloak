package com.example.Alquileres.Services.DTOS;

import com.example.Alquileres.Entities.Alquiler;
import com.example.Alquileres.Entities.Tarifa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDTORead {

    private Long id;
    private String idCliente;
    private Integer estado;
    private Long estacionRetiroObj;
    private Long estacionDevolucionObj;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private Double monto;
    private Tarifa tarifa;
    public AlquilerDTORead(Alquiler alq){
        this.id = alq.getId();
        this.idCliente = alq.getIdCliente();
        this.estado = alq.getEstado();
        this.estacionRetiroObj = alq.getEstacionRetiroObj();
        this.estacionDevolucionObj = alq.getEstacionDevolucionObj();
        this.fechaHoraRetiro = alq.getFechaHoraRetiro();
        this.fechaHoraDevolucion = alq.getFechaHoraDevolucion();
        this.monto = alq.getMonto();
        this.tarifa = alq.getTarifa();
    }
}
