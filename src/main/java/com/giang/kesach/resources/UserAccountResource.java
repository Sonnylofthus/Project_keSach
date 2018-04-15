package com.giang.kesach.resources;

import com.giang.kesach.models.Account;
import com.giang.kesach.models.Book;
import com.giang.kesach.models.BookShelf;
import com.giang.kesach.models.ReadBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountResource implements IUserAccount {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;
    private static UserInfoResource UR = new UserInfoResource();
    private static ReadListResource RR = new ReadListResource();
    private static WishListResource WR = new WishListResource();
    private static BookShelfResource BSR= new BookShelfResource();

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public int checkLoginInfo(Account account) {
        int id=0;
        try {
            stm = con.prepareStatement("SELECT * FROM account WHERE username=? AND password=?");
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            ResultSet rs = stm.executeQuery();

            if (!rs.next())
                return -1;
            else {
                id= rs.getInt("account_id");
                account.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public int createNewAccount(Account account) {
        int count=0;
        if(!isResisterValid(account))
            return -1;
        try {
            stm=con.prepareStatement("insert Into account(username,password) values(?,?)");
            stm.setString(1,account.getUsername());
            stm.setString(2,account.getPassword());
            count=stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



    @Override
    public boolean isUserExist(String username) {
        try {
            stm=con.prepareStatement("SELECT * FROM account WHERE username=?");
            stm.setString(1,username);
            ResultSet rs=stm.executeQuery();
            if(!rs.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteAccount(Account account) {
        return false;
    }

    @Override
    public int changeAccountPassword(Account account) {
       int count=0;
        try {
            stm=con.prepareStatement("update  account set password=? where account_id=?");
            stm.setString(1,account.getPassword());
            stm.setInt(2,account.getId());
            count=stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Account getAccountByName(String name) {
        Account account=new Account();
        try {
            stm=con.prepareStatement("SELECT * from account WHERE username=?");
            stm.setString(1,name);
            ResultSet rs=stm.executeQuery();
            if(rs.next()){
                account.setId(rs.getInt("account_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    private boolean isResisterValid(Account account){
        if(!(UR.getUserInfo(account.getId()).getuEmail()==null))
            if(isUserExist(account.getUsername()))
                return isPasswordAcceptable(account.getPassword());

        return false;
    }

    private boolean isPasswordAcceptable(String password) {
       return  (password.length()>=6);
    }

    @Override
    public Account getAccountById(int id) {
        Account account=new Account();
        try {
            stm=con.prepareStatement("SELECT * from account WHERE account_id=?");
            stm.setInt(1,id);
            ResultSet rs=stm.executeQuery();
            if(rs.next()){
                account.setId(rs.getInt("account_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account accountIni(Account account) {
        return null;
    }

    @Override
    public List<BookShelf> showAllBookShelf(int accountId) {

        List<BookShelf> bookShelves=new ArrayList<>();
        try {
            ResultSet rs=connectSql.query("SELECT account_bookshelf.bookShelfs_bookshelf_id,account_bookshelf.Account_account_id,bookshelf.bookshelf_name,bookshelf.description\n" +
                    "FROM account_bookshelf JOIN bookshelf ON account_bookshelf.bookShelfs_bookshelf_id=bookshelf.bookshelf_id AND account_bookshelf.Account_account_id="+accountId);
            while(rs.next()){
                BookShelf bookShelf=new BookShelf();
                bookShelf.setbId(rs.getInt("bookShelfs_bookshelf_id"));
                bookShelf.setbName(rs.getString("bookshelf_name"));
                bookShelf.setDescription(rs.getString("bookshelf.description"));
                bookShelves.add(bookShelf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookShelves;
    }
    private long getToken(int id){
        long token=0;
        try {
            stm=con.prepareStatement("SELECT token from account WHERE account_id="+id);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
               token= rs.getLong("token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }
    public boolean isTokenCorrect(int id,long token){

       return ((token!=0)&&token==getToken(id));

    }
    public void deleteToken(int id){
        try {
            stm=con.prepareStatement("update account set token=? Where account_id=?");
            stm.setLong(1,0);
            stm.setInt(2,id);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int setToken(Account account){
        long token=0;
        int row=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        long hash=(account.getUsername()+account.getPassword()+date).hashCode();
        token=hash*hash;
        account.setToken(token);
        try {
           stm=con.prepareStatement("update account set token=? Where account_id=?");
           stm.setLong(1,token);
           stm.setInt(2,account.getId());
           row=stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


}
