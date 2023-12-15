package com.example.Estaciones.Controller;

import com.example.Estaciones.Entities.Estacion;
import com.example.Estaciones.Services.DTOs.EstacionDTORead;
import com.example.Estaciones.Services.DTOs.EstacionDTOWrite;
import com.example.Estaciones.Services.EstacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estacion")
public class EstacionController {

    private final EstacionService estacionService;

    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<List<EstacionDTORead>> getAll(){

        List<EstacionDTORead> estaciones =  estacionService.findAll();
        return estaciones.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(estaciones);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstacionDTORead> getEstacionById(@PathVariable Long id){
        Optional<EstacionDTORead> est = estacionService.findById(id);
        return est.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(est.get());
    }

    @PostMapping
    public ResponseEntity<EstacionDTORead> createEstacion(@RequestBody EstacionDTOWrite est){
        EstacionDTORead estacionCreada = estacionService.create(est);
        return ResponseEntity.ok(estacionCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstacionDTORead> updateEstacion(
            @PathVariable Long id,
            @RequestBody EstacionDTOWrite est
    ) {
        return new ResponseEntity<>(estacionService.update(id, est), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEstacion(@PathVariable Long id) {
        return estacionService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/mas-cercana")
    public ResponseEntity<Estacion> encontrarEstacionMasCercana(
            @RequestParam("latitud") Double latitud,
            @RequestParam("longitud") Double longitud) {
        Estacion estacionMasCercana = estacionService.encontrarEstacionMasCercana(latitud, longitud);
        return ResponseEntity.ok(estacionMasCercana);
    }


    @GetMapping("/entreEstaciones/{estacionOrigenId}/{estacionLlegadaId}")
    public double distanciaEntreEstaciones(
            @PathVariable Long estacionOrigenId,
            @PathVariable Long estacionLlegadaId
    ) {
        return estacionService.distanciaEntreEstaciones(estacionOrigenId, estacionLlegadaId);
    }

}
