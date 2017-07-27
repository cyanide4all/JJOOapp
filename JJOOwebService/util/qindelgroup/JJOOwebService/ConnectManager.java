package qindelgroup.JJOOwebService;
import java.sql.*;

public class ConnectManager {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			String userName = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/jjoo";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Database connection terminated");
			} catch (Exception e) {
			}
		}
	}
}
