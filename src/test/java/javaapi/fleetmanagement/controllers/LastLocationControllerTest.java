package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.services.LastLocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//habilita la integración de Spring con las pruebas JUnit
public class LastLocationControllerTest {

    @Mock//simula clases o interfaces
    private LastLocationService lastLocationService;

    @InjectMocks //inyecta las instancias del mock en el controller
    private LastLocationController lastLocationController;

    @Test
    void testLastLocationDTOS() {//prueba que el método del controller devuelva los resultados al usar service
        //simula data
        LastLocationDTO lastLocationDTO = new LastLocationDTO(1, "ABC123", null, 40.7128, -74.0060);
        List<LastLocationDTO> locationList = List.of(lastLocationDTO);
        Page<LastLocationDTO> mockedPage = new PageImpl<>(locationList);

        Pageable pageable = PageRequest.of(0, 10);// simula parámetros de paginación
        when(lastLocationService.getLatestTrajectories(pageable)).thenReturn(mockedPage);// simula que el service debería devolver la data falsa

        Page<LastLocationDTO> result = lastLocationController.lastLocationDTOS(0, 10);//se llama el método del controler con los params

        Assertions.assertNotNull(result);//se espera que el resultado no sea nulo
        Assertions.assertEquals(1, result.getContent().size());//se espera que el resultado contenga un elemento que fue el que se simuló
    }
}