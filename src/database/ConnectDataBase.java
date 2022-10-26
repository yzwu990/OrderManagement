package database;

import java.sql.*;


public class ConnectDataBase {
	// set JDBC driver
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// set database URL. 
	// Database name is between / and ?  
	static final String DB_URL = "jdbc:mysql://localhost:3306/management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT-5";
	// MySQL user name and pass world
	static final String USER_NAME = "root";
	static final String PASSWORLD = "Fuck1990";
	String adminUserName = "username";
	String adminPassword = "password"; 
	String adminTable = "admin", strtable2 = "books",ID="id";//连接数据库中emp中admindt
	public Connection conn = null;
	public Statement stmt = null;

	public ConnectDataBase() {
		try {
			// 
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORLD);
			stmt = conn.createStatement();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

}
