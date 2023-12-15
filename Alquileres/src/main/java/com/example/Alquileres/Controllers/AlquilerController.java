package com.example.Alquileres.Controllers;

import com.example.Alquileres.Services.AlquilerService;
import com.example.Alquileres.Services.DTOS.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;


@RestController
@RequestMapping("/api/alquiler")
public class AlquilerController {

    AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping
    public ResponseEntity<AlquilerDTORead> createTarifa(@RequestBody AlquilerDTOPost alq){
        AlquilerDTORead alquilerCreada = alquilerService.create(alq);
        return ResponseEntity.ok(alquilerCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlquiler(
            @PathVariable Long id,
            @Valid @RequestBody AlquilerFinRequestDto alq,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try{
            AlquilerDTOFinAlquiler respuesta = alquilerService.update(id, alq);
            return  ResponseEntity.ok(respuesta);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlquilerDTORead>> buscarAlquileresPorEstaciones(
            @RequestParam Long estacionRetiroId,
            @RequestParam Long estacionDevId) {
        List<AlquilerDTORead> alquileres = alquilerService.buscarPorEstaciones(estacionRetiroId, estacionDevId);
        return new ResponseEntity<>(alquileres, HttpStatus.OK);
    }


}
