package com.example.Estaciones.Entities;

import com.example.Estaciones.Services.DTOs.EstacionDTOWrite;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESTACIONES")
@Data
@NoArgsConstructor
public class Estacion {
   @Column(name = "ID")
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idEstacion;
   @Column(name = "NOMBRE")
   private String nombre;
   @Column(name = "FECHA_HORA_CREACION")
   private LocalDateTime fechaHoraCreacion;
   @Column(name = "LATITUD")
   private Double latitud;
   @Column(name = "LONGITUD")
   private Double longitud;

   public Estacion(String nombre,LocalDateTime fechaHoraCreacion, Double latitud, Double longitud) {
      this.nombre = nombre;
      this.fechaHoraCreacion = fechaHoraCreacion;
      this.latitud = latitud;
      this.longitud = longitud;
   }

   public Estacion (EstacionDTOWrite est){
      this.nombre = est.getNombre();
      this.fechaHoraCreacion = est.getFechaHoraCreacion();
      this.latitud = est.getLatitud();
      this.longitud = est.getLongitud();
   }

   public Estacion update(Long id, EstacionDTOWrite estacionDTOWrite){
      this.idEstacion = id;
      this.nombre = estacionDTOWrite.getNombre();
      this.fechaHoraCreacion = estacionDTOWrite.getFechaHoraCreacion();
      this.latitud = estacionDTOWrite.getLatitud();
      this.longitud = estacionDTOWrite.getLongitud();
      return this;
   }
}
