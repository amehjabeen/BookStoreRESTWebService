package com.bookstore.dal;

import java.util.ArrayList;

import com.bookstore.domain.item.Book;

public class BookCollectionDAO {

	private static ArrayList<Book> bookCollection = GenerateBookCollection();

	private static ArrayList<Book> GenerateBookCollection()
	{
		ArrayList<Book> books = new ArrayList<Book>();

		Book book = new Book();
		book.setId("11111");
		book.setISBN("ISBN11111");
		book.setAuthor("Author11111");
		book.setTitle("Title11111");
		book.setPrice(20.46);
		book.setShortDescription("This is a short description of book 11111");
		book.setDetailedDescription("This is a very detailed description of book 11111 with number of pages, index, table of contents etc...");
		books.add(book);


		book = new Book();
		book.setId("11112");
		book.setISBN("ISBN11112");
		book.setAuthor("Author11112");
		book.setTitle("Title11112");
		book.setPrice(19.99);
		book.setShortDescription("This is a short description of book 11112");
		book.setDetailedDescription("This is a very detailed description of book 11112 with number of pages, index, table of contents etc...");
		books.add(book);

		book = new Book();
		book.setId("11113");
		book.setISBN("ISBN11113");
		book.setAuthor("Author11113");
		book.setTitle("Title11113");
		book.setPrice(26.74);
		book.setShortDescription("This is a short description of book 11113");
		book.setDetailedDescription("This is a very detailed description of book 11113 with number of pages, index, table of contents etc...");
		books.add(book);


		book = new Book();
		book.setId("11114");
		book.setISBN("ISBN11114");
		book.setAuthor("Author11114");
		book.setTitle("Title11114");
		book.setPrice(43.16);
		book.setShortDescription("This is a short description of book 11114");
		book.setDetailedDescription("This is a very detailed description of book 11114 with number of pages, index, table of contents etc...");
		books.add(book);
		return books;
	}

	public Book getBookByID(String id)
	{
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getId().toLowerCase().equals(id.toLowerCase()))
				return bookCollection.get(i);
		}
		return null;
	}

	public Book getBookByISBN(String isbn)
	{
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getISBN().toLowerCase().equals(isbn.toLowerCase()))
				return bookCollection.get(i);
		}
		return null;
	}

	public ArrayList<Book> searchBookByISBN(String isbn)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getISBN().toLowerCase().contains(isbn.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}

	public ArrayList<Book> searchBookByTitle(String title)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getTitle().toLowerCase().contains(title.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}

	public ArrayList<Book> searchBookByAuthor(String author)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getAuthor().toLowerCase().contains(author.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}

	public ArrayList<Book> searchBook(String title, String author, String ISBN)
	{
		if(title.equals("") && author.equals("") && ISBN.equals("")){
			System.out.println("Returning complete collection");
			return bookCollection;
		}
		else{
			ArrayList<Book> list = new ArrayList<Book>();
			for(int i = 0; i< bookCollection.size(); i++)
			{
				Book b = bookCollection.get(i);
				if(b.getAuthor().equalsIgnoreCase(author) ||
						b.getTitle().equalsIgnoreCase(title) ||
						b.getISBN().equalsIgnoreCase(ISBN)) {
					list.add(b);
				}
			}
			return list;
		}
	}

	public Book searchBookById(String id) {
		Book b;
		for(int i = 0; i< bookCollection.size(); i++)
		{
			b = bookCollection.get(i);
			if(b.getId().equals(id))
				return b;
		}
		return null;
	}
}
