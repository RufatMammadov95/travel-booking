package com.travel.hotel;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HotelService {
	private final RestTemplate restTemplate = new RestTemplate();

	public String getExternalHotels(String location) {
		String url = "https://jsonplaceholder.typicode.com/users";
		try {
			String result = restTemplate.getForObject(url, String.class);
			return "Hotels found in " + location + " (Source: External Partner): " + result.substring(0, 400) + "...";
		} catch (Exception e) {
			return "Hotel API Error: " + e.getMessage();
		}
	}
}