package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TrajectoryModel;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@SpringBootTest
public class TrajectoryServiceTest {
    @Mock
    private TrajectoryRepository trajectoryRepository;

    @Test
    void testGetTrajectoriesByIdAndDate() {
        LocalDate date = LocalDate.parse("2024-02-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").ascending());
        TrajectoryModel trajectoryModel = new TrajectoryModel(1, null, date, 0.0, 0.0);
        Page<TrajectoryModel> expectedPage = new PageImpl<>(Collections.singletonList(trajectoryModel), pageable, 1);

        when(trajectoryRepository.findByTaxiIdAndDate(1, date, pageable)).thenReturn(expectedPage);

        TrajectoryService trajectoryService = new TrajectoryService(trajectoryRepository);
        Page<TrajectoryModel> actualPage = trajectoryService.getByTaxiIdAndDate(1, "2024-02-20", pageable);

        assertEquals(expectedPage, actualPage);

    }
}
