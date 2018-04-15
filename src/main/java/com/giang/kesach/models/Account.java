package com.giang.kesach.models;


import java.util.ArrayList;
import java.util.List;


public class Account {


    private int id;

    private String username;

    private String password;
//
//    private List<BookShelf> bookShelfs = new ArrayList<>();
//
//    private UserInfo userInfo;
//
//    private List<Book> readList;
//
//    private TradeList tradeList;
//
//    private List<Book> wishList;
    private long token;

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    public List<Book> getReadList() {
//        return readList;
//    }
//
//    public void setReadList(List<Book> readList) {
//        this.readList = readList;
//    }
//
//    public List<Book> getWishList() {
//        return wishList;
//    }
//
//    public void setWishList(List<Book> wishList) {
//        this.wishList = wishList;
//    }
//
//    public TradeList getTradeList() {
//        return tradeList;
//    }
//
//    public void setTradeList(TradeList tradeList) {
//        this.tradeList = tradeList;
//    }
//
//    public List<BookShelf> getBookShelfs() {
//        return bookShelfs;
//    }
//
//    public void setBookShelfs(List<BookShelf> bookShelfs) {
//        this.bookShelfs = bookShelfs;
//    }
//
//    public void addBookShelf(BookShelf bookshelf) {
//        this.bookShelfs.add(bookshelf);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
