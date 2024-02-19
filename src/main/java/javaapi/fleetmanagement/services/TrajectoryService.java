package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TrajectoryService {
    private final TrajectoryRepository trajectoryRepository;

    public TrajectoryService(TrajectoryRepository trajectoryRepository) {
        this.trajectoryRepository = trajectoryRepository;
    }

    public Page<TrajectoryModel> getByTaxiIdAndDate(int taxiId, String dateString, Pageable pageable) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return trajectoryRepository.findByTaxiIdAndDate(taxiId, date, pageable);
    }
}