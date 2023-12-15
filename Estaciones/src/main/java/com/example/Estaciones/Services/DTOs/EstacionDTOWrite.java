package com.example.Estaciones.Services.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDTOWrite {
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private Double latitud;
    private Double longitud;
}
