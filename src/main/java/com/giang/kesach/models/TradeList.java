package com.giang.kesach.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class TradeList {
	
	private int id;
	
	private List<OwnBook> tradeableList = new ArrayList<OwnBook>();
	
	private Account account;
	public List<OwnBook> getTradeableList() {
		return tradeableList;
	}

	public void setTradeableList(List<OwnBook> tradeableList) {
		this.tradeableList = tradeableList;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
