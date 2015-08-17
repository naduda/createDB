package pr.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tools {
	public static final Logger LOG = LoggerFactory.getLogger(Tools.class);

	public static boolean dbExist(Statement statement, String dbName) {
		String query = String.format("select count(*) from pg_catalog.pg_database where datname = '%s';", dbName);
		try(ResultSet rs = statement.executeQuery(query);) {
			if(rs.next()) return rs.getInt(1) == 1;
		} catch (Exception e) {
			LOG.error("Query wrong.\n" + query);
		}
		return false;
	}
	
	public static void createDB(Statement statement, String dbName) {
		if(dbExist(statement, dbName)) {
			LOG.info("DataBase [" + dbName + "] is exist");
		} else {
			String query = String.format("CREATE DATABASE %s;", dbName);
			try {
				statement.execute(query);
				LOG.info("DB " + dbName + " created!");
			} catch (SQLException e) {
				LOG.error("Can't create database " + dbName + "; " + query);
			}
		}
	}
	
	public static void deleteDB(Statement statement, String dbName) {
		if(!dbExist(statement, dbName)) {
			LOG.info("DataBase [" + dbName + "] is not exist");
		} else {
			String query = String.format("DROP DATABASE %s;", dbName);
			try {
				statement.execute(query);
				LOG.info("DB " + dbName + " deleted!");
			} catch (SQLException e) {
				LOG.error("Can't delete database " + dbName + "; " + query);
			}
		}
	}
	
	public static void createTableUser(Statement statement) {
		String query = String.format("CREATE TABLE users ("
				+ "idUser integer NOT NULL,"
				+ "userName character varying(20) NOT NULL,"
				+ "login character varying(20) NOT NULL,"
				+ "password character varying(50) NOT NULL,"
				+ "email character varying(50) NOT NULL,"
				+ "CONSTRAINT pk_users PRIMARY KEY (idUser));");
		try {
			statement.execute(query);
			LOG.info("Table user created!");
		} catch (SQLException e) {
			LOG.error("Can't create table user");
		}
	}
	
	public static void createTableTarifs(Statement statement) {
		String query = String.format("CREATE TABLE tarifs ("
				+ "dt timestamp NOT NULL,"
				+ "idTarif smallint NOT NULL,"
				+ "tarif1 double precision,"
				+ "tarif2 double precision,"
				+ "CONSTRAINT pk_tarifs PRIMARY KEY (idTarif, dt));");
		try {
			statement.execute(query);
			LOG.info("Table tarifs created!");
		} catch (SQLException e) {
			LOG.error("Can't create table tarifs");
			e.printStackTrace();
		}
	}
	
	public static void createTableData(Statement statement) {
		String query = String.format("CREATE TABLE data ("
				+ "dt timestamp NOT NULL,"
				+ "idTarif smallint NOT NULL,"
				+ "idUser integer NOT NULL,"
				+ "value1 double precision,"
				+ "value2 double precision,"
				+ "CONSTRAINT pk_data PRIMARY KEY (idTarif, dt));");
		try {
			statement.execute(query);
			LOG.info("Table data created!");
		} catch (SQLException e) {
			LOG.error("Can't create table data");
			e.printStackTrace();
		}
	}
}