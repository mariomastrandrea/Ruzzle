package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ruzzle.db.DizionarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model 
{
	private final int SIZE = 4;	//lato della scacchiera
	private Board board;
	private List<String> dizionario;
	private StringProperty statusText;

	
	public Model() 
	{
		this.statusText = new SimpleStringProperty();
		
		this.board = new Board(SIZE);
		DizionarioDAO dao = new DizionarioDAO();
		this.dizionario = dao.listParola();
		statusText.set(String.format("%d parole lette", this.dizionario.size()));
	}
	
	public void reset() 
	{
		this.board.reset() ;
		this.statusText.set("Board Reset");
	}

	public Board getBoard() 
	{
		return this.board;
	}

	public final StringProperty statusTextProperty() 
	{
		return this.statusText;
	}
	

	public final String getStatusText() 
	{
		return this.statusTextProperty().get();
	}
	

	public final void setStatusText(final String statusText) 
	{
		this.statusTextProperty().set(statusText);
	}

	public List<Pos> trovaParola(String parola) 
	{
		for(Pos p : board.getPositions()) 
		{
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0))
			{
				List<Pos> percorso = new ArrayList<>();
				percorso.add(p);
				
				if(cerca(parola, 1, percorso))
					return percorso;
			}
		}
		
		return null;
	}

	//metodo ricorsivo
	private boolean cerca(String parola, int livello, List<Pos> percorso)
	{
		//caso terminale
		if(livello == parola.length()) 
		{
			return true;
		}
		
		Pos ultima = percorso.get(percorso.size() - 1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		for(Pos p : adiacenti)
		{
			//non posso ripassare su una casella che è già stata premuta
			if(!percorso.contains(p) &&	
					parola.charAt(livello) == board.getCellValueProperty(p).get().charAt(0))
			{
				//tutto ok
				percorso.add(p);
				
				boolean parolaTrovata = cerca(parola, livello+1, percorso);
				if(parolaTrovata)
					return true;
				
				percorso.remove(percorso.size() - 1); //backtracking
			}
		}
		
		return false;
	}

	public List<String> trovaTutte() 
	{
		List<String> tutte = new ArrayList<>();
		for(String parola : this.dizionario)
		{
			if(parola.length() > 1)
			{
				parola = parola.toUpperCase();
				
				if(this.trovaParola(parola) != null) 
				{
					tutte.add(parola);
				}
			}
		}
		return null;
	}
	

}
