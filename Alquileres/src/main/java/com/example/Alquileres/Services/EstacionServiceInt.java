package com.example.Alquileres.Services;

import com.example.Alquileres.Services.DTOS.EstacionDTORead;

public interface EstacionServiceInt {
    Boolean validateIdEstacion(Long id);
    Double getDistanciaEntreEstaciones(Long idEstacionOrigen, Long idEstacionDestino);
}
