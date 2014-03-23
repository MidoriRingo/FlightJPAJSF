package edu.flight.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String flightNo;
	private java.sql.Timestamp departureDateTime;
	private java.sql.Timestamp arrivalDateTime;
	private double ticketPrice;
	private int amountOfTickets;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private String arrivalCity;
	private String planeType;
	private String company;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Timestamp getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Timestamp departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Timestamp getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Timestamp arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getAmountOfTickets() {
		return amountOfTickets;
	}

	public void setAmountOfTickets(int amountOfTickets) {
		this.amountOfTickets = amountOfTickets;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Flight() {
	}



	public Flight(String flightNo, Timestamp departureDateTime,
			Timestamp arrivalDateTime, double ticketPrice, int amountOfTickets,
			String arrivalCity, String planeType, String company) {
		super();
		this.flightNo = flightNo;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.ticketPrice = ticketPrice;
		this.amountOfTickets = amountOfTickets;
		this.arrivalCity = arrivalCity;
		this.planeType = planeType;
		this.company = company;
	}

}
