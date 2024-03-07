package javaapi.fleetmanagement.services;

import java.util.ArrayList;
import java.util.List;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.repositories.LastLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.*;

public class LastLocationServiceTest {

    @Mock //simula clases o interfaces
    private LastLocationRepository lastLocationRepository;

    @InjectMocks//inyecta las instancias del mock en el service
    private LastLocationService lastLocationService;

    @BeforeEach//se ejecuta antes de cada prueba
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    } //se inicializan los mocks y abre su contexto

    @Test
    public void testGetLatestTrajectories() {//prueba que el método del service devuelva su resultado al haber usado el repository
        // representacón de la solicitud de una página de datos paginada
        int page = 0;
        int size = 10;
        PageRequest pageable = PageRequest.of(page, size);

        //se configura que el repositorio devuelva una lista vacía de datos simulados paginados
        List<Object[]> testData = new ArrayList<>();
        Page<Object[]> mockedPage = new PageImpl<>(testData, pageable, testData.size());
        when(lastLocationRepository.findLastTrajectories(pageable)).thenReturn(mockedPage);

        Page<LastLocationDTO> result = lastLocationService.getLatestTrajectories(pageable);

        Assertions.assertNotNull(result);//se espera que el resultado no sea nulo
        Assertions.assertEquals(testData.size(), result.getContent().size()); // se espera que el size sea igual entre el simulado y obtenido
    }
}