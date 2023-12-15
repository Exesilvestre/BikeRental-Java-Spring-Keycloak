package com.example.Alquileres.Services.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CotizacionRequest {
    @JsonProperty("moneda_destino")
    private String monedaDestino;
    private Double importe;

    // Constructor sin argumentos, getters y setters

    public CotizacionRequest(String moneda_destino, Double importe) {
        this.monedaDestino = moneda_destino;
        this.importe = importe;
    }
}

