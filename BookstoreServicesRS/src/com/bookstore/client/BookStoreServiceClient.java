package com.bookstore.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.WebApplicationException;
import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.bookstore.client.request.BookRequest;
import com.bookstore.client.request.CustomerRequest;
import com.bookstore.client.request.OrderDetailRequest;
import com.bookstore.client.request.OrderRequest;
import com.bookstore.domain.customer.Address;
import com.bookstore.service.representation.AddressRepresentation;
import com.bookstore.service.representation.BookRepresentation;
import com.bookstore.service.representation.CustomerRepresentation;
import com.bookstore.service.representation.OrderDetailRepresentation;
import com.bookstore.service.representation.OrderRepresentation;
import com.bookstore.service.representation.OrderRepresentation.PaymentOption;
import com.bookstore.service.resources.AddressUtil;

public final class BookStoreServiceClient {

	private BookStoreServiceClient() {
	} 

	public static void main(String args[]) throws Exception {

		List<Object> providers = new ArrayList<Object>();
		JacksonJsonProvider provider = new JacksonJsonProvider();
		provider.addUntouchable(Response.class);
		providers.add(provider);

		/*****************************************************************************************
		 * GET METHOD invoke
		 *****************************************************************************************/
		System.out.println("GET METHOD - Get Client.........................................................");
		WebClient getClient = WebClient.create("http://localhost:8081", providers);

		//Configuring the CXF logging interceptor for the outgoing message
		WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
		//Configuring the CXF logging interceptor for the incoming response
		WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to get in xml format
		getClient = getClient.accept("application/xml").type("application/json").path("/bookstoreservice/v1.0/customers/12345");

		//The following lines are to show how to log messages without the CXF interceptors
		String getRequestURI = getClient.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
		String getRequestHeaders = getClient.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);

		//to see as raw XML/json
		String response = null;
		try{
			//to get the response as object of Employee class
			CustomerRepresentation customer = getClient.get(CustomerRepresentation.class);
			System.out.println("CustomerId: "+customer.getCustomerId());
			System.out.println("First Name:" + customer.getFirstName());
			System.out.println("Last Name:" + customer.getLastName());
			AddressRepresentation address = customer.getDefaultBillingAddress();
			System.out.println("Address - Street: "+address.getStreet()+", City: "+address.getCity()+
					", State: "+address.getState()+ ", Zip: "+address.getZip());            
		}catch(BadRequestException e){
			System.out.println("The request made cannot be fullfilled by the WebService. Please check your request and try again.");
			System.out.println("Status: "+e.getResponse().getStatus());
		}catch(NotAllowedException e){
        	System.out.println("The resource at the given URI cannot be accessed. Please enter a valid URI");
        	System.out.println("Status: 405 Resource cannot be accessed");
        }
		System.out.println("GET METHOD Response: ...." + response);


		/*****************************************************************************************
		 * GET METHOD invoke - Search particular book
		 *****************************************************************************************/
		System.out.println("GET METHOD - Search a particular book.........................................................");
		WebClient getClient2 = WebClient.create("http://localhost:8081", providers);

		//Configuring the CXF logging interceptor for the outgoing message
		WebClient.getConfig(getClient2).getOutInterceptors().add(new LoggingOutInterceptor());
		//Configuring the CXF logging interceptor for the incoming response
		WebClient.getConfig(getClient2).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to get in xml format
		getClient2 = getClient2.accept("application/json").type("application/json").path("/bookstoreservice/v1.0/books").query("title", "Title11111");

		//The following lines are to show how to log messages without the CXF interceptors
		getRequestURI = getClient2.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
		getRequestHeaders = getClient2.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);

		//to see as raw XML/json
		try{
			//to get the response as collection of BookRepresentation class
			Collection<? extends BookRepresentation> bookRepList = getClient2.getCollection(BookRepresentation.class);
			for(BookRepresentation bookRep: bookRepList){
				System.out.println("Title: "+bookRep.getTitle()+
						", Author: "+bookRep.getAuthor()+
						", ISBN: "+bookRep.getISBN()+
						", Price: "+bookRep.getPrice());
			}
		}catch(BadRequestException e){
			System.out.println("The request made cannot be fullfilled by the WebService. Please check your request and try again.");
			System.out.println("Status: "+e.getResponse().getStatus());
		}catch(NotAllowedException e){
        	System.out.println("The resource at the given URI cannot be accessed. Please enter a valid URI");
        	System.out.println("Status: 405 Resource cannot be accessed");
        }

		/*****************************************************************************************
		 * GET METHOD invoke - SEARCH ALL BOOKS
		 *****************************************************************************************/
		System.out.println("GET METHOD - Search all books.........................................................");
		WebClient getClient3 = WebClient.create("http://localhost:8081", providers);

		//Configuring the CXF logging interceptor for the outgoing message
		WebClient.getConfig(getClient3).getOutInterceptors().add(new LoggingOutInterceptor());
		//Configuring the CXF logging interceptor for the incoming response
		WebClient.getConfig(getClient3).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to get in xml format
		getClient3 = getClient3.accept("application/json").type("application/json").path("/bookstoreservice/v1.0/books");

		//The following lines are to show how to log messages without the CXF interceptors
		getRequestURI = getClient3.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
		getRequestHeaders = getClient3.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);

		//to see as raw XML/json
		try{
			//to get the response as collection of BookRepresentation class
			Collection<? extends BookRepresentation> bookRepList = getClient3.getCollection(BookRepresentation.class);
			if(bookRepList != null && !(bookRepList.isEmpty())){
				for(BookRepresentation bookRep: bookRepList){
					System.out.println("Title: "+bookRep.getTitle()+
							", Author: "+bookRep.getAuthor()+
							", ISBN: "+bookRep.getISBN()+
							", Price: "+bookRep.getPrice());
				}
			}
		}catch(BadRequestException e){
			System.out.println("The request made cannot be fullfilled by the WebService. Please check your request and try again.");
			System.out.println("Status: "+e.getResponse().getStatus());
		}catch(NotAllowedException e){
        	System.out.println("The resource at the given URI cannot be accessed. Please enter a valid URI");
        	System.out.println("Status: 405 Resource cannot be accessed");
        }

		/*****************************************************************************************
		 * POST METHOD invoke
		 *****************************************************************************************/
		System.out.println("POST METHOD - add customer.........................................................");
		WebClient postClient = WebClient.create("http://localhost:8081", providers);
		WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to application/json get in json format
		postClient = postClient.accept("application/xml").type("application/json").path("/bookstoreservice/v1.0/customers");

		String postRequestURI = postClient.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
		String postRequestHeaders = postClient.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setFirstName("Michael");
		customerRequest.setLastName("Gerard");
		Address address = new Address();
		address.setCity("Chicago");
		address.setState("Illinois");
		address.setStreet("101, East Pearson Street");
		address.setZip("60111");
		AddressUtil util = new AddressUtil();
		customerRequest.setDefaultBillingAddress(util.convertToAddressRepresentation(address));
		customerRequest.setDefaultShippingAddress(util.convertToAddressRepresentation(address));

		try{
			javax.ws.rs.core.Response responsePost =  postClient.post(customerRequest);
			System.out.println("POST METHOD Response ........." + responsePost.getStatus());
		}
		catch(WebApplicationException e){
			System.out.println("Customer could not be added!");
			System.out.println("Status: "+e.getResponse().getStatus());
		}

		/*****************************************************************************************
		 * GET METHOD invoke for all employees
		 *****************************************************************************************/
		/*  System.out.println("GET METHOD for all employees..........................................");
        WebClient getAllClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(getAllClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(getAllClient).getInInterceptors().add(new LoggingInInterceptor());

        // change application/xml  to get in xml format
        getAllClient = getAllClient.accept("application/json").type("application/json").path("/employeeservice/employee");

        String getAllRequestURI = getAllClient.getCurrentURI().toString();
        System.out.println("Client GET METHOD Request URI:  " + getAllRequestURI);
        String getAllRequestHeaders = getAllClient.getHeaders().toString();
        System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeaders);

        //to see as raw XML/json
        String getAllResponse = getAllClient.get(String.class);
        System.out.println("GET All METHOD Response: ...." + getAllResponse);

        /*****************************************************************************************
		 * POST METHOD invoke - Place order
		 *****************************************************************************************/
		System.out.println("POST METHOD - Place order .........................................................");
		WebClient postClient2 = WebClient.create("http://localhost:8081", providers);
		WebClient.getConfig(postClient2).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClient2).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to application/json get in json format
		postClient2 = postClient2.accept("application/xml").type("application/json").path("/bookstoreservice/v1.0/customers/MA111/orders");

		postRequestURI = postClient2.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
		postRequestHeaders = postClient2.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomerId("MA111");
		orderRequest.setBillingAddress(util.convertToAddressRepresentation(address));
		orderRequest.setShippingAddress(util.convertToAddressRepresentation(address));
		List<OrderDetailRequest> orderDetailRequests = new ArrayList<OrderDetailRequest>();

		BookRequest bookRequest = new BookRequest();
		bookRequest.setBookId("11112");		
		OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
		orderDetailRequest.setBookRequest(bookRequest);
		orderDetailRequest.setQuantity(2);
		orderDetailRequests.add(orderDetailRequest);

		bookRequest = new BookRequest();
		bookRequest.setBookId("11113");		
		orderDetailRequest = new OrderDetailRequest();
		orderDetailRequest.setBookRequest(bookRequest);
		orderDetailRequest.setQuantity(4);
		orderDetailRequests.add(orderDetailRequest);

		orderRequest.setOrderDetails(orderDetailRequests);
		orderRequest.setPaymentOption(PaymentOption.PayPal);

		try{
			javax.ws.rs.core.Response responsePost =  postClient2.post(orderRequest);
			System.out.println("POST METHOD Response ........." + responsePost.getStatus());
		}
		catch(WebApplicationException e){
			System.out.println("Order could not be placed!");
			System.out.println("Status: "+e.getResponse().getStatus());
		}

		/*****************************************************************************************
		 * GET METHOD invoke - Get order status information
		 *****************************************************************************************/
		System.out.println("GET METHOD - Get order status information.........................................................");
		WebClient getClient4 = WebClient.create("http://localhost:8081", providers);

		//Configuring the CXF logging interceptor for the outgoing message
		WebClient.getConfig(getClient4).getOutInterceptors().add(new LoggingOutInterceptor());
		//Configuring the CXF logging interceptor for the incoming response
		WebClient.getConfig(getClient4).getInInterceptors().add(new LoggingInInterceptor());

		// change application/xml  to get in xml format
		getClient4 = getClient4.accept("application/json").type("application/json").path("/bookstoreservice/v1.0/customers/MA111/orders/XO111");

		//The following lines are to show how to log messages without the CXF interceptors
		getRequestURI = getClient4.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
		getRequestHeaders = getClient4.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);

		//to see as raw XML/json
		try{
			OrderRepresentation orderRepresentation = getClient4.get(OrderRepresentation.class);
			System.out.println("CustomerId: "+orderRepresentation.getCustomerId());
			List<OrderDetailRepresentation> orderDetails = orderRepresentation.getOrderDetails();
			System.out.println("OrderDetails:");
			int i=0;
			for(OrderDetailRepresentation odr: orderDetails){
				BookRepresentation br = odr.getBook();
				System.out.println("\tProduct "+i+":");
				i++;
				System.out.println("\t\tQuantity: "+odr.getQuantity());
				System.out.println("\t\tBook title = "+br.getTitle()+" ,author = "+br.getAuthor()+", ISBN = "+br.getISBN()+", Price= "+br.getPrice());
			}
			System.out.println("STATE: "+orderRepresentation.getOrderState().toString());
		}catch(BadRequestException e){
			System.out.println("The request made cannot be fullfilled by the WebService. Please check your request and try again.");
			System.out.println("Status: "+e.getResponse().getStatus());
		}catch(NotAllowedException e){
        	System.out.println("The resource at the given URI cannot be accessed. Please enter a valid URI");
        	System.out.println("Status: 405 Resource cannot be accessed");
        }

		/*****************************************************************************************
		 * DELETE METHOD invoke - Cancel order
		 *****************************************************************************************/
		System.out.println("DELETE METHOD - cancel order.........................................................");
        WebClient deleteClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(deleteClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(deleteClient).getInInterceptors().add(new LoggingInInterceptor());

        // change application/xml  to application/json get in json format
        deleteClient = deleteClient.accept("application/xml").type("application/json").path("/bookstoreservice/v1.0/customers/MA111/orders/XO111");

        String deleteRequestURI = deleteClient.getCurrentURI().toString();
        System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI);
        String deleteRequestHeaders = deleteClient.getHeaders().toString();
        System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders);

        try{
			javax.ws.rs.core.Response responsePost =  deleteClient.delete();
			System.out.println("DELETE METHOD Response ........." + responsePost.getStatus());
		}
		catch(WebApplicationException e){
			System.out.println("Order could not be cancelled!");
			System.out.println("Status: "+e.getResponse().getStatus());
		}

        System.exit(0);
	}

}