package com.example.Alquileres.Entities;

import com.example.Alquileres.Entities.Tarifa;
import com.example.Alquileres.Services.DTOS.AlquilerDTOPost;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@Table(name = "ALQUILERES")
@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_CLIENTE", length = 50)
    private String idCliente;

    @Column(name = "ESTADO")
    private Integer estado;

    @Column(name = "ESTACION_RETIRO")
    private Long estacionRetiroObj;

    @Column(name = "ESTACION_DEVOLUCION")
    private Long estacionDevolucionObj;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA", referencedColumnName = "ID")
    private Tarifa tarifa;

    @Transient
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Alquiler(AlquilerDTOPost alq){
        this.idCliente = alq.getIdCliente();
        this.estado = 1;
        this.estacionRetiroObj = alq.getEstacionRetiroObj();
        this.estacionDevolucionObj = null;
        this.fechaHoraRetiro = LocalDateTime.now();
        this.monto = null;
        this.tarifa = null;
    }

    public Alquiler update(Long id, Alquiler alq){
        this.id = id;
        this.idCliente = alq.getIdCliente();
        this.estado = 2;
        this.estacionRetiroObj = alq.getEstacionRetiroObj();
        this.estacionDevolucionObj = alq.getEstacionDevolucionObj();
        this.fechaHoraRetiro = alq.getFechaHoraRetiro();
        this.fechaHoraDevolucion = alq.getFechaHoraDevolucion();
        this.monto = alq.getMonto();
        this.tarifa = alq.getTarifa();

        return this;
    }

    public String getFormattedFechaHoraRetiro() {
        return fechaHoraRetiro.format(formatter);
    }

    public void setFormattedFechaHoraRetiro(String formattedFechaHoraRetiro) {
        this.fechaHoraRetiro = LocalDateTime.parse(formattedFechaHoraRetiro, formatter);
    }
}
