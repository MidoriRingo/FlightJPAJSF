package edu.flight.service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.flight.dao.BookedTicketsDAO;
import edu.flight.dao.FlightDAO;
import edu.flight.dao.OrderInfoDAO;
import edu.flight.entity.BookedTickets;
import edu.flight.entity.Flight;
import edu.flight.entity.OrderInfo;
import edu.flight.transport.BookedTicketsWeb;

@Named
@Transactional
public class CustomerService {

	@Inject
	OrderInfoDAO orderInfoDAO;
	@Inject
	BookedTicketsDAO bookedTicketsDAO;
	@Inject
	FlightDAO flightDAO;
	

	public void placeOrder(OrderInfo ord, List<BookedTicketsWeb> tickets) {
		ord.setOrderDateTime(new Timestamp(new GregorianCalendar().getTimeInMillis()));
		orderInfoDAO.create(ord);
		List<BookedTickets> list = new ArrayList<BookedTickets>();
		for (BookedTicketsWeb ticket : tickets) {
			BookedTickets bk = new BookedTickets();
			bk.setFlight(flightDAO.readByID((int)ticket.getFlightId()));
			bk.setNumberOfTickets(ticket.getCount());
			bk.setOrderInfo(ord);
			list.add(bk);
			bookedTicketsDAO.create(bk);
		}
		orderInfoDAO.changeCount(list);
	}

	public List<Flight> findFlights(Date start, Date end, String city, int amount) {
		GregorianCalendar endC = new GregorianCalendar();
		endC.setTime(end);
        endC.set(Calendar.HOUR, 23);
        endC.set(Calendar.MINUTE, 59);
        endC.set(Calendar.SECOND, 59);
		return flightDAO.resultBySearch(new Timestamp(start.getTime()),  new Timestamp(endC.getTimeInMillis()), city, amount);

	}

	public List<BookedTickets> getTicketList(OrderInfo order) {
		return orderInfoDAO.getTickets(order);

	}

	public List<Flight> getShedule() {
		flightDAO.shedule();
		return null;
	}

}
