package com.bookstore.service.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class OrderRepresentation extends AbstractRepresentation{
	

	public enum OrderStatus{Opened, Processing, Shipped, Delivered, Canceled, Invalid}
	public enum PaymentOption{CreditCard, DebitCard, GiftCard, PayPal, Cash}
	
	private String orderId;
	private String customerId;
	@XmlElementWrapper(name="orderDetails")
	private List<OrderDetailRepresentation> orderDetails;
	private OrderStatus orderState = OrderStatus.Opened;
	
	private PaymentOption paymentOption;	
	private double orderTotal;
	
	@XmlElement(name = "defaultBillingAddress")
	private AddressRepresentation defaultBillingAddress;
	
	@XmlElement(name = "defaultShippingAddress")
	private AddressRepresentation defaultShippingAddress;
	
	public OrderRepresentation(){
		this.orderDetails = new ArrayList<OrderDetailRepresentation>();
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<OrderDetailRepresentation> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailRepresentation> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public OrderStatus getOrderState() {
		return orderState;
	}
	public void setOrderState(OrderStatus orderState) {
		this.orderState = orderState;
	}

	public PaymentOption getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public AddressRepresentation getDefaultBillingAddress() {
		return defaultBillingAddress;
	}

	public void setDefaultBillingAddress(AddressRepresentation defaultBillingAddress) {
		this.defaultBillingAddress = defaultBillingAddress;
	}

	public AddressRepresentation getDefaultShippingAddress() {
		return defaultShippingAddress;
	}

	public void setDefaultShippingAddress(AddressRepresentation defaultShippingAddress) {
		this.defaultShippingAddress = defaultShippingAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
