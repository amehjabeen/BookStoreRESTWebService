package com.bookstore.service.resources;

import com.bookstore.domain.customer.Address;
import com.bookstore.service.representation.AddressRepresentation;

public class AddressUtil {

	public Address convertToAddress(AddressRepresentation addressRepresentation ){
		Address address = new Address();
		address.setAddressId(addressRepresentation.getAddressId());
		address.setCity(addressRepresentation.getCity());
		address.setState(addressRepresentation.getState());
		address.setStreet(addressRepresentation.getStreet());
		address.setZip(addressRepresentation.getZip());
		return address;
	}
	
	public AddressRepresentation convertToAddressRepresentation(Address address){
		AddressRepresentation addressRepresentation = new AddressRepresentation();
		addressRepresentation.setAddressId(address.getAddressId());
		addressRepresentation.setCity(address.getCity());
		addressRepresentation.setState(address.getState());
		addressRepresentation.setStreet(address.getStreet());
		addressRepresentation.setZip(address.getZip());
		return addressRepresentation;
	}
}
