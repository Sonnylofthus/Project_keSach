package com.giang.kesach.resources;

import com.giang.kesach.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListResource implements IWishList {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;
    @Override
    public List<Book> getWishList(int acccountId) {
        List<Book> wishList=new ArrayList<>();
        try {

            ResultSet rs=connectSql.query("SELECT book_wishlist.account_id,book_wishlist.book_id,book.book_name,\n" +
                    "book.description,book.img_link,book.book_published_year,book.book_publisher\n" +
                    "FROM book_wishlist join book on book_wishlist.book_id=book.book_id and book_wishlist.account_id="+acccountId);
            while(rs.next()){
                Book book =BookResource.getBookFromDB(rs);

                wishList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishList;
    }

    @Override
    public int addBookToWishList(int accountId, Book book) {
        int count = 0;
        try {
            stm = con.prepareStatement("INSERT INTO book_wishlist(book_id,account_id) VALUES (?,?)");
            stm.setLong(1,book.getbId());
            stm.setInt(2,accountId);
            count = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteBookFromWishList(int accountId, Book book) {
        int coutn=0;
        try {
            stm=con.prepareStatement("DELETE FROM book_wishlist WHERE book_id=? AND account_id=?");
            stm.setLong(1,book.getbId());
            stm.setInt(2,accountId);
            coutn=stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coutn;
    }
}
