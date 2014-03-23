package edu.flight.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;

import edu.flight.service.CustomerService;
import edu.flight.transport.BookedTicketsWeb;

@Named
@Scope("session")
public class BasketBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	CustomerService customerService;

	private double sumOrder = 0;
	private List<BookedTicketsWeb> list;

	public BasketBean() {
		list = new ArrayList<BookedTicketsWeb>();
	}

	public void addToBasket(BookedTicketsWeb ticket) {
		list.add(ticket);
	}

	public List<BookedTicketsWeb> getList() {
		return list;
	}

	public double getSumOrder() {
		sumOrder = countSumOrder();
		return sumOrder;
	}

	public double countSumOrder() {
		double retval = 0;
		for (BookedTicketsWeb b : list) {
			retval += b.getCount() * b.getTicketPrice();
		}
		retval = new BigDecimal(retval).setScale(2, RoundingMode.UP)
				.doubleValue();
		return retval;
	}
	
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Ticket Edited",((BookedTicketsWeb) event.getObject()).getFlightNo());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Ticket Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    } 
    
    public void removeTicket(BookedTicketsWeb ticket){
    	list.remove(ticket);
    }

}
