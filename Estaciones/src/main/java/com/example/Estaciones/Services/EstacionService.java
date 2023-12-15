package com.example.Estaciones.Services;

import com.example.Estaciones.Entities.Estacion;
import com.example.Estaciones.EstacionesApplication;
import com.example.Estaciones.Repository.EstacionRepository;
import com.example.Estaciones.Services.DTOs.EstacionDTORead;
import com.example.Estaciones.Services.DTOs.EstacionDTOWrite;
import com.example.Estaciones.Services.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {

    private EstacionRepository estacionRepository;

    public EstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public List<EstacionDTORead> findAll(){
        return estacionRepository.findAll().stream()
                .map(estacion -> new EstacionDTORead(estacion)).toList();
    }

    public Optional<EstacionDTORead> findById(Long id){
        Optional<Estacion> existingEstacion = estacionRepository.findById(id);
        return existingEstacion.isEmpty()
                ? Optional.empty()
                : Optional.of(new EstacionDTORead(existingEstacion.get()));
    }

    public EstacionDTORead create(EstacionDTOWrite est){
        Estacion estacion = new Estacion(est);
        Estacion estacionGuardada = estacionRepository.save(estacion);
        return new EstacionDTORead(estacionGuardada);
    }


    public EstacionDTORead update(Long pid, EstacionDTOWrite estacionDTO) {
        Estacion est = estacionRepository.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Estacion [%d] inexistente", pid))
        );
        return new EstacionDTORead(estacionRepository.save(est.update(pid, estacionDTO)));
    }

    public boolean deleteById(Long id) {
        if (!estacionRepository.existsById(id)) {
            return false;
        }
        estacionRepository.deleteById(id);
        return true;
    }

    public Estacion encontrarEstacionMasCercana(Double latitud, Double longitud) {
        List<Estacion> todasLasEstaciones = estacionRepository.findAll();

        // Calcular la distancia euclidiana y encontrar la estación más cercana
        Estacion estacionMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : todasLasEstaciones) {
            double distancia = calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }

        return estacionMasCercana;
    }

    private double calcularDistancia(Double latitud1, Double longitud1, Double latitud2, Double longitud2) {
        // Fórmula de la distancia euclidiana
        double distancia = Math.sqrt(Math.pow(latitud2 - latitud1, 2) + Math.pow(longitud2 - longitud1, 2))* 110000;
        return distancia;
    }

    public double distanciaEntreEstaciones(Long estacionOrigenId, Long estacionLlegadaId){
        Estacion estacionOrigen = estacionRepository.findById(estacionOrigenId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Estacion [%d] inexistente", estacionOrigenId))
        );
        Estacion estacionLlegada = estacionRepository.findById(estacionLlegadaId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Estacion [%d] inexistente", estacionLlegadaId))
        );
        double distancia = calcularDistancia(estacionOrigen.getLatitud(), estacionOrigen.getLongitud(),estacionLlegada.getLatitud(), estacionLlegada.getLongitud());
        distancia = (double) distancia/1000;
        return distancia;
    }




}
