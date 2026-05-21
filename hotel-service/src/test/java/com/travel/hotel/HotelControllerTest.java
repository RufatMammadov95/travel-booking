package com.travel.hotel;

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
class HotelControllerTest {

	private MockMvc mockMvc;

	@Mock
	private HotelService hotelService;

	@InjectMocks
	private HotelController hotelController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
	}

	@Test
	void searchHotelsReturnsServiceResult() throws Exception {
		when(hotelService.getExternalHotels("Baku"))
				.thenReturn("Hotels found in Baku");

		mockMvc.perform(get("/hotels/search")
				.param("location", "Baku"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Baku")));

		verify(hotelService).getExternalHotels("Baku");
	}

	@Test
	void searchHotelsRequiresLocation() throws Exception {
		mockMvc.perform(get("/hotels/search"))
				.andExpect(status().isBadRequest());
	}
}
