package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.services.LastLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trajectories")
public class LastLocationController {
    @Autowired
    private LastLocationService lastLocationService;

    @GetMapping("/lastLocations")
    public Page<LastLocationDTO> lastLocationDTOS(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return lastLocationService.getLatestTrajectories(pageable);
    }
}
