package com.giang.kesach.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.giang.kesach.models.Book;



public interface IBook {
	 
	public List<Book> getAllBook();
	public Book getBook(String name);
	public Book getBook(long id);
	public static Book createNewBook(Book book) {
		
		
		return null;
	}
	public int deleteBook(long id);
	public int modifyBook(long id,Book book);

}
