package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.ReadBook;

import java.util.List;

public interface IBookShelf {
    //tra ve sach co trong 1 ke sach
    List<ReadBook> getBookshelfsBook(int id );
    int addBookToBookShelf(int bookSelfId,int bId);
    int removeBookFromBookShelf(int bookSelfId,int bId);
}
