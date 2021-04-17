package com.cg.flightsearchapi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flightsearchapi.entity.Flight;
import com.cg.flightsearchapi.exception.NullValueException;
import com.cg.flightsearchapi.service.FlightSearchService;




@RestController
@CrossOrigin("*")
@RequestMapping("/flight_management")
public class FlightController {

	@Autowired
	FlightSearchService flightSearchService;
	
	@GetMapping("/viewAllFlights")
	public List<Flight> getAllFlights() {
       return flightSearchService.viewAllFlights();
    }
	
	@GetMapping("/getFlightById")
	public Flight getFlightById(@RequestParam String id) {
       return flightSearchService.getFlightById(id);
    }
	
	
	@GetMapping("/getFlightBySourceDestinationDate")
	public List<Flight> getFlightBySourceDestinationDate(@RequestParam String source , @RequestParam String destination, @RequestParam String date) {
		if (source.equals("") || destination.equals("") || date.equals("")) {
			throw new NullValueException("Please provide correct details!");
		}
		return flightSearchService.getFlightBySourceDestinationDate(source, destination, date);
	}
	
	@PostMapping("/addflight")
	public String addFlight(@RequestBody Flight flight) throws Exception {
		return flightSearchService.addFlight(flight);
	}
	
	
	
	
	
}
