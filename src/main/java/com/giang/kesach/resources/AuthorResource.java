package com.giang.kesach.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.giang.kesach.models.Author;
import com.giang.kesach.models.Book;

import javax.xml.ws.Response;

public class AuthorResource implements IAuthor {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;

    @Override
    public Author getWrittenBook(int id) {
        List<Book> writtenBook =new ArrayList<>();
        Author author=getAuthor(id);
              try {
            ResultSet rs=connectSql.query("select author_book.book_id,author_book.author_id,book.book_name,book.description,book.img_link,"
                    +"book.book_published_year,book.book_publisher from author_book " +
                    "join book on author_book.book_id=book.book_id and author_book.author_id="+id);
            while(rs.next()){
                Book book=new Book();
                author.setWrittenBookCount(author.getWrittenBookCount()+1);
                book.setbId(rs.getLong("book_id"));
                book.setbName(rs.getString("book_name"));
                book.setDescription(rs.getString("description"));
                book.setAuthors(BookResource.getAuthor(rs.getLong("book_id")));
                book.setCategory(BookResource.getCategory(rs.getLong("book_id")));
                book.setImgLink(rs.getString("img_link"));
                book.setPulisher(rs.getString("book_publisher"));
                book.setPulishYear(rs.getInt("book_published_year"));
                writtenBook.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        author.setWrittenBooks(writtenBook);

        return author;
    }

    public static boolean isExist(Author author) {
        // TODO Auto-generated method stub
        try {
            String query = "SELECT * FROM author WHERE author_name=? ";
            stm = con.prepareStatement(query);
            stm.setString(1, author.getaName());

            ResultSet rs = stm.executeQuery();
            if (!rs.next())
                return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Author> getAllAuthor() {
        List<Author> authors=new ArrayList<>();
        try {
            ResultSet rs= connectSql.query("select * from author");
            while (rs.next()){
                authors.add(getAuthorFromDataBase(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author getAuthor(String name) {
        Author author=new Author();
        try {
            ResultSet rs=connectSql.query("select * from author where author_name=\" "+name+"\"" );
            getAuthorFromDataBase(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return author;
    }

    @Override
    public Author getAuthor(int id) {
        Author author=new Author();
        try {
            ResultSet rs=connectSql.query("select * from author where aId="+id );
            author=getAuthorFromDataBase(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return author;
    }
    public static List<Author> searchAuthor(String key){
        List<Author> authors=new ArrayList<>();
        try {
            stm=con.prepareStatement("SELECT * FROM author WHERE author_name LIKE ?");
            stm.setString(1,'%'+key+'%');
            ResultSet rs=stm.executeQuery();
            while (rs.next()){

                authors.add(getAuthorFromDataBase(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
    private static Author getAuthorFromDataBase(ResultSet rs) throws SQLException {
        Author author=new Author();
        if(rs.next()){

            author.setaImg(rs.getString("author_img"));
            author.setaDescription(rs.getString("author_description"));
            author.setaBirthDay(rs.getInt("author_birthday"));
            author.setaName(rs.getString("author_name"));
            author.setaId(rs.getInt("aId"));
        }
        return author;
    }

    public int createNewAuthor(Author author) {
        int aId = 0;
        int count=0;
        try {
            String querry = "INSERT INTO author(author_name,author_birthday,author_description,author_img) "
                    + "VALUES(?,?,?,?)";
            stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, author.getaName());
            stm.setInt(2, author.getaBirthDay());
            stm.setString(3, author.getaDescription());
            stm.setString(4, author.getaImg());
            count=stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                aId = rs.getInt(1);
                author.setaId(aId);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteAuthor(int id) {
        int count=0;
        sql= "delete from author where aId ="+id;
        try {
            count=connectSql.delete(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int modifyAuthor(int id, Author author) {
        int count =0;
        sql="update author set author_name=?,author_birthday=?,author_description=?,author_img=? where aId=?";
        try {
            stm=con.prepareStatement(sql);
            stm.setString(1,author.getaName());
            stm.setInt(2,author.getaBirthDay());
            stm.setString(3,author.getaDescription());
            stm.setString(4,author.getaImg());
            stm.setInt(5,author.getaId());
            count=stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
