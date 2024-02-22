package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@SpringBootTest
public class TrajectoryServiceTest {

    @MockBean
    private TrajectoryRepository trajectoryRepository;

    @Autowired
    private TrajectoryService trajectoryService;

    @Test
    public void testGetByTaxiIdAndDate_shouldReturnEmptyPage_whenNoDataFound() {
        Page<TrajectoryModel> emptyPage = Page.empty();
        when(trajectoryRepository.findByTaxiIdAndDate(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(emptyPage);

        int taxiId = 123;
        String dateString = "2023-12-31";
        Page<TrajectoryModel> trajectories = trajectoryService.getByTaxiIdAndDate(taxiId, dateString, Pageable.ofSize(10));

        assertEquals(trajectories.getContent(), Collections.emptyList());
    }

    @Test
    public void testGetByTaxiIdAndDate_shouldReturnData_whenDataExists() {
        List<TrajectoryModel> mockData = Collections.singletonList(new TrajectoryModel());
        Page<TrajectoryModel> mockPage = new PageImpl<>(mockData);
        when(trajectoryRepository.findByTaxiIdAndDate(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(mockPage);

        int taxiId = 456;
        String dateString = "2024-02-22";
        Pageable pageable = PageRequest.of(0, 10);
        Page<TrajectoryModel> trajectories = trajectoryService.getByTaxiIdAndDate(taxiId, dateString, pageable);

        assertEquals(trajectories.getContent(), mockData);
    }
}