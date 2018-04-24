package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.ReadBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadListResource implements IReadList {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;

    @Override
    public List<ReadBook> getReadList(int account_id) {
            List<ReadBook> readList= new ArrayList<>();
        try {
            ResultSet rs = connectSql.query("select readbook.readerComment,readbook.account_id,readbook.book_id,book.book_name,\n" +
                    "book.description,book.img_link,book.book_published_year,book.book_publisher\n" +
                    "FROM readbook join book on readbook.book_id=book.book_id WHERE readbook.account_id= " + account_id);
            if (rs.next()) {
                ReadBook readBook = getReadBookFromDB(rs);
                readList.add(readBook);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readList;
    }

    public static ReadBook getReadBookFromDB(ResultSet rs) throws SQLException {
        ReadBook readBook = new ReadBook();
        readBook.setbId(rs.getLong("book_id"));
        readBook.setbName(rs.getString("book_name"));
        readBook.setDescription(rs.getString("description"));
        readBook.setAuthors(BookResource.getAuthor(rs.getLong("book_id")));
        readBook.setCategory(BookResource.getCategory(rs.getLong("book_id")));
        readBook.setImgLink(rs.getString("img_link"));
        readBook.setPulisher(rs.getString("book_publisher"));
        readBook.setPulishYear(rs.getInt("book_published_year"));
        readBook.setReaderComment(rs.getString("readerComment"));
        return readBook;
    }

    @Override
    public int addBookToReadList(int accountId, ReadBook book) {
        int count = 0;
        try {
            stm = con.prepareStatement("INSERT INTO readbook(readerComment, book_id, account_id) VALUES (?,?,?)");
            stm.setString(1, book.getReaderComment());
            stm.setLong(2, book.getbId());
            stm.setInt(3, accountId);
            count = stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteBookFromReadList(int accountId, ReadBook book) {
        int count = 0;
        try {
            stm = con.prepareStatement("DELETE FROM readbook WHERE book_id=?");
            stm.setLong(1, book.getbId());
            count = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
