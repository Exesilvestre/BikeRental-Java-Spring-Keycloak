package com.example.Estaciones.Repository;

import com.example.Estaciones.Entities.Estacion;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long>{
}
