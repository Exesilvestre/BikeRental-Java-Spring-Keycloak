package com.example.Alquileres.Controllers;


import com.example.Alquileres.Services.DTOS.TarifaDTORead;
import com.example.Alquileres.Services.DTOS.TarifaDTOWrite;
import com.example.Alquileres.Services.TarifaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifa")
public class TarifaController {

    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @GetMapping
    public ResponseEntity<List<TarifaDTORead>> getAll(){

        List<TarifaDTORead> tarifas =  tarifaService.findAll();
        return tarifas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(tarifas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TarifaDTORead> getTarifaById(@PathVariable Long id){
        Optional<TarifaDTORead> tar = tarifaService.findById(id);
        return tar.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(tar.get());
    }

    @PostMapping
    public ResponseEntity<TarifaDTORead> createTarifa(@RequestBody TarifaDTOWrite tar){
        TarifaDTORead tarifaCreada = tarifaService.create(tar);
        return ResponseEntity.ok(tarifaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaDTORead> updateTarifa(
            @PathVariable Long id,
            @RequestBody TarifaDTOWrite tar
    ) {
        return new ResponseEntity<>(tarifaService.update(id, tar), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTarifa(@PathVariable Long id) {
        return tarifaService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

