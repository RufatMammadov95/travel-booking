package com.travel.carrental;

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
class CarRentalControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CarRentalService carRentalService;

	@InjectMocks
	private CarRentalController carRentalController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(carRentalController).build();
	}

	@Test
	void searchCarsReturnsServiceResult() throws Exception {
		when(carRentalService.getExternalCars("Baku"))
				.thenReturn("Car rentals available at Baku");

		mockMvc.perform(get("/car-rentals/search")
				.param("location", "Baku"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Baku")));

		verify(carRentalService).getExternalCars("Baku");
	}

	@Test
	void searchCarsRequiresLocation() throws Exception {
		mockMvc.perform(get("/car-rentals/search"))
				.andExpect(status().isBadRequest());
	}
}
