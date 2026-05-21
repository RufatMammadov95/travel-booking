package com.travel.flight;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService {

	private final RestTemplate restTemplate = new RestTemplate();

	public String getExternalFlights(String origin, String destination) {
		String url = "https://opensky-network.org/api/states/all";

		try {
			String result = restTemplate.getForObject(url, String.class);

			return "Flight results from " + origin + " to " + destination + " (Source: OpenSky Network): "
					+ result.substring(0, 500) + "...";
		} catch (Exception e) {
			return "Error: Could not connect to external flight API. Details: " + e.getMessage();
		}
	}
}