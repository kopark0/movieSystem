package server_client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
	public static Connection getConn() throws ClassNotFoundException, SQLException  {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String user = "root";
		String password = "mysql";
		String url = "jdbc:mysql://localhost:3306/movie_db?"
				+ "characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		
		return DriverManager.getConnection(url, user, password);
		
	}
}
