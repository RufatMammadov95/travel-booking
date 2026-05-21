package com.travel.carrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-rentals")
public class CarRentalController {

	@Autowired
	private CarRentalService carRentalService;

	@GetMapping("/search")
	public String searchCars(@RequestParam String location) {
		return carRentalService.getExternalCars(location);
	}
}