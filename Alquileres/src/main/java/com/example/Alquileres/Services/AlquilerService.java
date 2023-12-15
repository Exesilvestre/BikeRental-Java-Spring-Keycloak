package com.example.Alquileres.Services;

import com.example.Alquileres.Entities.Alquiler;
import com.example.Alquileres.Entities.Tarifa;
import com.example.Alquileres.Repositories.AlquilerRepository;
import com.example.Alquileres.Repositories.TarifaRepository;
import com.example.Alquileres.Services.DTOS.AlquilerDTOFinAlquiler;
import com.example.Alquileres.Services.DTOS.AlquilerDTORead;
import com.example.Alquileres.Services.DTOS.AlquilerDTOPost;

import com.example.Alquileres.Services.DTOS.AlquilerFinRequestDto;
import com.example.Alquileres.Services.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {
    private AlquilerRepository alquilerRepository;

    private TarifaRepository tarifaRepository;
    private EstacionServiceInt estacionServiceInt;
    private CotizacionMOnedaInt cotizacionMOnedaInt;

    public AlquilerService(AlquilerRepository alquilerRepository, TarifaRepository tarifaRepository, EstacionServiceInt estacionServiceInt, CotizacionMOnedaInt cotizacionMOnedaInt) {
        this.alquilerRepository = alquilerRepository;
        this.tarifaRepository = tarifaRepository;
        this.estacionServiceInt = estacionServiceInt;
        this.cotizacionMOnedaInt = cotizacionMOnedaInt;
    }

    public List<AlquilerDTORead> findAll(){
        return alquilerRepository.findAll().stream()
                .map(alquiler -> new AlquilerDTORead(alquiler)).toList();
    }

    public Optional<AlquilerDTORead> findById(Long id){
        Optional<Alquiler> existingAlquiler = alquilerRepository.findById(id);
        return existingAlquiler.isEmpty()
                ? Optional.empty()
                : Optional.of(new AlquilerDTORead(existingAlquiler.get()));
    }

    public List<AlquilerDTORead> buscarPorEstaciones(Long estacionRetiroId, Long estacionDevId) {
        Boolean estacionRetiro = controlEstacion(estacionRetiroId);
        if (!estacionRetiro) {
            throw new ResourceNotFoundException("La estación de retiro no es válida");
        }

        Boolean estacionDevolucion = controlEstacion(estacionDevId);
        if (!estacionDevolucion) {
            throw new ResourceNotFoundException("La estación de devolucion no es válida");
        }

        List<Alquiler> alquileres = alquilerRepository.findAllByEstacionRetiroObjAndAndEstacionDevolucionObj(estacionRetiroId, estacionDevId);

        // Utiliza map para convertir Alquiler a AlquilerDTORead y toList() para recolectar los resultados en una lista
        List<AlquilerDTORead> alquilerDTOList = alquileres.stream()
                .map(a -> new AlquilerDTORead(a))
                .toList();

        return alquilerDTOList;
    }


    public AlquilerDTORead create(AlquilerDTOPost alq){
        Boolean estacionRetiro = controlEstacion(alq.getEstacionRetiroObj());
        if (!estacionRetiro){
            throw new ResourceNotFoundException("La estación de retiro no es válida");
        }
        Alquiler alquiler = new Alquiler(alq);
        Alquiler alquilerGuardada = alquilerRepository.save(alquiler);
        return new AlquilerDTORead(alquilerGuardada);
    }




    public AlquilerDTOFinAlquiler update(Long id, AlquilerFinRequestDto alquilerFinDTO) {
        Alquiler alq = alquilerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Alquiler [%d] inexistente", id))
        );

        Boolean estacionDevolucion = controlEstacion(alquilerFinDTO.getEstacionDevolucion());
        if (!estacionDevolucion) {
            throw new ResourceNotFoundException("La estación de devolución no es válida");
        }
        //seteo estacion de devolucion.
        alq.setEstacionDevolucionObj(alquilerFinDTO.getEstacionDevolucion());

        // Establecer la fecha y hora de devolución
        LocalDateTime fechaDevolucion = LocalDateTime.now();
        alq.setFechaHoraDevolucion(fechaDevolucion);
        int diaSemana = alq.getFechaHoraDevolucion().getDayOfWeek().getValue();
        LocalDateTime fechaRetiro = alq.getFechaHoraRetiro();
        //Obtener tarifa y calcular el monto
        Tarifa obtengoTarifa = buscarTarifaCorresponde(fechaRetiro, diaSemana, alq, fechaDevolucion);
        double monto = calcularCosto(obtengoTarifa, fechaRetiro, fechaDevolucion, alq);
        alq.setMonto(monto);

        // Guardar el alquiler actualizado en la base de datos
        Alquiler alquilerFinal = alquilerRepository.save(alq.update(id, alq));
        Double montoConvertido;
        if (alquilerFinDTO.getMonedaElegida() == null || "ARS".equals(alquilerFinDTO.getMonedaElegida())) {
            montoConvertido = alq.getMonto();
        } else {
            montoConvertido = cotizacionMOnedaInt.cotizarMoneda(alquilerFinal.getMonto(), alquilerFinDTO.getMonedaElegida());
        }
        //Retorno
        return new AlquilerDTOFinAlquiler(alquilerFinal, montoConvertido, alquilerFinDTO.getMonedaElegida());
    }

    public boolean deleteById(Long id) {
        if (!alquilerRepository.existsById(id)) {
            return false;
        }
        alquilerRepository.deleteById(id);
        return true;
    }

    private Tarifa buscarTarifaCorresponde(LocalDateTime fechaRetiro, int diaSemana, Alquiler alq, LocalDateTime fechaDevolucion ){
        // Obtener la lista de tarifas de promoción para esa fecha de devolución
        List<Tarifa> tarifasPromocionales = tarifaRepository.findAll();

        // Iterar sobre las tarifas para verificar coincidencias
        Tarifa tarifaSemana = null;
        Tarifa tarifaCoincidente = null;

        for (Tarifa tarifa : tarifasPromocionales) {
            if ("C".equals(tarifa.getDefinicion()) && coincideFechaDevolucionConTarifa(fechaRetiro, tarifa) != null) {
                tarifaCoincidente = tarifa;
                break; // Si encontramos una coincidencia, salimos del bucle
            } else if (!"C".equals(tarifa.getDefinicion()) && diaSemana == tarifa.getDiaSemana()) {
                tarifaSemana = tarifa;
            }

        }
        // Verificar si se encontró una tarifa coincidente
        if (tarifaCoincidente != null) {
            alq.setTarifa(tarifaCoincidente);
        }else{
            alq.setTarifa(tarifaSemana);
        }
        return alq.getTarifa();
    }

    // Método para verificar si la fecha de devolución coincide con la fecha de la tarifa
    private Tarifa coincideFechaDevolucionConTarifa(LocalDateTime fechaRetiro, Tarifa tarifa) {
        int diaTarifa = tarifa.getDiaMes();
        int mesTarifa = tarifa.getMes();
        int anioTarifa = tarifa.getAnio();

        int diaDevolucion = fechaRetiro.getDayOfMonth();
        int mesDevolucion = fechaRetiro.getMonthValue();
        int anioDevolucion = fechaRetiro.getYear();

        if (diaDevolucion == diaTarifa && mesDevolucion == mesTarifa && anioDevolucion == anioTarifa) {
            return tarifa;
        } else {
            return null;
        }
    }

    //CALCULA EL COSTO
    private Double calcularCosto(Tarifa tarifa, LocalDateTime fechaRetiro, LocalDateTime fechaDevolucion, Alquiler alq){
        // Calcular la diferencia de tiempo en minutos entre la fecha de retiro y la fecha de devolución
        long minutosTotales = ChronoUnit.MINUTES.between(fechaRetiro, fechaDevolucion);

        // Calcular las horas completas y los minutos restantes
        long horasCompletas = minutosTotales / 60;
        long minutosRestantes = minutosTotales % 60;
        if(minutosRestantes >= 30){
            minutosRestantes = 0;
            horasCompletas += 1;
        }

        double costoPorKm = estacionServiceInt.getDistanciaEntreEstaciones(alq.getEstacionRetiroObj(), alq.getEstacionDevolucionObj());
        double costoDistancia = costoPorKm * tarifa.getMontoKm();
        // Calcular el costo total
        double costoTotal = tarifa.getMontoFijoAlquiler() + (horasCompletas * tarifa.getMontoHora())+ (minutosRestantes * tarifa.getMontoMinutoFraccion()) + costoDistancia;
        return costoTotal;
    }

    public Boolean controlEstacion(Long id){
        Boolean estacion = estacionServiceInt.validateIdEstacion(id);
        return estacion;
    }
    public Tarifa controlTarifa(Long id){
        Tarifa tarifa = tarifaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Tarifa [%d] inexistente", id))
        );
        return tarifa;
    }
}
