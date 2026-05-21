package com.travel.flight;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

	private MockMvc mockMvc;

	@Mock
	private FlightService flightService;

	@InjectMocks
	private FlightController flightController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}

	@Test
	void searchFlightsReturnsServiceResult() throws Exception {
		when(flightService.getExternalFlights("Baku", "Istanbul"))
				.thenReturn("Flight results from Baku to Istanbul");

		mockMvc.perform(get("/flights/search")
				.param("origin", "Baku")
				.param("destination", "Istanbul"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Baku to Istanbul")));

		verify(flightService).getExternalFlights("Baku", "Istanbul");
	}

	@Test
	void searchFlightsRequiresOriginAndDestination() throws Exception {
		mockMvc.perform(get("/flights/search")
				.param("origin", "Baku"))
				.andExpect(status().isBadRequest());
	}
}
