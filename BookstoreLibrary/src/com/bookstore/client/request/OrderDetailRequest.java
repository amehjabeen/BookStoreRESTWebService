package com.bookstore.client.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderDetailRequest {

	private BookRequest bookRequest;
	private int quantity;

	public OrderDetailRequest() {}
	
	public OrderDetailRequest(BookRequest book, int quantity) {
		this.bookRequest = book;
		this.quantity = quantity;
	}
	
	public BookRequest getBookRequest() {
		return bookRequest;
	}
	
	public void setBookRequest(BookRequest book) {
		this.bookRequest = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
