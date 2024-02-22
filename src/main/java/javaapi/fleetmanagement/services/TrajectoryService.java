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

@Service
public class TrajectoryService {
    private final TrajectoryRepository trajectoryRepository;

    public TrajectoryService(TrajectoryRepository trajectoryRepository) {
        this.trajectoryRepository = trajectoryRepository;
    }

    public Page<TrajectoryModel> getByTaxiIdAndDate(int taxiId, String dateString, Pageable pageable) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);
        return trajectoryRepository.findByTaxiIdAndDate(taxiId, startOfDay, endOfDay, pageable);
    }
}
