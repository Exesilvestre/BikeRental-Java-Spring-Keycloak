package com.example.Alquileres.Services;

import com.example.Alquileres.Entities.Tarifa;
import com.example.Alquileres.Repositories.TarifaRepository;
import com.example.Alquileres.Services.DTOS.TarifaDTORead;
import com.example.Alquileres.Services.DTOS.TarifaDTOWrite;
import com.example.Alquileres.Services.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaService {

    private TarifaRepository tarifaRepository;

    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public List<TarifaDTORead> findAll(){
        return tarifaRepository.findAll().stream()
                .map(tarifa -> new TarifaDTORead(tarifa)).toList();
    }

    public Optional<TarifaDTORead> findById(Long id){
        Optional<Tarifa> existingTarifa = tarifaRepository.findById(id);
        return existingTarifa.isEmpty()
                ? Optional.empty()
                : Optional.of(new TarifaDTORead(existingTarifa.get()));
    }

    public TarifaDTORead create(TarifaDTOWrite tar){
        Tarifa tarifa = new Tarifa(tar);
        Tarifa tarifaGuardada = tarifaRepository.save(tarifa);
        return new TarifaDTORead(tarifaGuardada);
    }


    public TarifaDTORead update(Long id, TarifaDTOWrite tarifaDTO) {
        Tarifa tar = tarifaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Tarifa [%d] inexistente", id))
        );

        return new TarifaDTORead(tarifaRepository.save(tar.update(id, tarifaDTO)));
    }

    public boolean deleteById(Long id) {
        if (!tarifaRepository.existsById(id)) {
            return false;
        }
        tarifaRepository.deleteById(id);
        return true;
    }



}
