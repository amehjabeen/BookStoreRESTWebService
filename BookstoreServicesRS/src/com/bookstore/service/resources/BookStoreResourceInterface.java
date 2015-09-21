package com.bookstore.service.resources;

import javax.ws.rs.core.Response;

import com.bookstore.client.request.CustomerRequest;
import com.bookstore.client.request.OrderRequest;


public interface BookStoreResourceInterface {
	
	Response getCustomer(String id);
	
	Response addCustomer(CustomerRequest customerRequest);
	
	Response searchBook(String title, String author, String ISBN);
	
	Response searchBookById(String id);
		
	Response placeOrder(OrderRequest orderRequest);
			
	Response getOrder(String customerId, String orderId, boolean statusRequired);
		
	Response cancelOrder(String customerId, String orderId);
	
	Response authenticateCustomer(String username, String password);

}
