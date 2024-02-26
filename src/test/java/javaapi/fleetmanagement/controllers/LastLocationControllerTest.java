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

@ExtendWith(MockitoExtension.class)
public class LastLocationControllerTest {

    @Mock
    private LastLocationService lastLocationService;

    @InjectMocks
    private LastLocationController lastLocationController;

    @Test
    @DisplayName("Test lastLocationDTOS")
    void testLastLocationDTOS() {
        LastLocationDTO lastLocationDTO = new LastLocationDTO(1, "ABC123", null, 40.7128, -74.0060);
        List<LastLocationDTO> locationList = List.of(lastLocationDTO);
        Page<LastLocationDTO> mockedPage = new PageImpl<>(locationList);

        Pageable pageable = PageRequest.of(0, 10);
        when(lastLocationService.getLatestTrajectories(pageable)).thenReturn(mockedPage);

        Page<LastLocationDTO> result = lastLocationController.lastLocationDTOS(0, 10);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getContent().size());
    }
}