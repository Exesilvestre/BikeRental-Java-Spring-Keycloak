package com.example.Alquileres.Services;

import com.example.Alquileres.Services.DTOS.CotizacionRequest;
import com.example.Alquileres.Services.DTOS.CotizacionResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CotizacionMonedaService implements CotizacionMOnedaInt {
    RestTemplate restTemplate;

    @Override
    public Double cotizarMoneda(Double monto, String monedaDestino) {
        // Hacer la llamada al servicio de cotización de moneda
        // (Ajusta la URL según la correcta y según la respuesta del servicio)

        // Crear el cuerpo de la solicitud
        CotizacionRequest cotizacionRequest = new CotizacionRequest(monedaDestino, monto);
        // Configurar los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CotizacionRequest> requestEntity = new HttpEntity<>(cotizacionRequest, headers);

        // Realizar la solicitud
        ResponseEntity<CotizacionResponse> response = restTemplate.postForEntity(
                "http://34.82.105.125:8080/convertir",
                cotizacionRequest,
                CotizacionResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getImporte();
        }

        return null;
    }
}







