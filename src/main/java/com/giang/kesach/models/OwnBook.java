package com.giang.kesach.models;


//@Inheritance(strategy=InheritanceType.JOINED)
public class OwnBook extends Book{
	/**
	 * 
	 */
	
	private String imgLink;
	private int accountId;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	
}
