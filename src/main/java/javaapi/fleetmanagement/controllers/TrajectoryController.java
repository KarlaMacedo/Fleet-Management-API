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

// GESTIONA SOLICITUDES HTTP Y DEVUELVE RESPONSES
@RestController// indica que esta clase es un controlador de Spring que manejará las solicitudes HTTP
@RequestMapping("/trajectory")// establece ruta base para todas las solicitudes manejadas por este controlador
public class TrajectoryController {
    private final TrajectoryService trajectoryService;// inyecta instancia de service por constructor

    public TrajectoryController(TrajectoryService trajectoryService) {
        this.trajectoryService = trajectoryService;
    }

    @GetMapping("/{taxiId}")//indica que este método manejará solicitudes HTTP GET con la variable de ruta taxiId
    //método para obtener página de trayectorias filtradas por taxiId y date
    public ResponseEntity<Page<TrajectoryModel>> getByTaxiIdAndDate(//recibe dos parámetros opcionales, uno necesario y otro de la url
            @PathVariable int taxiId, //parametro de la URL
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);//objeto PageRequest utilizando parámetros
        String dateString = date == null ? null : date.toString();
        Page<TrajectoryModel> trajectories = trajectoryService.getByTaxiIdAndDate(taxiId, dateString, pageable);//página de objetos TrajectoryModel obtenida del servicio
        return ResponseEntity.ok(trajectories); //respuesta exitosa y pagina
    }
}

//Controller -> Service
