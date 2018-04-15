package com.giang.kesach.resources;

import com.giang.kesach.models.Account;
import com.giang.kesach.models.Address;
import com.giang.kesach.models.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoResource {
    private static ConnectToSql connectSql = ConnectToSql.getInstance();
    private static Connection con = connectSql.conn;
    private static PreparedStatement stm = null;
    private static String sql = null;


    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = new UserInfo();
        try {
            ResultSet rs = connectSql.query("select * from userinfo where accountId=" + id);
            if (rs.next()) {

                userInfo.setuEmail(rs.getString("email"));
                userInfo.setuJoinDate(rs.getDate("joindate"));
                userInfo.setuNickName(rs.getString("nickname"));
                userInfo.setuName(rs.getString("name"));
                userInfo.setuTelNumber(rs.getString("tel"));
                userInfo.setuWorkPlace(rs.getString("work_place"));
                Address address=new Address();
                address.setCity(rs.getString("city"));
                address.setDistrict(rs.getString("district"));
                address.setWard(rs.getString("ward"));
                address.setNumber(rs.getString("number"));
                userInfo.setAddress(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }


    public int createUserInfo(int accountId, UserInfo userInfo) {
        int count = 0;
        if (isUserInfoExist(accountId))
            return count;

        try {
            stm = con.prepareStatement("INSERT INTO userinfo(accountId, email, joindate, name,  tel, work_place, city, district, ward, number,nickname) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)");
            stm.setInt(1, accountId);
            stm.setString(2, userInfo.getuEmail());
            stm.setDate(3, userInfo.getuJoinDate());
            stm.setString(4, userInfo.getuName());
            stm.setString(5, userInfo.getuTelNumber());
            stm.setString(6, userInfo.getuWorkPlace());
            stm.setString(7, userInfo.getAddress().getCity());
            stm.setString(8, userInfo.getAddress().getDistrict());
            stm.setString(9, userInfo.getAddress().getWard());
            stm.setString(10, userInfo.getAddress().getNumber());
            stm.setString(11,userInfo.getuNickName());
            count = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public boolean isUserInfoExist(int accountId) {
        try {
            ResultSet rs = connectSql.query("select * from userinfo where accountId=" + accountId);
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int modifyProfile(int accId,UserInfo userInfo) {
        int count = 0;
        if (isUserInfoExist(accId)) {
            //UserInfo userInfo =getUserInfo(accId);

            try {
                stm = con.prepareStatement("UPDATE  userinfo SET email=?, joindate=?, name=?, nickname=?, tel=?," +
                        " work_place=?, city=?, district=?, ward=?, number=? WHERE accountId=?");
                stm.setString(1, userInfo.getuEmail());
                stm.setDate(2, userInfo.getuJoinDate());
                stm.setString(3, userInfo.getuName());
                stm.setString(4, userInfo.getuTelNumber());
                stm.setString(5, userInfo.getuWorkPlace());
                stm.setString(6, userInfo.getAddress().getCity());
                stm.setString(7, userInfo.getAddress().getDistrict());
                stm.setString(8, userInfo.getAddress().getWard());
                stm.setString(9, userInfo.getAddress().getNumber());
                stm.setInt(10,accId);
                count = stm.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
