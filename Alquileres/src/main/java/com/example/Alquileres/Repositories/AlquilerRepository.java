package com.example.Alquileres.Repositories;

import com.example.Alquileres.Entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findAllByEstacionRetiroObjAndAndEstacionDevolucionObj(Long retiroId, Long devolucionId);
}
