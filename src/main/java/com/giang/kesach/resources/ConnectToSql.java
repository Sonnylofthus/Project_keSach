package com.giang.kesach.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class ConnectToSql {
		public Connection conn;
	    private Statement statement;
	    public static ConnectToSql db;
	    private ConnectToSql() {
	        String url= "jdbc:mysql://localhost:3306/";
	        String dbName = "project2";
	        String driver = "com.mysql.jdbc.Driver";
	        String userName = "root";
	        String password = "giang123";
	        try {
	            Class.forName(driver).newInstance();
	            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
	        }
	        catch (Exception sqle) {
	            sqle.printStackTrace();
	        }
	    }
	    /**
	     *
	     * @return MysqlConnect Database connection object
	     */
	    public static synchronized ConnectToSql getInstance() {
	        if ( db == null ) {
	            db = new ConnectToSql();
	        }
	        return db;
	 
	    }
	    /**
	     *
	     * @param query String The query to be executed
	     * @return a ResultSet object containing the results or null if not available
	     * @throws SQLException
	     */
	    public ResultSet query(String query) throws SQLException{
	        statement = db.conn.createStatement();
	        ResultSet res = statement.executeQuery(query);
	        return res;
	    }
	    /**
	     * @desc Method to insert data to a table
	     * @param insertQuery String The Insert query
	     * @return boolean
	     * @throws SQLException
	     */ 
	    public int insert(String insertQuery) throws SQLException {
	        statement = db.conn.createStatement();
	        int result = statement.executeUpdate(insertQuery);
	        return result;
	 
	    }
	public int delete(String deleteQuery) throws SQLException {
		statement = db.conn.createStatement();
		int result = statement.executeUpdate(deleteQuery);
		return result;

	}
	}
