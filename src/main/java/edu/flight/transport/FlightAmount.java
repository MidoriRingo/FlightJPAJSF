package edu.flight.transport;

public class FlightAmount {
	int flightCode;
	int amount;
	public FlightAmount(int flightCode, int amount) {
		this.flightCode = flightCode;
		this.amount = amount;
	}
	public int getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(int flightCode) {
		this.flightCode = flightCode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
