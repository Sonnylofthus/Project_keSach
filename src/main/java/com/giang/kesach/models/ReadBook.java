package com.giang.kesach.models;


import java.util.ArrayList;
import java.util.List;




//@Inheritance(strategy=InheritanceType.JOINED)
public class ReadBook extends Book {
	
	/**
	 * 
	 */
	
	private String readerComment;



	private List<BookShelf> bookShelfList = new ArrayList<>();

	public List<BookShelf> getBookShelfList() {
		return bookShelfList;
	}

	public void setBookShelfList(List<BookShelf> bookShelfList) {
		this.bookShelfList = bookShelfList;
	}

	public String getReaderComment() {
		return readerComment;
	}

	public void setReaderComment(String readerComment) {
		this.readerComment = readerComment;
	}





	

}
