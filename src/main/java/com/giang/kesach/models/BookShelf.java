package com.giang.kesach.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class BookShelf{
	
	private int bId;
	
	private String bName;
	
	private String description;
	


	private List<ReadBook> bookList = new ArrayList<>();

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ReadBook> getBookList() {
		return bookList;
	}

	public void setBookList(List<ReadBook> bookList) {
		this.bookList = bookList;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

}
