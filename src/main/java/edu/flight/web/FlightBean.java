package edu.flight.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.flight.entity.Flight;
import edu.flight.service.BusinessAdministratorService;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class FlightBean {

	@Inject
	private BusinessAdministratorService businessAdministratorService;

	private int code;
	private String flightNo;
	private Date departureDateTime;
	private Date arrivalDateTime;
	private double ticketPrice;
	private int amountOfTickets;
	private String arrivalCity;
	private String planeType;
	private String company;
	private List<Flight> flights;
	private Flight selectedFlight;

	public List<Flight> getFlights() {
		flights = businessAdministratorService.showFlights();
		return flights;
	}

	public FlightBean() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
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

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Flight Edited",
				((Flight) event.getObject()).getFlightNo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Flight Cancelled",
				((Flight) event.getObject()).getFlightNo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String addFlight() {
		Flight flight = new Flight();
		flight.setAmountOfTickets(amountOfTickets);
		flight.setArrivalCity(arrivalCity);
		flight.setArrivalDateTime(new Timestamp(arrivalDateTime.getTime()));
		flight.setCompany(company);
		flight.setDepartureDateTime(new Timestamp(departureDateTime.getTime()));
		flight.setPlaneType(planeType);
		flight.setTicketPrice(ticketPrice);
		flight.setFlightNo(flightNo);
		businessAdministratorService.createFlight(flight);
		return "businessAdministrator.xhtml";
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void removeFlight(Flight flight) {
		businessAdministratorService.deleteFlight(flight);
	}

}
