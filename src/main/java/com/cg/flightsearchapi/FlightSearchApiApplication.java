package com.cg.flightsearchapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import com.cg.flightsearchapi.entity.Flight;
import com.cg.flightsearchapi.repository.FlightRepository;


@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner demoData(FlightRepository repo) {
        return args -> { 

            repo.save(new Flight("1", "GOAIR A36","BBS", "MUM", "2020-03-02", 6000));
            repo.save(new Flight("2", "GOAIR A57","BBS", "MUM", "2020-05-25", 4000));
            repo.save(new Flight("3", "INDIGO B415", "KOL", "BLR", "2020-01-15", 7000));
            repo.save(new Flight("4", "AIRINDIA A98", "KOL", "BLR", "2020-06-15", 10000));
            repo.save(new Flight("5", "AirAsia India V456", "KOL", "BLR", "2020-03-22", 6000));
            repo.save(new Flight("6", "SpiceJet H567","BBS", "MUM", "2020-07-14", 6000));
            repo.save(new Flight("7", "SpiceJet A90","BBS", "MUM", "2020-05-25", 9000));
            repo.save(new Flight("8", "INDIGO B57","BBS", "MUM", "2020-05-25", 9000));
        };
    }

}
