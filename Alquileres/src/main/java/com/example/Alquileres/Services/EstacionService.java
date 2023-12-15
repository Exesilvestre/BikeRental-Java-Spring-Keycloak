package com.example.Alquileres.Services;


import com.example.Alquileres.Services.DTOS.EstacionDTORead;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionService implements EstacionServiceInt {

    RestTemplate restTemplate;

    @Override
    public Boolean validateIdEstacion(Long id) {
        val response = restTemplate.getForEntity("http://localhost:8083/api/estacion/{id}", EstacionDTORead.class, id);

        //validar que el status code sea 200 y que el body no sea null
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return true;
        }
        return false;
    }

    @Override
    public Double getDistanciaEntreEstaciones(Long idEstacionOrigen, Long idEstacionDestino) {
        //http://localhost:8081/api/v1/estaciones/distanciaEntreEstaciones?idEstacion1={idEstacionOrigen}&idEstacion2={idEstacionDestino}
        try {
            val response = restTemplate.getForEntity("http://localhost:8083/api/estacion" +
                            "/entreEstaciones/{estacionOrigenId}/{estacionLlegadaId}"
                    , Double.class, idEstacionOrigen,idEstacionDestino);

            if (response.getStatusCode().is2xxSuccessful()  && response.getBody() != null){
                return response.getBody();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
