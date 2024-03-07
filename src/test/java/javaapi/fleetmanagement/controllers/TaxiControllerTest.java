package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.services.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TaxiController.class) //indica que es una prueba específica de un controlador web de Spring
class TaxiControllerTest {

    @Autowired //inicializa el controlador
    private TaxiController taxiController;

    @MockBean //simula componentes de Spring
    private TaxiService taxiService;

    @BeforeEach //se ejecuta antes de cada prueba
    public void init() { //configura el mock sel service
        List<TaxiModel> taxis = new ArrayList<>();
        taxis.add(new TaxiModel(1, "ABCD-1234"));
        taxis.add(new TaxiModel(2, "EFGH-5678"));

        Page<TaxiModel> page = new PageImpl<>(taxis);
        when(taxiService.getTaxis(PageRequest.of(0,10))).thenReturn(page);
    }

    @Test
    public void testGetTaxis() { //verifica el comportamiento del método getTaxis() del controller
        Page<TaxiModel> result = taxiController.getTaxis(0, 10);

        assertEquals(2, result.getTotalElements());
        assertEquals("ABCD-1234", result.getContent().get(0).getPlate());
        assertEquals("EFGH-5678", result.getContent().get(1).getPlate());
    }

}