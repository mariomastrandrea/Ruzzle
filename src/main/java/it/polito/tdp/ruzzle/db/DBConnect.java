package it.polito.tdp.ruzzle.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnect 
{
	private static final String jdbcURL = "jdbc:mariadb://localhost/dizionario";
	private static final String username = "root";
	private static final String password = "root";
	private static HikariDataSource ds;
	
	static
	{
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcURL);
		config.setUsername(username);
		config.setPassword(password);
		
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtChacheSize", 250);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		ds = new HikariDataSource(config);
	}
	
	public static Connection getConnection() 
	{
		try 
		{
			return ds.getConnection();
		} 
		catch (SQLException sqle) 
		{
			System.err.println("Errore di connessione al db, url: " + jdbcURL);
			throw new RuntimeException(sqle);
		}
	}
}
