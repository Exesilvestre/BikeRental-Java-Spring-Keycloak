package com.example.Alquileres.Services.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDTOPost {
    private String idCliente;
    private Long estacionRetiroObj;
}
