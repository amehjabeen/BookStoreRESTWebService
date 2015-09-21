package com.bookstore.client.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bookstore.service.representation.AddressRepresentation;
import com.bookstore.service.representation.OrderRepresentation.PaymentOption;

@XmlRootElement(name = "OrderRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	private String customerId;
	private String orderId;
	@XmlElementWrapper(name="orderDetails")
	private List<OrderDetailRequest> orderDetails = new ArrayList<OrderDetailRequest>();
	@XmlElement(name = "shippingAddress")
	private AddressRepresentation shippingAddress;
	@XmlElement(name = "billingAddress")
	private AddressRepresentation billingAddress;
	private PaymentOption paymentOption;	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public List<OrderDetailRequest> getOrderDetails()
	{
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailRequest> orderDetails)
	{
		this.orderDetails = orderDetails;
	}
	
	public AddressRepresentation getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressRepresentation shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public AddressRepresentation getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressRepresentation billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public PaymentOption getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}
}
