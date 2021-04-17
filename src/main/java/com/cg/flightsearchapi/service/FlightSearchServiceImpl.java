package com.cg.flightsearchapi.service;



import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.flightsearchapi.entity.Flight;
import com.cg.flightsearchapi.exception.FlightNotFoundException;
import com.cg.flightsearchapi.repository.FlightRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;



@Service
public class FlightSearchServiceImpl implements FlightSearchService {
	
	@Autowired
    FlightRepository flightRepo;
	
	
	// db - 1st - it is not responding for 2 sec = success
	// db - 2 - it is not responding for 2 sec = success
	// db - 3 - it is not responding for 2 sec = success
	// db - 4 - it is not responding for 2 sec = fault request - wait for 5 sec
	// db - 5 - it is not responding for 2 sec = fault request - wait for 5 sec
	// db - 6 - it is not responding for 2 sec = fault request - wait for 5 sec
	@HystrixCommand(fallbackMethod = "getFallbackFlights",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			})
	@Override
	public List<Flight> viewAllFlights() {
	
		return flightRepo.findAll();
	
	}
	


	@HystrixCommand(fallbackMethod = "getFallbackFlightsBySourceDestinationDate",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			})
	@Override
	public List<Flight> getFlightBySourceDestinationDate(String source, String destination, String date) {
		
		List<Flight> flights = flightRepo.findBySourceDestinationDate(source, destination, date);
		
		if(flights.size() != 0) {
			return flights;
		}
		else {
			throw new FlightNotFoundException("There is no flight between "+source+" to "+destination+" on "+date);
		}
	}

	@Override
	public String addFlight(Flight flight) throws Exception {
		Random rand = new Random(); 
		int random = rand.nextInt(1000); 
		//generating flight
		flight.setFlightId(random+""+ (int)(flight.getFare()/random));
		if(flightRepo.save(flight) != null) {
			return "flight "+flight.getFlightName()+"is UP!";
		}
		else {
			throw new Exception("fail to add flight");
		}
		
		
	}

	@Override
	public Flight getFlightById(String id) {

		return flightRepo.findById(id).get();
	}
	
	
	
	
	public List<Flight> getFallbackFlights(){
		
		return Arrays.asList(
					new Flight("TestId", "TestName", "TestSource", "TestDestination", "TestDate", 0)
				); 
		
	}
	
	public List<Flight> getFallbackFlightsBySourceDestinationDate(String source, String destination, String date){
		
		return Arrays.asList(
					new Flight("TestId", "TestName", "TestSource", "TestDestination", "TestDate", 0)
				); 
		
	}

}
