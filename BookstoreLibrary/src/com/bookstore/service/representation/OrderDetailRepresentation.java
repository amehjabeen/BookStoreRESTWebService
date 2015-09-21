package com.bookstore.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class OrderDetailRepresentation{

	private BookRepresentation book;
	private int quantity;

	public OrderDetailRepresentation() {}
	
	public OrderDetailRepresentation(BookRepresentation book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public BookRepresentation getBook() {
		return book;
	}
	
	public void setBook(BookRepresentation book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
