package com.bookstore.service.workflowActivities;

import com.bookstore.dal.CustomerCollectionDAO;
import com.bookstore.domain.customer.Address;
import com.bookstore.domain.customer.Customer;
import com.bookstore.service.representation.Constants;
import com.bookstore.service.representation.CustomerRepresentation;
import com.bookstore.service.representation.Link;
import com.bookstore.service.representation.StringRepresentation;
import com.bookstore.service.resources.AddressUtil;

public class CustomerActivity {
	CustomerCollectionDAO custDao = new CustomerCollectionDAO();
	
	public CustomerRepresentation getCustomer(String id) {
		Customer customer = custDao.getCustomer(id);

		if (customer != null){
			CustomerRepresentation custRep = new CustomerRepresentation();
			custRep.setCustomerId(id);
			custRep.setFirstName(customer.getFirstName());
			custRep.setLastName(customer.getLastName());
			AddressUtil u = new AddressUtil();
			custRep.setDefaultBillingAddress(u.convertToAddressRepresentation(customer.getDefaultBillingAddress()));
			custRep.setDefaultShippingAddress(u.convertToAddressRepresentation(customer.getDefaultShippingAddress()));
		
			Link link = new Link();
			link.setMediaType(Constants.mediaType);
			link.setMethod(Constants.HttpGet);
			link.setUse("View all orders");
			link.setUrl(Constants.baseUri+"/customers/"+id+"/orders");
			
			custRep.addLink(link);
			
			return custRep;	
		}
		else
			return null;
	}
	
	public CustomerRepresentation authenticateCustomer(String username, String password){
		if(!username.trim().equals("") && !password.trim().equals("")){
			Customer customer = custDao.authenticateCustomer(username, password);
			CustomerRepresentation customerRepresentation = new CustomerRepresentation();
			String id = customer.getCustomerId();
			customerRepresentation.setCustomerId(id);
			customerRepresentation.setFirstName(customer.getFirstName());
			customerRepresentation.setLastName(customer.getLastName());
			Link link = new Link();
			link.setMediaType(Constants.mediaType);
			link.setMethod(Constants.HttpGet);
			link.setUse("View all orders");
			link.setUrl(Constants.baseUri+"/customers/"+id+"/orders");			
			customerRepresentation.addLink(link);
			
			link = new Link();
			link.setMediaType(Constants.mediaType);
			link.setMethod(Constants.HttpGet);
			link.setUse("View the customer's detailed information");
			link.setUrl(Constants.baseUri+"/customers/"+id);
			customerRepresentation.addLink(link);
			return customerRepresentation;			
		}
		else{
			return null;
		}
	}

	public StringRepresentation addCustomer(String firstName, String lastName, Address billingAddress, Address shippingAddress)
	{
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setDefaultBillingAddress(billingAddress);
		customer.setDefaultShippingAddress(shippingAddress);
		StringRepresentation sRepresentation = new StringRepresentation();
		try{
			custDao.addCustomer(customer);			
			sRepresentation.setMessageString("OK");
			
			Link link = new Link();
			link.setMediaType(Constants.mediaType);
			link.setMethod(Constants.HttpGet);
			link.setUse("Search all books");
			link.setUrl(Constants.baseUri+"/books");			
			sRepresentation.addLink(link);			
			return sRepresentation;
		}
		catch(Exception e){
			sRepresentation.setMessageString("Server error");
			return sRepresentation;
		}
	}
}
