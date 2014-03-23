package edu.flight.web;

import java.util.List;

import org.primefaces.model.SelectableDataModel;

import edu.flight.entity.OrderInfo;

import javax.faces.model.ListDataModel;

public class OrderDataModel extends ListDataModel<OrderInfo> implements
		SelectableDataModel<OrderInfo> {

	public OrderDataModel() {
	}

	public OrderDataModel(List<OrderInfo> orders) {
		super(orders);
	}

	public OrderInfo getRowData(String rowKey) {
		List<OrderInfo> orders = (List<OrderInfo>) getWrappedData();

		for (OrderInfo order : orders) {
			if (String.valueOf(order.getInfoCode()).equals(rowKey))
				return order;
		}

		return null;
	}

	public Object getRowKey(OrderInfo order) {
		return order.getInfoCode();
	}
}