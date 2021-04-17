package com.cg.flightsearchapi.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="flight_table")
public class Flight {
	
	@Id
	@Column(name ="Flight_id")
	private String flightId;
	
	@Column(name ="Flight_name")
	private String flightName;
	
	@Column(name ="Source")
	private String source;
	
	@Column(name ="Destination")
	private String destination;
	
	@Column(name ="Date")
	private String date;
	
	@Column(name ="Fare")
	private double fare;

	
	public Flight() {
		
	}

	public Flight(String flightId, String flightName,String source,String destination,
			String date,double fare) {
		super();
		this.flightId=flightId;
		this.flightName=flightName;
		this.source=source;
		this.destination=destination;
		this.date=date;
		this.fare=fare;
	
	}


	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	
}
	
	

