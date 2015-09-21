package com.bookstore.service.workflowActivities;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import com.bookstore.client.request.OrderDetailRequest;
import com.bookstore.client.request.OrderRequest;
import com.bookstore.dal.BookCollectionDAO;
import com.bookstore.dal.CustomerCollectionDAO;
import com.bookstore.domain.customer.Customer;
import com.bookstore.domain.item.Book;
import com.bookstore.domain.order.Order;
import com.bookstore.domain.order.OrderDetail;
import com.bookstore.service.representation.BookRepresentation;
import com.bookstore.service.representation.Constants;
import com.bookstore.service.representation.Link;
import com.bookstore.service.representation.OrderDetailRepresentation;
import com.bookstore.service.representation.OrderRepresentation;
import com.bookstore.service.representation.OrderRepresentation.OrderStatus;
import com.bookstore.service.representation.StringRepresentation;
import com.bookstore.service.resources.AddressUtil;

public class OrderActivity {
	
	CustomerCollectionDAO custDao = new CustomerCollectionDAO();
	
	public StringRepresentation placeOrder(OrderRequest orderRequest) {
		StringRepresentation sRepresentation = new StringRepresentation();
		String id = orderRequest.getCustomerId();
		Customer customer = custDao.getCustomer(id);
		if(customer == null){
			sRepresentation.setMessageString("Customer does not exist!");
			return sRepresentation;
		}
		Order order = new Order();
		order.setOrderId(orderRequest.getOrderId());
		BookCollectionDAO bookDAO = new BookCollectionDAO();
		for (OrderDetailRequest orderDetail : orderRequest.getOrderDetails())
		{
			String bookId = orderDetail.getBookRequest().getBookId();
			Book book = bookDAO.getBookByID(bookId);
			if(book != null)
			{
				order.addBook(book, orderDetail.getQuantity());
			}
			else{
				sRepresentation.setMessageString("No book found with Id: "+bookId);
				return sRepresentation;
			}

		}
		AddressUtil util = new AddressUtil();
		order.setbillingAddress(util.convertToAddress(orderRequest.getBillingAddress()));
		order.setShippingAddress(util.convertToAddress(orderRequest.getShippingAddress()));
		order.setOrderState(OrderStatus.Opened);
		order.setPaymentOption(orderRequest.getPaymentOption());
		String orderId = customer.addOrder(order);
		sRepresentation.setMessageString("OK");
		Link link = new Link();
		link.setMediaType(Constants.mediaType);
		link.setMethod(Constants.HttpGet);
		link.setUse("View all orders");
		link.setUrl(Constants.baseUri+"/customers/"+id+"/orders");
		sRepresentation.addLink(link);
		
		link = new Link();
		link.setMediaType(Constants.mediaType);
		link.setMethod(Constants.HttpGet);
		link.setUse("View order status");
		link.setUrl(Constants.baseUri+"/customers/"+id+"/orders/"+orderId+"?statusRequired=true");
		sRepresentation.addLink(link);
		return sRepresentation;
	}

	
	public List<OrderRepresentation> viewAllOrders(@PathParam("customerId")String customerId) {
		Customer customer = custDao.getCustomer(customerId);
		if(customer != null)
		{
			List<Order> orders = customer.getOrders();
			List<OrderRepresentation>  orderRepresentations = new ArrayList<OrderRepresentation>();
			for (Order order : orders) 
			{
				if(order!=null)
				{
					OrderRepresentation orderRep = new OrderRepresentation();

					orderRep.setCustomerId(customerId);
					String orderId = order.getOrderId();
					orderRep.setOrderId(orderId);
					orderRep.setOrderTotal(order.getOrderTotal());
					Link link = new Link();
					link.setMediaType(Constants.mediaType);
					link.setMethod(Constants.HttpGet);
					link.setUse("View complete order");
					link.setUrl(Constants.baseUri+"/customers/"+customerId+"/orders/"+orderId);
					orderRep.addLink(link);
					orderRepresentations.add(orderRep);
				}
			}
			return orderRepresentations;
		}
		return null;		
	}
	
	
	public OrderRepresentation viewCompleteOrder(String customerId, String orderId){
		Customer customer = custDao.getCustomer(customerId);
		if(customer != null)
		{
			List<Order> orders = customer.getOrders();
			for (Order order : orders) 
			{
				if(order!=null && order.getOrderId()!=null && order.getOrderId().equals(orderId))
				{
					OrderRepresentation orderRep = new OrderRepresentation();
					List<OrderDetailRepresentation> orderRepList = new ArrayList<OrderDetailRepresentation>();
					List<OrderDetail> ordDetailList = order.getOrderDetails();
					for (OrderDetail orD: ordDetailList){
						OrderDetailRepresentation ordDetails = new OrderDetailRepresentation();
						BookRepresentation bRep = new BookRepresentation();
						Book b = orD.getBook();
						bRep.setAuthor(b.getAuthor());
						bRep.setBookId(b.getId());
						bRep.setISBN(b.getISBN());
						bRep.setPrice(b.getPrice());
						bRep.setTitle(b.getTitle());
						
						Link link = new Link();
						link.setMethod(Constants.HttpGet);
						link.setMediaType(Constants.mediaType);
						link.setUse("View all the details of the book");
						link.setUrl(Constants.baseUri + "/books?title="+b.getTitle()+"&author="+b.getAuthor()+"&ISBN="+b.getISBN());
						bRep.addLink(link);
						
						ordDetails.setBook(bRep);
						ordDetails.setQuantity(orD.getQuantity());
						orderRepList.add(ordDetails);
					}
					orderRep.setCustomerId(customerId);
					orderRep.setOrderDetails(orderRepList);
					orderRep.setOrderTotal(order.getOrderTotal());
					AddressUtil util = new AddressUtil();
					orderRep.setDefaultShippingAddress(util.convertToAddressRepresentation(order.getShippingAddress()));
					orderRep.setDefaultBillingAddress(util.convertToAddressRepresentation(order.getbillingAddress()));
					orderRep.setOrderState(order.getOrderState());
					Link link = new Link();
					link.setMediaType(Constants.mediaType);
					link.setMethod(Constants.HttpDelete);
					link.setUse("Cancel Order");
					link.setUrl(Constants.baseUri+"/customers/"+customerId+"/orders/"+orderId);
					orderRep.addLink(link);
					return orderRep;
				}
			}
		}
		return null;
	}

	public StringRepresentation cancelOrder(String customerId, String orderId) {
		Customer customer = custDao.getCustomer(customerId);
		try
		{
			if(customer != null)
			{
				List<Order> orders = customer.getOrders();
				for (Order order : orders) 
				{
					if(order.getOrderId().equals(orderId))
					{
						System.out.println("Previous OrderState: "+order.getOrderState().toString());
						customer.removeOrder(order);
						order.cancelOrder();
						customer.addOrder(order);
						System.out.println("OrderState after cancel: "+order.getOrderState().toString());
						StringRepresentation sRepresentation = new StringRepresentation();
						sRepresentation.setMessageString("OK");
						Link link = new Link();
						link.setMediaType(Constants.mediaType);
						link.setMethod(Constants.HttpGet);
						link.setUse("View all orders");
						link.setUrl(Constants.baseUri+"/customers/"+customerId+"/orders");
						sRepresentation.addLink(link);
						return sRepresentation;
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			StringRepresentation sRepresentation = new StringRepresentation();
			sRepresentation.setMessageString("Server error");
			return sRepresentation;

		}
		StringRepresentation sRepresentation = new StringRepresentation();
		sRepresentation.setMessageString("FAILED");
		return sRepresentation;
	}


	public StringRepresentation getOrderStatus(String customerId, String orderId) {
		Customer customer = custDao.getCustomer(customerId);
		if(customer != null)
		{
			List<Order> orders = customer.getOrders();
			for (Order order : orders) 
			{
				if(order!=null && order.getOrderId()!=null && order.getOrderId().equals(orderId))
				{
					StringRepresentation sRepresentation = new StringRepresentation();
					sRepresentation.setMessageString(order.getOrderState().toString());
					Link link = new Link();
					link.setMediaType(Constants.mediaType);
					link.setMethod(Constants.HttpDelete);
					link.setUse("Cancel Order");
					link.setUrl(Constants.baseUri+"/customers/"+customerId+"/orders/"+orderId);
					sRepresentation.addLink(link);
					
					link = new Link();
					link.setMediaType(Constants.mediaType);
					link.setMethod(Constants.HttpGet);
					link.setUse("View complete order");
					link.setUrl(Constants.baseUri+"/customers/"+customerId+"/orders/"+orderId);
					sRepresentation.addLink(link);
					return sRepresentation;
				}
			}
		}
		return null;
	}
}
