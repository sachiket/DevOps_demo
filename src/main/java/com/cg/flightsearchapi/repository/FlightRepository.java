package com.cg.flightsearchapi.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.flightsearchapi.entity.Flight;



@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
	
	@Modifying
	@Query("SELECT opm FROM Flight opm WHERE opm.source=:source and opm.destination=:destination and opm.date=:date")
	@Transactional
	List<Flight> findBySourceDestinationDate(String source, String destination, String date);


}
