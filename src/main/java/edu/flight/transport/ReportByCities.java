package edu.flight.transport;

public class ReportByCities {
	private String city;
	private int numberOfTickets;
	private double ticketSum;

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getTicketSum() {
		return ticketSum;
	}

	public void setTicketSum(double ticketSum) {
		this.ticketSum = ticketSum;
	}

	public ReportByCities(String city, long numberOfTickets, double ticketSum) {
		this.city = city;
		this.numberOfTickets = (int) numberOfTickets;
		this.ticketSum = ticketSum;
	}

	public ReportByCities() {
	}

}
