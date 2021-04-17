package com.cg.flightsearchapi.service;

import java.util.List;

import com.cg.flightsearchapi.entity.Flight;



public interface FlightSearchService {

	public List<Flight> viewAllFlights();

	public  List<Flight> getFlightBySourceDestinationDate(String source, String destination, String date);

	public String addFlight(Flight flight) throws Exception;

	public Flight getFlightById(String id);

	
}
