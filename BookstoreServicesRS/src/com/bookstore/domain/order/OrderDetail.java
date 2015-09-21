package com.bookstore.domain.order;


import java.io.Serializable;

import com.bookstore.domain.item.Book;

public class OrderDetail implements Serializable{
	private static final long serialVersionUID = 4L;
	private Book book;
	private int quantity;

	public OrderDetail() {}
	
	public OrderDetail(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
