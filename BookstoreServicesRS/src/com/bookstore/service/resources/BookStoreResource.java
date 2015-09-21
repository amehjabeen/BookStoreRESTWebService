package com.bookstore.service.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.bookstore.client.request.CustomerRequest;
import com.bookstore.client.request.OrderRequest;
import com.bookstore.domain.customer.Address;
import com.bookstore.service.representation.BookRepresentation;
import com.bookstore.service.representation.CustomerRepresentation;
import com.bookstore.service.representation.OrderRepresentation;
import com.bookstore.service.representation.StringRepresentation;
import com.bookstore.service.workflowActivities.BookActivity;
import com.bookstore.service.workflowActivities.CustomerActivity;
import com.bookstore.service.workflowActivities.OrderActivity;

@Path("/bookstoreservice/v1.0/")
public class BookStoreResource implements BookStoreResourceInterface {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}")
	public Response getCustomer(@PathParam("customerId")String id) {
		System.out.println("GET METHOD Request from Client.Get customer with customer Id = ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		CustomerRepresentation custRep = customerActivity.getCustomer(id);
		if(custRep != null){
			return Response.status(Response.Status.OK).entity(custRep).build();
		}
		else{
			throw new CustomWebException("Customer does not exist");
		}
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customers")
	public Response addCustomer(CustomerRequest customerRequest) {
		if(customerRequest == null){
			System.out.println("POST METHOD Request from Client with empty request..Throwing Exception");
			throw new CustomWebException("The customer information is invalid");
		}
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity customerActivity = new CustomerActivity();
		AddressUtil util = new AddressUtil();
		Address billingAddress = util.convertToAddress(customerRequest.getDefaultBillingAddress());		
		Address shippingAddress = util.convertToAddress(customerRequest.getDefaultShippingAddress());
		
		StringRepresentation res = customerActivity.addCustomer(customerRequest.getFirstName(),customerRequest.getLastName(),billingAddress,shippingAddress);
		if (res.getMessageString().equals("OK")) {
			return Response.status(Response.Status.OK).entity(res).build();
		}
		else{
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/books")
	public Response searchBook(@QueryParam("title")@DefaultValue("") String title,
			@QueryParam("author")@DefaultValue("") String author,
			@QueryParam("ISBN")@DefaultValue("") String ISBN) {
		System.out.println("GET METHOD Request from Client with .............title = "+title+"...ISBN = "+ISBN+"...author = "+author);
		BookActivity bookstoreActivity = new BookActivity();
		List<BookRepresentation> bookRepList =  bookstoreActivity.searchBook(title, author, ISBN);	
		if(bookRepList != null){
			return Response.status(Response.Status.OK).entity(bookRepList).build();
		}
		else{
			throw new CustomWebException("The book you are trying to search is not found.");
		}
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/books/{id}")
	public Response searchBookById(@PathParam("id")@DefaultValue("") String id) {
		System.out.println("GET METHOD Request from Client with book id........."+id);
		BookActivity bookActivity = new BookActivity();
		BookRepresentation bookRep =  bookActivity.searchBookById(id);	
		if(bookRep != null){
			return Response.status(Response.Status.OK).entity(bookRep).build();
		}
		else{
			throw new CustomWebException("The book you are trying to search is not found.");
		}
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}/orders")
	public Response placeOrder(OrderRequest orderRequest) {
		if(orderRequest == null){
			throw new CustomWebException("The order is invalid!");
		}
		System.out.println("POST METHOD Request from Client with  customer id....." + orderRequest.getCustomerId());
		OrderActivity orderActivity = new OrderActivity();
		StringRepresentation res = orderActivity.placeOrder(orderRequest);
		if (res.getMessageString().equals("OK")) {
			return Response.status(Response.Status.OK).entity(res).build();
		}
		else{
			throw new CustomWebException(res.getMessageString());
		}
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}/orders")
	public Response viewAllOrders(@PathParam("customerId")String customerId) {
		OrderActivity orderActivity = new OrderActivity();
		System.out.println("GET METHOD Request from Client with ............customer Id = "+customerId);
		List<OrderRepresentation> ordList = orderActivity.viewAllOrders(customerId);
		if(ordList != null){
			return Response.status(Response.Status.OK).entity(ordList).build();
		}
		else{
			throw new CustomWebException("No orders found for the customer.");
		}
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}/orders/{orderId}")
	public Response getOrder(@PathParam("customerId")String customerId, @PathParam("orderId")String orderId ,
			@QueryParam("statusRequired")@DefaultValue("false") boolean statusRequired) {
		OrderActivity orderActivity = new OrderActivity();
		System.out.println("GET METHOD Request from Client with ............customer Id = "+customerId+"...orderId = "+orderId);
		if(statusRequired == true){
			StringRepresentation sRepresentation = orderActivity.getOrderStatus(customerId, orderId);
			if(sRepresentation == null){
				throw new CustomWebException("Request failed. Please check your customerId and OrderId again.");
			}
			else{
				return Response.status(Response.Status.OK).entity(sRepresentation).build();
			}
		}
		else{
			OrderRepresentation orderRep = orderActivity.viewCompleteOrder(customerId, orderId);
			if(orderRep == null){
				throw new CustomWebException("Request failed. Please check your customerId and OrderId again.");
			}
			else{
				return Response.status(Response.Status.OK).entity(orderRep).build();
			}
		}
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}/orders/{orderId}")
	public Response cancelOrder(@PathParam("customerId")String customerId, @PathParam("orderId")String orderId) {
		System.out.println("DELETE METHOD Request from Client with  customer id....." + customerId+"...order id= "+orderId);
		OrderActivity orderActivity = new OrderActivity();
		StringRepresentation res = orderActivity.cancelOrder(customerId, orderId);
		if (res.getMessageString().equals("OK")) {
			return Response.status(Response.Status.OK).entity(res).build();
		}
		else if(res.getMessageString().equals("FAILED")){
			throw new CustomWebException("The order you are trying to delete is not found");
		}
		else{
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers")
	public Response authenticateCustomer(@QueryParam("username")@DefaultValue("")String username, 
			@QueryParam("password")@DefaultValue("")String password) {
		CustomerActivity customerActivity = new CustomerActivity();
		CustomerRepresentation custRep = customerActivity.authenticateCustomer(username, password);
		if(custRep != null){
			return Response.status(Response.Status.OK).entity(custRep).build();
		}
		else{
			throw new CustomWebException("Invalid customer");
		}
	}
}
