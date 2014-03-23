package edu.flight.transport;

public class BookedTicketsWeb {
	private String flightNo;
	private double ticketPrice;
	private String city;
	private int count;
	private long flightId;
	
	public BookedTicketsWeb(){}
	
	public BookedTicketsWeb(String flightNo, double ticketPrice, String city,
			int count, long flightId) {
		super();
		this.flightNo = flightNo;
		this.ticketPrice = ticketPrice;
		this.city = city;
		this.count = count;
		this.flightId = flightId;
	}
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
