package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.services.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaxiRepositoryTest {
    @Autowired
    private TaxiRepository taxiRepository;

    @MockBean
    private TaxiService taxiService;

    @BeforeEach
    public void setUp() {
        List<TaxiModel> expectedTaxis = Arrays.asList(
                new TaxiModel(1, "ABC123"),
                new TaxiModel(2, "DEF456")
        );

        Page<TaxiModel> expectedPage = new PageImpl<>(expectedTaxis, PageRequest.of(0, 10), expectedTaxis.size());
        when(taxiRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);
    }

    @Test
    public void testFindAll() {
        List<TaxiModel> expectedTaxis = Arrays.asList(
                new TaxiModel(1, "ABC123"),
                new TaxiModel(2, "DEF456")
        );

        Page<TaxiModel> actualTaxis = new PageImpl<>(expectedTaxis, PageRequest.of(0, 10), expectedTaxis.size());

        assertEquals(expectedTaxis, actualTaxis.getContent());
    }
}