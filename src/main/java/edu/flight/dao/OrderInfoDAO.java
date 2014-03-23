package edu.flight.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.flight.entity.BookedTickets;
import edu.flight.entity.OrderInfo;
import edu.flight.transport.FlightAmount;
import edu.flight.transport.ReportByCities;
import edu.flight.transport.ReportByDate;
import edu.flight.dao.GenericDAO;

@Repository
public class OrderInfoDAO extends GenericDAO<OrderInfo> {
	
	public void changeCount(List<BookedTickets> list) {
		for (BookedTickets b : list) {
			em.createQuery(
					"UPDATE Flight f SET f.amountOfTickets = f.amountOfTickets - ?1 WHERE f.code = ?2")
					.setParameter(1, b.getNumberOfTickets())
					.setParameter(2, b.getFlight().getCode()).executeUpdate();
		}
	}
	
	public List<BookedTickets> getTickets(OrderInfo order) {
		int number = order.getInfoCode();
		List<BookedTickets> list = em
				.createQuery(
						"select b from BookedTickets b where b.orderinfo.infocode = ?1",
						BookedTickets.class).setParameter(1, number)
				.getResultList();
		return list;
	}


	// get all orders for
	public List<OrderInfo> showOrders() {
		TypedQuery<OrderInfo> query = em.createQuery(
				"SELECT ord FROM OrderInfo ord", OrderInfo.class);
		List<OrderInfo> list = query.getResultList();
		return list;
	}

	// And here
	public List<ReportByDate> getReportByPeriod(Date start, Date end) {
		List<ReportByDate> list = em
				.createQuery(
						"select new edu.flight.transport.ReportByDate(report.orderTime, report.orderCount, report.orderSum) from ReportData report where orderTime between ?1 and ?2",
						ReportByDate.class).setParameter(1, start)
				.setParameter(2, end).getResultList();
		for (ReportByDate report : list){
			System.out.println(report.getOrderSum());
		}
		return list;
	}

	public List<ReportByCities> getReportByCities(Timestamp start, Timestamp end) {
		List<ReportByCities> report = em
				.createQuery(
						"select new edu.flight.transport.ReportByCities(fl.arrivalCity, sum(b.numberOfTickets), sum(b.numberOfTickets*fl.ticketPrice)) "
								+ "from Flight fl, BookedTickets b, OrderInfo ord "
								+ "where b.flight.code = fl.code and b.orderInfo.infoCode = ord.infoCode and ord.orderStatus = 1 "
								+ "and ord.orderDateTime between ?1 and ?2 "
								+ "group by fl.arrivalCity",
						ReportByCities.class).setParameter(1, start).setParameter(2, end)
				.getResultList();
		return report;
	}
	
	// unpayed remover for admin
	public void removeUnpayed() {
		TypedQuery<FlightAmount> query = em
				.createQuery(
						"SELECT new edu.flight.transport.FlightAmount(b.flight.code, b.numberOfTickets) FROM BookedTickets b, Flight f, OrderInfo ord WHERE b.orderInfo.infoCode = ANY(SELECT ord.infoCode FROM OrderInfo ord WHERE ord.orderDateTime < ?1 AND ord.orderStatus = 'false')",
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
		}
		em.createQuery(
				"DELETE FROM OrderInfo ord WHERE ord.orderDateTime < ?1 AND ord.orderStatus = 'false'")
				.setParameter(1, redDate()).executeUpdate();
	}

	// just a functional func
	public static Date redDate() {
		GregorianCalendar c = new GregorianCalendar();
		c.add(Calendar.DAY_OF_YEAR, -3);
		java.util.Date dt = c.getTime();
		return (new java.sql.Date(dt.getTime()));

	}

	public List<OrderInfo> getUnpayed() {
		return em.createQuery("select ord from OrderInfo ord where ord.orderStatus = 0", OrderInfo.class).getResultList();
	}

}