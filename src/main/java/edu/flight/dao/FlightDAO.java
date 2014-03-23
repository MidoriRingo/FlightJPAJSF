package edu.flight.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.flight.entity.Flight;
import edu.flight.transport.FlightAmount;

@Repository
public class FlightDAO extends GenericDAO<Flight> {

	public List<Flight> resultBySearch(Timestamp start, Timestamp end,
			String city, int amount) {
		List<Flight> list = null;
		TypedQuery<Flight> query = em
				.createQuery(
						"SELECT f FROM Flight f where f.arrivalCity = ?1 and f.departureDateTime between ?2 and ?3 and f.amountOfTickets >= ?4",
						Flight.class);
		query.setParameter(1, city).setParameter(2, start).setParameter(3, end)
				.setParameter(4, amount);
		try {
			list = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		return list;
	}

	public List<Flight> shedule(){
		GregorianCalendar start = new GregorianCalendar();
        start.set(Calendar.HOUR, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        
        GregorianCalendar end = new GregorianCalendar();
        end.set(Calendar.HOUR, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
   
  		List<Flight> list = em.createQuery("select fl from Flight fl where fl.departureDateTime between ?1 and ?2", Flight.class).setParameter(1, new Timestamp(start.getTimeInMillis())).setParameter(2, new Timestamp(end.getTimeInMillis())).getResultList();
		return list;
	}

	public void removeUnpayed() {
		TypedQuery<FlightAmount> query = em
				.createQuery(
						"SELECT new edu.flight.transport.FlightAmount(b.flight.code, b.numberOfTickets) FROM BookedTickets b, Flight f, OrderInfo ord WHERE b.orderInfo.infoCode = ANY(SELECT ord.infoCode FROM OrderInfo ord WHERE ord.orderDateTime < ?1 AND ord.orderStatus = 0)",
						FlightAmount.class).setParameter(1, redDate());
		List<FlightAmount> list = query.getResultList();
		for (FlightAmount i : list) {
			em.createQuery(
					"UPDATE Flight f set f.amountOfTickets = f.amountOfTickets + ?1 WHERE f.code = ?2")
					.setParameter(1, i.getAmount())
					.setParameter(2, i.getFlightCode()).executeUpdate();
			em.createQuery(
					"DELETE FROM BookedTickets b WHERE b.flight.code = ?1")
					.setParameter(1, i.getFlightCode()).executeUpdate();
			em.createQuery(
					"DELETE FROM OrderInfo ord WHERE ord.orderDateTime < ?1 AND ord.orderStatus = 0")
					.setParameter(1, redDate()).executeUpdate();
		}

	}

	public static Timestamp redDate() {
		GregorianCalendar c = new GregorianCalendar();
		c.add(Calendar.HOUR_OF_DAY, -72);
		long dt = c.getTimeInMillis();
		return new Timestamp(dt);

	}

	public List<Flight> showFlights() {
		List<Flight> list = new ArrayList<Flight>();
		TypedQuery<Flight> query = em.createQuery("select f from Flight f order by f.departureDateTime DESC",
				Flight.class);
		try {
			list = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		return list;
	}
}
