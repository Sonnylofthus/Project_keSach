package com.giang.kesach.resources;

import com.giang.kesach.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlobalResource {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;

}
