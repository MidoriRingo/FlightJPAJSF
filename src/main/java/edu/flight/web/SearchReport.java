package edu.flight.web;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.flight.service.AnalystService;
import edu.flight.transport.ReportByCities;
import edu.flight.transport.ReportByDate;

@Named
@Scope("session")
public class SearchReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private String city;
	
	@Inject
	private AnalystService analystService;
	
	private List<ReportByCities> cityReport;
	private List<ReportByDate> periodReport;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String selectReport(){
		if (city.equals("1"))
			return "reportByCities.xhtml";
		else
			return "reportByPeriod.xhtml";
	}
	
	public List<ReportByDate> getPeriodReport() {
		periodReport = analystService.getReportByPeriod(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
		return periodReport;
	}
	
	public List<ReportByCities> getCityReport() {
		cityReport = analystService.getReportByCities(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()));
		return cityReport;
	}
	public void setCityReport(List<ReportByCities> cityReport) {
		this.cityReport = cityReport;
	}
}
