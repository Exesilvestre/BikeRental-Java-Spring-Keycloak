package com.example.Estaciones.Services.DTOs;

import com.example.Estaciones.Entities.Estacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDTORead {
    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private Double latitud;
    private Double longitud;

    public EstacionDTORead(Estacion est){
        this.id = est.getIdEstacion();
        this.nombre = est.getNombre();
        this.fechaHoraCreacion = est.getFechaHoraCreacion();
        this.latitud = est.getLatitud();
        this.longitud = est.getLongitud();
    }
}

