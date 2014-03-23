package edu.flight.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int infoCode;
	private Timestamp orderDateTime;
	private String name;
	private String mail;
	private String phone;
	private int orderStatus = 0;

	public OrderInfo() {
	}

	public OrderInfo(int infoCode, Timestamp orderDateTime, String clientName,
			String clientMail, String clientPhone, int orderStatus) {
		super();
		this.infoCode = infoCode;
		this.orderDateTime = orderDateTime;
		this.name = clientName;
		this.mail = clientMail;
		this.phone = clientPhone;
		this.orderStatus = orderStatus;
	}

	public int getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}

	public Timestamp getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Timestamp orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String clientName) {
		this.name = clientName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String clientMail) {
		this.mail = clientMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String clientPhone) {
		this.phone = clientPhone;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInfo other = (OrderInfo) obj;
		if (infoCode != other.infoCode)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderDateTime == null) {
			if (other.orderDateTime != null)
				return false;
		} else if (!orderDateTime.equals(other.orderDateTime))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}





}
