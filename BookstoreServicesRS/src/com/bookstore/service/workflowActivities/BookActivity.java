package com.bookstore.service.workflowActivities;

import java.util.ArrayList;

import com.bookstore.dal.BookCollectionDAO;
import com.bookstore.domain.item.Book;
import com.bookstore.service.representation.BookRepresentation;
import com.bookstore.service.representation.Constants;
import com.bookstore.service.representation.Link;

public class BookActivity {

	BookCollectionDAO bookDao = new BookCollectionDAO();

	public ArrayList<BookRepresentation> searchBook(String title, String author, String ISBN) {
		ArrayList<Book> books = bookDao.searchBook(title, author, ISBN);
		if(books!=null && (!books.isEmpty())){
			ArrayList<BookRepresentation> bookReps = new ArrayList<BookRepresentation>();
			Link link;
			for (Book book : books){
				BookRepresentation bookRep = new BookRepresentation();
				title = book.getTitle();
				ISBN = book.getISBN();
				author = book.getAuthor();
				bookRep.setBookId(book.getId());
				bookRep.setAuthor(author);
				bookRep.setISBN(ISBN);
				bookRep.setPrice(book.getPrice());
				bookRep.setTitle(title);

				link = new Link();
				link.setMethod(Constants.HttpGet);
				link.setMediaType(Constants.mediaType);
				link.setUse("View all details");
				link.setUrl(Constants.baseUri + "/books?title="+title+"&author="+author+"&ISBN="+ISBN);

				bookRep. addLink(link);
				bookReps.add(bookRep);		

			}
			return bookReps;
		}
		else{
			System.out.println("Books not NULL: "+(books!=null));
			System.out.println("Books not empty: "+(!(books.isEmpty())));
			return null;
		}
	}
	
	public BookRepresentation searchBookById(String id){
		Book book = bookDao.searchBookById(id);
		if(book != null){
			BookRepresentation bookRep = new BookRepresentation();
			bookRep.setBookId(id);
			bookRep.setAuthor(book.getAuthor());
			bookRep.setTitle(book.getTitle());
			bookRep.setISBN(book.getISBN());
			bookRep.setPrice(book.getPrice());
			bookRep.setShortDescription(book.getShortDescription());
			bookRep.setLongDescription(book.getDetailedDescription());
			
			Link link = new Link();
			link.setMediaType(Constants.mediaType);
			link.setMethod(Constants.HttpGet);
			link.setUse("Search all books");
			link.setUrl(Constants.baseUri+"/books");
			
			bookRep.addLink(link);
			
			/*Link link = new Link();
			link.setMethod(Constants.HttpGet);
			link.setMediaType(Constants.mediaType);
			link.setUse("Purchase book");
			link.setUrl(Constants.baseUri + WHERE YOU WANT TO DIRECT THE USER); */
			
			//TODO - ADD LINKS HERE IF YOU WISH TO - DEPENDS ON HOW YOU DESIGN THE UI.

			/*bookRep.addLink(link); */
			
		}		
		return null;
	}
}
