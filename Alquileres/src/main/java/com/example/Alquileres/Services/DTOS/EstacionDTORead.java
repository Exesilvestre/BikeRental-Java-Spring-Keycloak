package com.example.Alquileres.Services.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDTORead {
    private Long id;
    private String nombre;
    private Double latitud;
    private Double longitud;

}

