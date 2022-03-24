package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public static Connection getConnection() {
		try {
			final String url = "jdbc:mariadb://localhost:3306/cinema";
			return DriverManager.getConnection(url, "elian", "senha5");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}