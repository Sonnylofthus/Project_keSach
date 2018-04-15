package com.giang.kesach.resources;

import com.giang.kesach.models.Book;

import java.util.List;

public interface IWishList {
   List<Book> getWishList(int acccountId);
    int addBookToWishList(int accountId,Book book);
    int deleteBookFromWishList(int accountId,Book book);
}
