package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.OwnBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradeListResource {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;
    public void addBookToTradeList(int accountId,long bId){
        try {
            stm=con.prepareStatement("INSERT INTO tradelist(account_id, book_id) VALUES (?,?)");
            stm.setInt(1,accountId);
            stm.setLong(2,bId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void removeBookFromTradeList(int accountId,long bId){
        try {
            stm=con.prepareStatement("DELETE FROM tradelist WHERE account_id=? AND book_id=?");
            stm.setInt(1,accountId);
            stm.setLong(2,bId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<OwnBook> getTradeableBookInCity(String city){
        BookResource BR=new BookResource();
        List<OwnBook> ownBooks=new ArrayList<>();
        try {
            stm=con.prepareStatement("SELECT userinfo.accountId,userinfo.city, tradelist.book_id\n" +
                    "FROM userinfo JOIN tradelist ON userinfo.city=?");
            stm.setString(1,city);
            ResultSet rs=stm.executeQuery();
            OwnBook ownBook=new OwnBook();
            while (rs.next()){
                Book book=BR.getBook(rs.getLong("book_id"));
                ownBook.setAccountId(rs.getInt("accountId"));
                ownBook.setbId(book.getbId());
                ownBook.setAuthors(book.getAuthors());
                ownBook.setbName(book.getbName());
                ownBook.setCategory(book.getCategory());
                ownBook.setDescription(book.getDescription());
                ownBook.setPulisher(book.getPulisher());
                ownBook.setPulishYear(book.getPulishYear());
                ownBook.setImgLink(book.getImgLink());
                ownBooks.add(ownBook);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ownBooks;
    }
    public List<OwnBook> getTradeableBookInDistrict(String district){
        BookResource BR=new BookResource();
        List<OwnBook> ownBooks=new ArrayList<>();
        try {
            stm=con.prepareStatement("SELECT userinfo.accountId,userinfo.district, tradelist.book_id\n" +
                    "FROM userinfo JOIN tradelist ON userinfo.district=?");
            stm.setString(1,district);
            ResultSet rs=stm.executeQuery();
            OwnBook ownBook=new OwnBook();
            while (rs.next()){
                Book book=BR.getBook(rs.getLong("book_id"));
                ownBook.setAccountId(rs.getInt("accountId"));
                ownBook.setbId(book.getbId());
                ownBook.setAuthors(book.getAuthors());
                ownBook.setbName(book.getbName());
                ownBook.setCategory(book.getCategory());
                ownBook.setDescription(book.getDescription());
                ownBook.setPulisher(book.getPulisher());
                ownBook.setPulishYear(book.getPulishYear());
                ownBook.setImgLink(book.getImgLink());
                ownBooks.add(ownBook);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ownBooks;
    }
}
