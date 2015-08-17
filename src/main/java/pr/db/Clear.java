package pr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

public class Clear {
	private static final Logger LOG = Tools.LOG;
	private static String db = "127.0.0.1";
	private static String port = "5432";
	private static String user = "postgres";
	private static String password = "12345678";
	
	public void clear() {
		clear(db, port, user, password);
	}
	
	public void clear(String db, String port, String user, String password) {
		String connStr = String.format("jdbc:postgresql://%s:%s/", db, port);
		try (Connection connection = 
				DriverManager.getConnection(connStr, user, password);
				Statement statement = connection.createStatement();) {

			LOG.info("Clear ready!");
			Tools.deleteDB(statement, "pays");
		} catch (SQLException e) {
			LOG.error("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
}