package cn.edu.cuz.zhengjun.words.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class OpenDB {
	static String driverName = "com.mysql.jdbc.Driver";
	static String dbURL = "jdbc:mysql://rdsrvyfvqu7byyvvo.mysql.rds.aliyuncs.com:3306/words?characterEncoding=utf8";
	static String userName = "words";
	static String userPwd = "words_123";

	public static Connection getConnection() {
		Connection dbConn = null;

		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
}