package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.ReadBook;

import java.util.List;

public interface IReadList {
    List<ReadBook> getReadList(int account_id);
    int addBookToReadList(int accountId,ReadBook book);
    int deleteBookFromReadList(int accountId,ReadBook book);
}
