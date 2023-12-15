package com.example.Alquileres.Repositories;

import com.example.Alquileres.Entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
}
