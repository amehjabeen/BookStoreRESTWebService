package com.bookstore.service.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CustomerRepresentation extends AbstractRepresentation{
	
	private String customerId;
	private String lastName;
	private String firstName;
	
	@XmlElement(name = "defaultBillingAddress")
	private AddressRepresentation defaultBillingAddress;
	
	@XmlElement(name = "defaultShippingAddress")
	private AddressRepresentation defaultShippingAddress;
	
	@XmlElementWrapper(name="orders")
	private List<OrderRepresentation> orders;

	public List<OrderRepresentation> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderRepresentation> orders) {
		this.orders = orders;
	}
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

}
