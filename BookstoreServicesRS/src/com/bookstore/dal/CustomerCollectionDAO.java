package com.bookstore.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.bookstore.domain.customer.Address;
import com.bookstore.domain.customer.Customer;
import com.bookstore.domain.order.Order;
import com.bookstore.domain.order.OrderDetail;
import com.bookstore.service.representation.OrderRepresentation.OrderStatus;
import com.bookstore.service.representation.OrderRepresentation.PaymentOption;


public class CustomerCollectionDAO {
	
private static HashMap<String, Customer> customerMap = GenerateCustomers();
		
	public Customer getCustomer(String cid) 
	{
		return customerMap.get(cid);
	}
	
	
	public boolean addCustomer(Customer customer)
	{
		if(customerMap.get(customer.getCustomerId()) == null)
		{
			customerMap.put(customer.getCustomerId(), customer);
			return true;
		}
		else 
			return false;
	}
	
	public Customer authenticateCustomer(String username, String password){
		Iterator<?> entries = customerMap.entrySet().iterator();
		while (entries.hasNext()) {
		  Entry<?, ?> thisEntry = (Entry<?, ?>) entries.next();
		  Customer customer =  (Customer) thisEntry.getValue();
		  if(customer.getUsername().equals(username) && customer.getPassword().equals(password)){
			  return customer;
		  }
		}
		return null;
	}
	
	
	private static HashMap<String, Customer> GenerateCustomers()
	{
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		Customer customer = new Customer();
		
		customer.setCustomerId("MA111");
		customer.setFirstName("Asma");
		customer.setLastName("Mehjabeen");
		customer.setUsername("Customer1111");
		customer.setUsername("Password1111");
		
		Address addr1 = new Address();
		addr1.setAddressId("456");
		addr1.setState("IL");
		addr1.setCity("Chicago");
		addr1.setStreet("7700 Glenwood Ave");
		addr1.setZip("60107");
		customer.setDefaultShippingAddress(addr1);
		customer.setDefaultBillingAddress(addr1);
		
		Order order = new Order();
		order.setOrderId("XO111");
		order.setOrderState(OrderStatus.Processing);
		order.setPaymentOption(PaymentOption.PayPal);
		order.setShippingAddress(customer.getDefaultShippingAddress());
		order.setbillingAddress(customer.getDefaultBillingAddress());
		
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11112"));
		orderDetail.setQuantity(1);
		orderDetails.add(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11113"));
		orderDetail.setQuantity(3);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		customer.addOrder(order);
		
		
		customers.put(customer.getCustomerId(), customer);
		
		customer = new Customer();
		
		customer.setCustomerId("12345");
		customer.setFirstName("Daniel");
		customer.setLastName("Bednarczyk");

		customer.setUsername("Customer1112");
		customer.setUsername("Password1112");
		
		addr1 = new Address();
		addr1.setAddressId("123");
		addr1.setState("IL");
		addr1.setCity("Chicago");
		addr1.setStreet("1100 Milwaukee Ave");
		addr1.setZip("60111");
		customer.setDefaultShippingAddress(addr1);
		customer.setDefaultBillingAddress(addr1);
		
		order = new Order();
		order.setOrderId("1234");
		order.setOrderState(OrderStatus.Processing);
		order.setPaymentOption(PaymentOption.PayPal);
		order.setShippingAddress(customer.getDefaultShippingAddress());
		order.setbillingAddress(customer.getDefaultBillingAddress());
		
		orderDetails = new ArrayList<OrderDetail>();
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11111"));
		orderDetail.setQuantity(2);
		orderDetails.add(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11112"));
		orderDetail.setQuantity(1);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		customer.addOrder(order);
		
		
		customers.put(customer.getCustomerId(), customer);
		
		
		
		customer = new Customer();
		
		customer.setCustomerId("123456");
		customer.setFirstName("Fred");
		customer.setLastName("Riekkie");

		customer.setUsername("Customer1113");
		customer.setUsername("Password1113");
		
		addr1 = new Address();
		addr1.setAddressId("1234");
		addr1.setState("IL");
		addr1.setCity("Chicago");
		addr1.setStreet("11234 North Ave");
		addr1.setZip("61234");
		customer.setDefaultShippingAddress(addr1);
		customer.setDefaultBillingAddress(addr1);
		
		order = new Order();
		order.setOrderId("12345");
		order.setOrderState(OrderStatus.Shipped);
		order.setPaymentOption(PaymentOption.CreditCard);
		order.setShippingAddress(customer.getDefaultShippingAddress());
		order.setbillingAddress(customer.getDefaultBillingAddress());
		
		orderDetails = new ArrayList<OrderDetail>();
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11113"));
		orderDetail.setQuantity(3);
		orderDetails.add(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11114"));
		orderDetail.setQuantity(4);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		customer.addOrder(order);
		
		customers.put(customer.getCustomerId(), customer);
		
		return customers;
	}
}
