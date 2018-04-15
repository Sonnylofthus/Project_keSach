package com.giang.kesach.resources;

import com.giang.kesach.models.Book;
import com.giang.kesach.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryResource implements ICategory {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;

    public int addNewCategory(String cName) {
        int count = 0;
        try {
            count = connectSql.insert("insert into category(category_name) values(" + cName + ")");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        try {
            ResultSet rs = connectSql.query("select * from category");
            while (rs.next()) {
                categories.add(getCategoryFromDb(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private static Category getCategoryFromDb(ResultSet rs) throws SQLException {
        Category cat = new Category();
        if(rs.next()) {
            cat.setcId(rs.getInt("category_id"));
            cat.setName(rs.getString("category_name"));
        }
        return cat;
    }

    @Override
    public Category getCategory(String name) {
        Category category = new Category();
        try {
            stm=con.prepareStatement("select * from category where category_name = ?");
            stm.setString(1,name);
            ResultSet query=stm.executeQuery();
            if (query.next()) {
                category.setName(name);
                category.setcId(query.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category getCategory(int id) {
        Category category = new Category();
        try {
            ResultSet query = connectSql.query("select * from category where category_id = " + id);
            if (query.next()) {
                category.setName(query.getString("category_name"));
                category.setcId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    @Override
    public int createNewCategory(String name) {
        int count = 0;
        sql = String.format("insert into category(category_name) values('%s')", name);
        try {
            count = connectSql.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int deleteCategory(int id) {
        int count = 0;
        sql = "delete from category where category_id =" + id;
        try {
            count = connectSql.delete(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean modifyCategory(Category cate) {
        return false;
    }

    public static List<Category> searchBook(String key){
        List<Category>  categories=new ArrayList<>();
        try {
            stm=con.prepareStatement("SELECT * FROM category WHERE category.category_name LIKE ?");
            stm.setString(1,'%'+key+'%');
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                categories.add(getCategoryFromDb(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    //loc sach theo category
    public List<Book> getBookUnderCategory(int id) {
        List<Book> books = new ArrayList<>();
        try {
            ResultSet rs = connectSql.query("select category_book.book_id,category_book.category_id,category_name,book.book_name," +
                    "book.description,book.img_link,book.book_published_year,book.book_publisher from category_book " +
                    "join book on category_book.book_id=book.book_id join category ON category.category_id=category_book.category_id " +
                    "and category_book.category_id=" + id);
            while (rs.next()) {
                Book book =BookResource.getBookFromDB(rs);
                books.add(book);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}
