package cn.edu.cuz.zhengjun.words.db;

import cn.edu.cuz.zhengjun.words.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {
    public static User user;

    public static int getIdByUserName(String userName){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        int result = -1;

        try {
            String SQL = "SELECT id FROM users where username = '" + userName + "'";
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            if (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }

    public static boolean login(String userName, String password){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String SQL = "SELECT * FROM users where username = '" + userName +
                    "' and password = '" + password + "'";
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            if (rs.next()) {
                user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"), rs.getInt("type"));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }

    public static boolean register(String userName, String password){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String SQL = "insert into users(username,password) values('"+
                    userName + "','" + password + "')";
            stmt = dbConn.createStatement();
            int count = stmt.executeUpdate(SQL);

            if (count > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }
}
