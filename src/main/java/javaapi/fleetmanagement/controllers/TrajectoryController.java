package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.services.TrajectoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trajectory")
public class TrajectoryController {
    private final TrajectoryService trajectoryService;

    public TrajectoryController(TrajectoryService trajectoryService) {
        this.trajectoryService = trajectoryService;
    }

    @GetMapping("/{taxiId}")
    public ResponseEntity<Page<TrajectoryModel>> getByTaxiIdAndDate(
            @PathVariable int taxiId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        String dateString = date == null ? null : date.toString();
        Page<TrajectoryModel> trajectories = trajectoryService.getByTaxiIdAndDate(taxiId, dateString, pageable);
        return ResponseEntity.ok(trajectories);
    }
}
