package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//ENCAPSULA LA LÓGICA
@Service//indica que esta clase es un servicio de Spring
public class TrajectoryService {
    private final TrajectoryRepository trajectoryRepository; //inyecta instancia de repository por constructor

    public TrajectoryService(TrajectoryRepository trajectoryRepository) {
        this.trajectoryRepository = trajectoryRepository;
    }

    public Page<TrajectoryModel> getByTaxiIdAndDate(int taxiId, String dateString, Pageable pageable) {// define método para obtener página de trayectorias filtradas por taxiId y date
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); //parsea la fecha en cierto formato
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN); //obtiene el inicio del día
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX); //obtiene el fin del día
        //utiliza el método findByTaxiIdAndDate del repositorio para obtener una página de objetos TrajectoryModel filtradas por taxiId y el periodo de tiempo
        return trajectoryRepository.findByTaxiIdAndDate(taxiId, startOfDay, endOfDay, pageable);
    }
}

//Service -> Repository
