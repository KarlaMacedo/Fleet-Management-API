package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.services.LastLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

// GESTIONA SOLICITUDES HTTP Y DEVUELVE RESPONSES
@RestController// indica que esta clase es un controlador de Spring que manejará las solicitudes HTTP
@RequestMapping("/trajectories")// establece ruta base para todas las solicitudes manejadas por este controlador
public class LastLocationController {
    @Autowired// inyecta instancia de service
    private LastLocationService lastLocationService;

    @GetMapping("/lastLocations")//indica que este método manejará solicitudes HTTP GET en la ruta /trajectories/lastLocations
    //método para obtener lista paginada de ultimas trayectorias de cada taxi
    public Page<LastLocationDTO> lastLocationDTOS(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {//recibe dos parámetros opcionales
        PageRequest pageable = PageRequest.of(page, size); //objeto PageRequest utilizando parámetros
        return lastLocationService.getLatestTrajectories(pageable);//página de objetos LastLocationDTO obtenida del servicio
    }
}
//Controller -> Service