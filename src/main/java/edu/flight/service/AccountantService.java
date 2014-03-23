package edu.flight.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.flight.dao.OrderInfoDAO;
import edu.flight.entity.OrderInfo;

@Named
@Transactional
public class AccountantService {
	@Inject
	OrderInfoDAO orderInfoDAO;

	public List<OrderInfo> showOrders() {
		return orderInfoDAO.showOrders();
	}

	public void setPayedStatus(int infoCode) {
		OrderInfo order = orderInfoDAO.readByID(infoCode);
		order.setOrderStatus(1);
		orderInfoDAO.update(order);
	}


	public List<OrderInfo> getUnpayed() {
		return orderInfoDAO.getUnpayed();

	}

}
