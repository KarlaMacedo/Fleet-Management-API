package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.repositories.TaxiRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest // indica que esta es una prueba de integración completa de Spring Boot
public class TaxiServiceTest {
    @Autowired //inicializa el service
    private TaxiService taxiService;

    @MockBean
    private TaxiRepository taxiRepository;

    @Test
    void testGetTaxis() { //verifica el comportamiento del método getTaxis() del service
        List<TaxiModel> taxis = new ArrayList<>();
        taxis.add(new TaxiModel(1, "ABCD-1234"));
        taxis.add(new TaxiModel(2, "EFGH-5678"));

        Page<TaxiModel> page = new PageImpl<>(taxis);
        Pageable pageable = Pageable.unpaged(); //lista completa de resultados sin paginación

        when(taxiRepository.findAll(pageable)).thenReturn(page);

        Page<TaxiModel> result = taxiService.getTaxis(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("ABCD-1234", result.getContent().get(0).getPlate());
        assertEquals("EFGH-5678", result.getContent().get(1).getPlate());
    }
}