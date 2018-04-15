package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.BookShelf;
import com.giang.kesach.models.ReadBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookShelfResource implements IBookShelf {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;
    @Override
    public List<ReadBook> getBookshelfsBook(int id) {

        List<ReadBook> books=new ArrayList<>();
        try {
            ResultSet rs=connectSql.query("SELECT readbook_bookshelf.book_id,readbook_bookshelf.bookshelf_id,readbook.book_id,book.book_name,\n" +
                    "  book.description,book.img_link,book.book_published_year,book.book_publisher,readbook.readerComment\n" +
                    "from readbook_bookshelf join readbook on readbook_bookshelf.book_id=readbook.book_id\n" +
                    "join book on readbook.book_id=book.book_id and readbook_bookshelf.bookshelf_id="+id);
            while(rs.next()){
                ReadBook readBook=ReadListResource.getReadBookFromDB(rs);
                books.add(readBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    @Override
    public int addBookToBookShelf(int bookSelfId, int bId) {
        int count = 0;
        try {
            stm = con.prepareStatement("INSERT INTO readbook_bookshelf(book_id, bookshelf_id) VALUES (?,?)");

            stm.setLong(1,bId);
            stm.setInt(3, bookSelfId);
            count = stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }


    @Override
    public int removeBookFromBookShelf(int bookSelfId, int bId) {
        int count = 0;
        try {
            stm = con.prepareStatement("DELETE FROM readbook_bookshelf WHERE book_id=? and bookshelf_id=?");
            stm.setLong(1, bId);
            stm.setInt(2,bookSelfId);
            count = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
