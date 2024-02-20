package javaapi.fleetmanagement;

import javaapi.fleetmanagement.repositories.TaxiRepository;
import javaapi.fleetmanagement.repositories.TrajectoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FleetManagementApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TaxiRepository taxiRepository;

	@Test
	@DisplayName("TestFirstEndPoint")
	void testFirstEndPointContextLoads() throws Exception {
		mockMvc.perform(get("/taxi?page=0&size=10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content[0].id").exists())
				.andExpect(jsonPath("$.content[0].plate").exists())
				.andExpect(jsonPath("$.content.length()").value(10));
	}

	@Autowired
	private TrajectoryRepository trajectoryRepository;

	@Test
	@DisplayName("TestSecondEndPoint")
	void testSecondEndPointContextLoads() throws Exception {
		mockMvc.perform(get("/trajectory/7957?date=2008-02-02&page=0&size=20")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content[0].id").exists())
				.andExpect(jsonPath("$.content[0].taxi.id").exists())
				.andExpect(jsonPath("$.content[0].taxi.plate").exists())
				.andExpect(jsonPath("$.content[0].date").exists())
				.andExpect(jsonPath("$.content[0].latitude").exists())
				.andExpect(jsonPath("$.content[0].longitude").exists())
				.andExpect(jsonPath("$.content[0].taxi.id").value(7957))
				.andExpect(jsonPath("$.content[0].date").value("2008-02-02"))
				.andExpect(jsonPath("$.content.length()").value(20));
	}

}
