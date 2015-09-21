package com.bookstore.domain.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.domain.order.Order;

public class Customer implements Serializable {
	private static final long serialVersionUID = 2L;
	private String customerId;
	private String lastName;
	private String firstName;
	private Address defaultBillingAddress;
	private Address defaultShippingAddress;
	private String username;
	private String password;
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Address getDefaultBillingAddress() {
		return defaultBillingAddress;
	}

	public void setDefaultBillingAddress(Address billingAddress) {
		this.defaultBillingAddress = billingAddress;
	}

	public Address getDefaultShippingAddress() {
		return defaultShippingAddress;
	}

	public void setDefaultShippingAddress(Address shippingAddress) {
		this.defaultShippingAddress = shippingAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String id) {
		this.customerId = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String addOrder(Order order) {
		orders.add(order);
		return "1234";
	}
	
	public void removeOrder(Order order){
		orders.remove(order);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
