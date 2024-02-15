package javaapi.fleetmanagement.controllers;

import static org.junit.jupiter.api.Assertions.*;

import javaapi.fleetmanagement.controllers.TaxiController;
import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.services.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class TaxiControllerTest {

    @Mock
    private TaxiService taxiService;

    @InjectMocks
    private TaxiController taxiController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTaxis() {
        List<TaxiModel> taxis = new ArrayList<>();
        taxis.add(new TaxiModel(1, "ABCD-1234"));
        taxis.add(new TaxiModel(2, "EFGH-5678"));

        Page<TaxiModel> page = new PageImpl<>(taxis);
        when(taxiService.getTaxis(PageRequest.of(0,10))).thenReturn(page);

        Page<TaxiModel> result = taxiController.getTaxis(0,10);

        assertEquals(2, result.getTotalElements());
        assertEquals("ABCD-1234", result.getContent().get(0).getPlate());
        assertEquals("EFGH-5678", result.getContent().get(1).getPlate());
    }

}