package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrajectoryService {

    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public Page<TrajectoryModel> getTaxiLocations(Integer taxiId, String date, Pageable pageable) {
        return trajectoryRepository.findByTaxiIdAndDate(taxiId, date, pageable);
    }
}
