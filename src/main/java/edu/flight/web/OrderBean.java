package edu.flight.web;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.flight.entity.OrderInfo;
import edu.flight.service.AccountantService;
import edu.flight.service.BusinessAdministratorService;
import edu.flight.service.CustomerService;

@Named
@Scope("session")
public class OrderBean {

	@Inject
	private CustomerService customerService;
	@Inject
	private AccountantService accountantService;
	@Inject
	private BasketBean basketBean;
	@Inject
	BusinessAdministratorService businessAdministratorService;

	private int infoCode;
	private Date orderDateTime;
	private String clientName;
	private String clientMail;
	private String clientPhone;
	private List<OrderInfo> orders;
	private OrderInfo selectedOrder;
	private OrderDataModel orderModel;

	public List<OrderInfo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderInfo> orders) {
		this.orders = orders;
	}

	public OrderInfo getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(OrderInfo selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public OrderDataModel getOrderModel() {
		return orderModel;
	}

	public int getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMail() {
		return clientMail;
	}

	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public OrderBean() {
		
	}

	public List<OrderInfo> getUnpayed() {
		return accountantService.getUnpayed();
	}

	public String placeOrder() {
		
		OrderInfo ord = new OrderInfo();
		ord.setMail(clientMail);
		ord.setName(clientName);
		ord.setPhone(clientPhone);
		customerService.placeOrder(ord, basketBean.getList());
		return "final.xhtml";
	}

	public void deleteUnpayed() {
		businessAdministratorService.removeUnpayed();
	}
	
	public OrderDataModel getAllUnpayed() {
		return new OrderDataModel(accountantService.getUnpayed());
		
	}
	
	public void setPayed() {
		accountantService.setPayedStatus(selectedOrder.getInfoCode());
	}

	public String enterDetails(){
		if (basketBean.getList().isEmpty()){
			return "basket.xhtml";
		}
			else
			return "clientInfo.xhtml";
		}
}