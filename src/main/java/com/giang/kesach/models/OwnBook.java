package com.giang.kesach.models;


//@Inheritance(strategy=InheritanceType.JOINED)
public class OwnBook extends ReadBook{
	/**
	 * 
	 */
	
	private String imgLink;

	private Account bAccount;
	
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public Account getbAccount() {
		return bAccount;
	}
	public void setbAccount(Account bAccount) {
		this.bAccount = bAccount;
	}
	
}
