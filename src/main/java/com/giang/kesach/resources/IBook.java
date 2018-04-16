package com.giang.kesach.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.ReadBook;


public interface IBook {
	 
	public List<Book> getAllBook();
	public Book getBook(String name);
	public Book getBook(long id);
	public static Book createNewBook(Book book) {
		
		
		return null;
	}
	void addToReadList(int accountId,long bId,String comment);
	void rateBook(int accountId,long bId,short star);
	public int deleteBook(long id);
	public int modifyBook(long id,Book book);
	List<Book> getMostReadBook();
}
