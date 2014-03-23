package edu.flight.transport;

import java.util.Date;

public class ReportByDate {
	private Date orderDateTime;
	private long numberOfTickets;
	private double orderSum;

	public ReportByDate(Date orderDateTime, long numberOfTickets, double orderSum) {
		this.orderDateTime = orderDateTime;
		this.numberOfTickets = numberOfTickets;
		this.orderSum = orderSum;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public long getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(long numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(double orderSum) {
		this.orderSum = orderSum;
	}

	public ReportByDate() {
	}

}
