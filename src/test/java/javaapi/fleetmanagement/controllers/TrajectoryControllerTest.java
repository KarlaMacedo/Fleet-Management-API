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
import java.util.List;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TrajectoryController.class)
public class TrajectoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajectoryService trajectoryService;

    @Test
    public void testGetByTaxiIdAndDate() throws Exception {
        // Given
        int taxiId = 1;
        LocalDate date = LocalDate.of(2024, 2, 22);
        Page<TrajectoryModel> expectedPage = new PageImpl<>(Collections.emptyList());
        when(trajectoryService.getByTaxiIdAndDate(taxiId, date.toString(), Pageable.unpaged())).thenReturn(expectedPage);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/trajectory/{taxiId}", taxiId)
                        .param("date", date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}