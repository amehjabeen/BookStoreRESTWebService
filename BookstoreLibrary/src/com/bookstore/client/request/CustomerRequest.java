package com.bookstore.client.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bookstore.service.representation.AddressRepresentation;

@XmlRootElement(name = "CustomerRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRequest {
	private String firstName;
	private String lastName;
	
	@XmlElement(name = "defaultBillingAddress")
	private AddressRepresentation defaultBillingAddress;
	
	@XmlElement(name = "defaultShippingAddress")
	private AddressRepresentation defaultShippingAddress;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
