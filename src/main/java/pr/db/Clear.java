package pr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

public class Clear {
	private static final Logger LOG = Tools.LOG;
	
	public static void main(String[] args) {
		LOG.info("-------- Clear ------------");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOG.info("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			return;
		}

		try (Connection connection = 
				DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/", "postgres", "12345678");
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