package edu.flight.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.flight.dao.OrderInfoDAO;
import edu.flight.transport.ReportByCities;
import edu.flight.transport.ReportByDate;

@Named
@Transactional
public class AnalystService {
	@Inject
	private OrderInfoDAO orderInfoDAO;

	public List<ReportByDate> getReportByPeriod(Date start, Date end) {
		return orderInfoDAO.getReportByPeriod(
				start, end);
	}

	public List<ReportByCities> getReportByCities(Timestamp start, Timestamp end) {
		List<ReportByCities> report = orderInfoDAO
				.getReportByCities(start, end);
		return report;

	}

}
