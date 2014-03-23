package edu.flight.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.flight.entity.Flight;
import edu.flight.service.CustomerService;
import edu.flight.transport.BookedTicketsWeb;

@Named
@Scope("session")
public class SearchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	CustomerService customerService;
	@Inject
	BasketBean basketBean;

	private String arrivalCity;
	private Date departureDateStart;
	private Date departureDateEnd;
	private int amount;
	private List<Flight> searchedFlights;
	private Flight selectedFlight;
	private FlightDataModel flightModel;
	private String flightNo;

	public List<Flight> getSearchedFlights() {
		return searchedFlights;
	}

	public void setSearchedFlights(List<Flight> searchedFlights) {
		this.searchedFlights = searchedFlights;
	}

	public SearchBean() {
	}

	public SearchBean(String arrivalCity, Date departureDateStart,
			Date departureDateEnd, int amount) {
		this.arrivalCity = arrivalCity;
		this.departureDateStart = departureDateStart;
		this.departureDateEnd = departureDateEnd;
		this.amount = amount;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Date getDepartureDateStart() {
		return departureDateStart;
	}

	public void setDepartureDateStart(Date departureDateStart) {
		this.departureDateStart = departureDateStart;
	}

	public Date getDepartureDateEnd() {
		return departureDateEnd;
	}

	public void setDepartureDateEnd(Date departureDateEnd) {
		this.departureDateEnd = departureDateEnd;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	public String searchFlightsCustomer() {
		if (departureDateEnd.before(departureDateStart)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Start date is later then end date",
					"Check the dates, please");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else
		searchedFlights = customerService.findFlights(departureDateStart,
				departureDateEnd, arrivalCity, amount);
		flightModel = new FlightDataModel(searchedFlights);
		return "flightView.xhtml";
	}

	public String putTicketsToBasket() {
		if (selectedFlight != null){
		BookedTicketsWeb ticket = new BookedTicketsWeb();
		ticket.setFlightId(selectedFlight.getCode());
		ticket.setCity(selectedFlight.getArrivalCity());
		ticket.setTicketPrice(selectedFlight.getTicketPrice());
		ticket.setFlightNo(selectedFlight.getFlightNo());
		ticket.setCount(amount);
		basketBean.addToBasket(ticket);
		System.out.println(selectedFlight.getFlightNo());
		return "basket.xhtml";
		}
		else {
			FacesMessage msg = new FacesMessage("There is no Flights chosen");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        return "flightView.xhtml";
		}
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public FlightDataModel getFlightModel() {
		return flightModel;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

}
