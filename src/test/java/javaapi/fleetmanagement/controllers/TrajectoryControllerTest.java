package javaapi.fleetmanagement.controllers;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.services.TrajectoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrajectoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajectoryService trajectoryService;

    @Test
    void testGetTrajectoryByIdAndDate () throws Exception {
        int taxiId = 7957;
        LocalDate date = LocalDate.of(2008, 2, 2);
        PageRequest pageable = PageRequest.of(0, 10);
        List<TrajectoryModel> trajectoryList = new ArrayList<>();
        trajectoryList.add(new TrajectoryModel(1, null, date, 0.0, 0.0));
        Page<TrajectoryModel> trajectoryPage = new PageImpl<>(trajectoryList);

        // Mocking service response
        when(trajectoryService.getByTaxiIdAndDate(taxiId, date.toString(), pageable))
                .thenReturn(trajectoryPage);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/trajectory/{taxiId}", taxiId)
                        .param("date", date.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].date").value(date.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));
    }
}