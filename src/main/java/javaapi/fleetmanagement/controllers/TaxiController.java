package javaapi.fleetmanagement.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.services.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// GESTIONA SOLICITUDES HTTP Y DEVUELVE RESPONSES
@RestController // indica que esta clase es un controlador de Spring que manejará las solicitudes HTTP
@RequestMapping("/taxi") // establece ruta base para todas las solicitudes manejadas por este controlador
public class TaxiController {
    @Autowired // inyecta instancia de service
    TaxiService taxiService;

    @GetMapping() //indica que este método manejará solicitudes HTTP GET
    //método para obtener una lista paginada de taxis
    public Page<TaxiModel> getTaxis(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){ //recibe dos parámetros opcionales
        PageRequest pageable = PageRequest.of(page, size); //objeto PageRequest utilizando parámetros
        return taxiService.getTaxis(pageable); //página de objetos TaxiModel obtenida del servicio
    }

    //Controller -> Service

}
