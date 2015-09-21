package com.bookstore.domain.order;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bookstore.domain.customer.Address;
import com.bookstore.domain.item.Book;
import com.bookstore.service.representation.OrderRepresentation.OrderStatus;
import com.bookstore.service.representation.OrderRepresentation.PaymentOption;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 3L;

	private String orderId;
	@XmlElementWrapper(name="OrderDetails")
	@XmlElement(name="OrderDetail")
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	private OrderStatus orderState = OrderStatus.Opened;
	private PaymentOption paymentOption;
	private Address billingAddress;
	private Address shippingAddress;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public void setOrderState(OrderStatus orderState) {
		this.orderState = orderState;
	}
	
	public OrderStatus getOrderState() {
		return orderState;
	}
	
	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}
	
	public PaymentOption getPaymentOption() {
		return paymentOption;
	}
	
	public Address getbillingAddress() {
		return billingAddress;
	}

	public void setbillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public void addBook(Book book, int quantity) {
		if (orderState == OrderStatus.Opened) {
			orderDetails.add(new OrderDetail(book, quantity));
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
	}
	
	public void cancelOrder() {
		if (orderState == OrderStatus.Opened || orderState == OrderStatus.Processing) {
			orderState = OrderStatus.Canceled;
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public double getOrderTotal() {
		double total = 0.00;
		for (OrderDetail line : orderDetails) {
			total += line.getBook().getPrice() * line.getQuantity();
		}
		return total;
	}

}
