package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.repositories.LastLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

@Service
public class LastLocationService {
    @Autowired
    private LastLocationRepository lastLocationRepository;

    public Page<LastLocationDTO> getLatestTrajectories(Pageable pageable) {
        Page<Object[]> lastTrajectories = lastLocationRepository.findLastTrajectories(pageable);
        lastTrajectories.forEach(row -> System.out.println(Arrays.toString(row)));
        return lastTrajectories.map(row -> new LastLocationDTO(
                (Integer) row[0], // id
                (String) row[1], // plate
                ((Timestamp) row[2]).toLocalDateTime(), // timestamp
                (Double) row[3], // latitude
                (Double) row[4] // longitude
        ));
    }
}