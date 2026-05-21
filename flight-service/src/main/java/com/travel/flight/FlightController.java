package com.travel.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/search")
	public String searchFlights(@RequestParam String origin, @RequestParam String destination) {
		return flightService.getExternalFlights(origin, destination);
	}
}