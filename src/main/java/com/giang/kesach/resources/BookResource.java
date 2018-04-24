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
import com.giang.kesach.models.Category;
import com.giang.kesach.models.ReadBook;

public class BookResource implements IBook {
	private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;
	private static final AuthorResource AR=new AuthorResource();
	//lay book tu batabase chuyen thanh pojo
	public static Book getBookFromDB(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setbId(rs.getLong("book_id"));
		book.setbName(rs.getString("book_name"));
		book.setDescription(rs.getString("description"));
		book.setAuthors(BookResource.getAuthor(rs.getLong("book_id")));
		book.setCategory(BookResource.getCategory(rs.getLong("book_id")));
		book.setImgLink(rs.getString("img_link"));
		book.setPulisher(rs.getString("book_publisher"));
		book.setPulishYear(rs.getInt("book_published_year"));
		return book;
	}
	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		ConnectToSql connectSql = ConnectToSql.getInstance();
		Connection con = connectSql.conn;
		List<Book> books = new ArrayList<>();
		long id = 0;
		String sql = "select * from book";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				id = rs.getLong("book_id");
				Book book = new Book();
				book.setbId(id);
				book.setbName(rs.getString("book_name"));
				book.setDescription(rs.getString("description"));
				book.setAuthors(BookResource.getAuthor(id));
				book.setCategory(BookResource.getCategory(id));
				book.setImgLink(rs.getString("img_link"));
				book.setPulisher(rs.getString("book_publisher"));
				book.setPulishYear(rs.getInt("book_published_year"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return books;
	}

	public  long createNewBook(Book book) {
		// ConnectToSql connectSql=ConnectToSql.getInstance();
		// Connection con=connectSql.conn;
		long bId = 0;
		sql = "insert into book (book_name,description,book_publisher,book_published_year,img_link) values(?,?,?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, book.getbName());
			stm.setString(2, book.getDescription());
			stm.setString(3, book.getPulisher());
			stm.setInt(4, book.getPulishYear());
			stm.setString(5, book.getImgLink());
			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				bId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapCategoryToBook(bId, book.getCategory());
		mapAuthorToBook(bId, book.getAuthors());
		return bId;
	}

	public static List<Book> searchBook(String key){
		List<Book> books=new ArrayList<>();
		try {
			stm=con.prepareStatement("SELECT * FROM book WHERE book.book_name LIKE ?");
			stm.setString(1,'%'+key+'%');
			ResultSet rs=stm.executeQuery();
			while (rs.next()){
				books.add(getBookFromDB(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	private static void mapAuthorToBook(long bId, List<Author> authors) {
      AuthorResource authorResource=new AuthorResource();
		for (Author a : authors) {
			if (!AuthorResource.isExist(a)) {
				AR.createNewAuthor(a);
			}
			Author author = authorResource.getAuthor(a.getaName());

				sql = "insert into author_book(book_id,author_id) values(?,?)";

				try {
					stm = con.prepareStatement(sql);
					stm.setLong(1, bId);
					stm.setInt(2,author.getaId());
					stm.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

			}
		}


	}

	private static List<Category> mapCategoryToBook(long bId, List<Category> category) {
		CategoryResource categoryResource=new CategoryResource();
		for (Category cat : category) {
			cat.setcId(categoryResource.getCategory(cat.getName()).getcId());
			sql = "insert into category_book(book_id,category_id) values(?,?)";
			try {
				stm = con.prepareStatement(sql);
				stm.setLong(1, bId);
				stm.setInt(2,cat.getcId());
				stm.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return category;
	}

	@Override
	public int deleteBook(long id) {
		int count = 0;
		// TODO Auto-generated method stub
		sql = "delete from book where book_id=?";
		count = getCount(id,sql);
		unmapAuthorBook(id);
		unmapCategoryBook(id);

		return count;

	}

	public static int unmapAuthorBook(long id) {
		sql = "delete from author_book where book_id=?";
		return getCount(id,sql);
	}

	private static int getCount(long id, String sql) {
		int count=0;
		try {
			stm = con.prepareStatement(sql);
			stm.setLong(1, id);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static int unmapCategoryBook(long id) {
		sql = "delete from category_book where book_id=?";
		return getCount(id,sql);
	}

	@Override
	public int modifyBook(long bId, Book book) {
		// TODO Auto-generated method stub
		int count = 0;
		sql = "update book set book_name=?,description=?,img_link=?,book_published_year=?,"
				+ "book_publisher=? where book_id=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, book.getbName());
			stm.setString(2, book.getDescription());
			stm.setString(3, book.getImgLink());
			stm.setInt(4, book.getPulishYear());
			stm.setString(5, book.getPulisher());
			stm.setLong(6, bId);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		AuthorResource authorResource=new AuthorResource();
//		for(Author author:book.getAuthors()) {
//			authorResource.modifyAuthor(author.getaId(),)
//		}
		unmapAuthorBook(bId);
		unmapCategoryBook(bId);
		mapAuthorToBook(bId, book.getAuthors());
		mapCategoryToBook(bId, book.getCategory());

		return count;
	}

	public static List<Category> getCategory(long id) {
		// TODO Auto-generated method stub
		ConnectToSql connectSql = ConnectToSql.getInstance();
		Connection con = connectSql.conn;
		List<Category> categorys = new ArrayList<>();
		String querry = "select category_book.book_id,category.category_name,category.category_id "
				+ " from category_book "
				+ " inner join category on category_book.category_id=category.category_id and category_book.book_id=?";
		try {
			PreparedStatement stm = con.prepareStatement(querry);
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Category cat = new Category();
				cat.setcId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));
				categorys.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorys;
	}

	// lay list tac gia cua book
	public static List<Author> getAuthor(long id) {
		// TODO Auto-generated method stub
		ConnectToSql connectSql = ConnectToSql.getInstance();
		Connection con = connectSql.conn;
		List<Author> authors = new ArrayList<>();
		String querry = "select author_book.book_id,author_book.author_id,author.author_name,author.author_img,author.author_description,author.author_birthday "
				+ " from author_book " + "join author on author_book.author_id=author.aId and author_book.book_id=?";
		try {
			PreparedStatement stm = con.prepareStatement(querry);
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Author a = new Author();
				a.setaBirthDay(rs.getInt("author_birthday"));
				a.setaId(rs.getInt("author_id"));
				a.setaName(rs.getString("author_name"));
				a.setaDescription(rs.getString("author_description"));
				authors.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public Book getBook(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(long id) {
		// TODO Auto-generated method stub
		
		Book book=new Book();
		sql="select * from book where book_id=?";
		try {
			stm=con.prepareStatement(sql);
			stm.setLong(1, id);
			ResultSet rs=stm.executeQuery();
			while(rs.next()) {
				id = rs.getLong("book_id");
				book.setbId(id);
				book.setbName(rs.getString("book_name"));
				book.setDescription(rs.getString("description"));
				book.setAuthors(BookResource.getAuthor(id));
				book.setCategory(BookResource.getCategory(id));
				book.setImgLink(rs.getString("img_link"));
				book.setPulisher(rs.getString("book_publisher"));
				book.setPulishYear(rs.getInt("book_published_year"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return book;
	}

	@Override
	public void addToReadList(int accountId, long bId,String comment) {
		try {
			stm=con.prepareStatement("INSERT INTO readbook(readerComment, book_id, account_id) VALUES (?,?,?)");
			stm.setString(1,comment);
			stm.setLong(2,bId);
			stm.setInt(3,accountId);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rateBook(int accountId, long bId, short star) {
		try {
			stm=con.prepareStatement("INSERT INTO book_rate(rated_book_id, acccount_id, star) VALUES (?,?,?)");
			stm.setLong(1,bId);
			stm.setInt(2,accountId);
			stm.setShort(3,star);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> getMostReadBook() {
		List<Book> topBook=new ArrayList<>();
		try {
			stm=con.prepareStatement("SELECT * FROM  book ORDER BY readCount DESC LIMIT 100");
			ResultSet rs=stm.executeQuery();
			while (rs.next()){
				topBook.add(getBookFromDB(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topBook;
	}
}
