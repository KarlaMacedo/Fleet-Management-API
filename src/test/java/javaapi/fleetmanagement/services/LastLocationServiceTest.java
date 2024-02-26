package javaapi.fleetmanagement.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import static org.mockito.Mockito.*;

public class LastLocationServiceTest {

    @Mock
    private LastLocationRepository lastLocationRepository;

    @InjectMocks
    private LastLocationService lastLocationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getLatestTrajectories")
    public void testGetLatestTrajectories() {
        int page = 0;
        int size = 10;
        PageRequest pageable = PageRequest.of(page, size);

        List<Object[]> testData = new ArrayList<>();

        Page<Object[]> mockedPage = new PageImpl<>(testData, pageable, testData.size());
        when(lastLocationRepository.findLastTrajectories(pageable)).thenReturn(mockedPage);

        Page<LastLocationDTO> result = lastLocationService.getLatestTrajectories(pageable);
    }
}