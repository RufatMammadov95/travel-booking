package com.travel.carrental;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarRentalService {
	private final RestTemplate restTemplate = new RestTemplate();

	public String getExternalCars(String location) {
		String url = "https://fakestoreapi.com/products/category/electronics";
		try {
			String result = restTemplate.getForObject(url, String.class);
			return "Car rentals available at " + location + " (Provider: Global Cars): " + result.substring(0, 400)
					+ "...";
		} catch (Exception e) {
			return "Car Rental API Error: " + e.getMessage();
		}
	}
}