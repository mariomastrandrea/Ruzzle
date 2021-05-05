package it.polito.tdp.ruzzle.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DizionarioDAO 
{	
	/**
	 * Ritorna tutte le parole presenti nel dizionario
	 * @return
	 */
	public List<String> listParola() 
	{
		String query = "SELECT nome FROM parola ORDER BY nome";
		List<String> result = new ArrayList<>();

		try 
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				result.add(res.getString("nome"));
			}
			
			res.close();
			st.close();
			conn.close();			
		} 
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
			throw new RuntimeException("Error DAO in listParola()", sqle);
		}
		
		return result;
	}
	
	public Set<String> setParola() 
	{
		String query = "SELECT nome FROM parola ORDER BY nome";
		Set<String> result = new HashSet<>();

		try 
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				result.add(res.getString("nome"));
			}
			
			res.close();
			st.close();
			conn.close();			
		} 
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
			throw new RuntimeException("Error DAO in listParola()", sqle);
		}
		
		return result;
	}
}
