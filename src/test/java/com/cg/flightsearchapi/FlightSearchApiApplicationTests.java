package com.cg.flightsearchapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.flightsearchapi.entity.Flight;
import com.cg.flightsearchapi.repository.FlightRepository;
import com.cg.flightsearchapi.service.FlightSearchService;



@RunWith(SpringRunner.class)
@SpringBootTest
class FlightSearchApiApplicationTests {

	@Autowired
	private FlightSearchService service;

	@MockBean
	private FlightRepository repository;

	@Test
	public void getAllFlightsTest() {

		when(repository.findAll())
				.thenReturn((List<Flight>) Stream
						.of(new Flight("1", "GOAIR A36", "BBS", "MUM", "03-02-2021", 6000),
								new Flight("3", "INDIGO B415", "KOL", "BLR", "15-01-201", 7000))
						.collect(Collectors.toList()));

		assertEquals(2, service.viewAllFlights().size());

	}

	@Test
	public void getFlightBySourceDestinationDateTest() {
		when(repository.findBySourceDestinationDate("BBS", "MUM", "03-02-2021")).thenReturn((List<Flight>) Stream
				.of(new Flight("1", "GOAIR A36", "BBS", "MUM", "03-02-2021", 6000)).collect(Collectors.toList()));

		assertEquals(1, service.getFlightBySourceDestinationDate("BBS", "MUM", "03-02-2021").size());
	}

	@Test
	public void addFlightTest() throws Exception {
		Flight flight = new Flight("99", "GOAIR 999", "BBS", "BLR", "03-02-2021", 4000);
		when(repository.save(flight)).thenReturn(flight);

		assertEquals("flight "+flight.getFlightName()+"is UP!", service.addFlight(flight));

	}

}
