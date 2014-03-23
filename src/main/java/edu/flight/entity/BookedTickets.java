package edu.flight.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import edu.flight.entity.Flight;;

@Entity
public class BookedTickets implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private int numberOfTickets;
	@ManyToOne
	@JoinColumn(name = "infoCode")
	private OrderInfo orderInfo;
	@ManyToOne
	@JoinColumn(name = "codeFlight")
	private Flight flight;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public BookedTickets(int code, int numberOfTickets, OrderInfo orderInfo,
			Flight flight) {
		super();
		this.code = code;
		this.numberOfTickets = numberOfTickets;
		this.orderInfo = orderInfo;
		this.flight = flight;
	}

	public BookedTickets() {
	}


	public String toString() {
		return this.flight.getCode() + " " + this.getNumberOfTickets();

	}
}
