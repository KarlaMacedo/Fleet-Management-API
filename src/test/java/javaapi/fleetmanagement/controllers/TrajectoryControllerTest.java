package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.services.TrajectoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

@ExtendWith(SpringExtension.class)//habilita la integración de Spring con las pruebas JUnit
@WebMvcTest(TrajectoryController.class) //indica que es una prueba específica de un controlador web de Spring
public class TrajectoryControllerTest {

    @Autowired // inyecta instancia MockMvc para simular solicitudes HTTP y verificar el comportamiento de controladores
    private MockMvc mockMvc;

    @MockBean//simula componentes de Spring
    private TrajectoryService trajectoryService;

    @Test
    public void testGetByTaxiIdAndDate() throws Exception {// verifica que el controller maneje correctamente una solicitud GET
        int taxiId = 1;
        LocalDate date = LocalDate.of(2024, 2, 22);
        Page<TrajectoryModel> expectedPage = new PageImpl<>(Collections.emptyList());
        //llamando al service con los parametros correctos
        when(trajectoryService.getByTaxiIdAndDate(taxiId, date.toString(), Pageable.unpaged())).thenReturn(expectedPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/trajectory/{taxiId}", taxiId)
                        .param("date", date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); //y se espera una respuesta exitosa
    }
}