package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.polito.tdp.ruzzle.db.DizionarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model 
{
	private final int SIZE = 4;	//lato della scacchiera
	private Board board;
	private List<String> dizionario; //parole in ordine alfabetico
	private Set<String> parole;
	private StringProperty statusText;
	private List<Double> charsDistribution;
	private DizionarioDAO dao;
	
	public Model() 
	{
		this.statusText = new SimpleStringProperty();
		this.board = new Board(SIZE);
		
		this.dao = new DizionarioDAO();
		this.dizionario = this.dao.listParola();
		this.parole = this.dao.setParola();
		
		this.statusText.set(String.format("%d parole lette", this.dizionario.size()));
		
		this.charsDistribution = this.getCharsDistribution();
	}
	
	public void reset() 
	{
		this.board.resetBoard(this.charsDistribution);
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

	public final void setStatusText(final String statusText) 
	{
		this.statusTextProperty().set(statusText);
	}

	public PercorsiTrovati trovaParola(String parola) 
	{
		Set<List<Cell>> percorsi = new HashSet<>();
		
		for(Cell cell : this.board) 
		{
			if(cell.getChar() == parola.charAt(0))
			{
				List<Cell> percorso = new ArrayList<>();
				percorso.add(cell);
				
				if(cerca(parola, 1, percorso))
					percorsi.add(percorso);
			}
		}
		
		boolean haSenso = this.parole.contains(parola);
		
		return new PercorsiTrovati(percorsi, haSenso);
	}

	//metodo ricorsivo
	private boolean cerca(String parola, int livello, List<Cell> percorso)
	{
		//caso terminale
		if(livello == parola.length()) 
		{
			return true;
		}
		
		Cell ultima = percorso.get(percorso.size() - 1);
		List<Cell> celleAdiacenti = this.board.getAdjacencies(ultima);
		
		for(Cell cell : celleAdiacenti)
		{
			//non posso ripassare su una casella che è già stata premuta
			if(!percorso.contains(cell) && cell.getChar() == parola.charAt(livello))
			{
				//tutto ok
				percorso.add(cell);
				
				boolean parolaTrovata = cerca(parola, livello+1, percorso);
				
				if(parolaTrovata)
					return true;
				
				percorso.remove(percorso.size() - 1); //backtracking
			}
		}
		
		return false;
	}

	public List<String> trovaTutteleParolePresenti() 
	{
		List<String> tutte = new ArrayList<>();
		
		for(String parola : this.dizionario)
		{
			if(parola.length() > 1)
			{
				parola = parola.toUpperCase();
				
				Set<List<Cell>> percorsiTrovati = this.trovaParola(parola).getPercorsi();
				if(percorsiTrovati != null && !percorsiTrovati.isEmpty()) 
				{
					tutte.add(parola);
				}
			}
		}
		
		return tutte;
	}
	
	public Map<Character, Double> getCharsFrequencies()
	{
		Map<Character, LongObj> charsOccurrencies = new TreeMap<>();
		
		for(int i=0; i<26; i++)
		{
			Character c = Character.valueOf((char)('a' + i));
			charsOccurrencies.put(c, new LongObj());
		}
		
		List<String> dictionary = this.dao.listParola();
		
		for(String word : dictionary)
		{
			char[] array = word.toCharArray();
			
			for(char c : array)
			{
				if(c == 'à')
					c = 'a';
				
				if(c == 'è')
					c = 'e';
				
				if(c == 'é')
					c = 'e';
				
				if(c == 'ì')
					c = 'i';
				
				if(c == 'ò')
					c = 'o';
				
				if(c == 'ù')
					c = 'u';
				
				if(charsOccurrencies.containsKey(c))
					charsOccurrencies.get(c).value++;
			}
		}
		
		long totChars = 0;
		
		for(char c : charsOccurrencies.keySet())
		{
			totChars += charsOccurrencies.get(c).value;
		}
		
		Map<Character, Double> charsFrequencies = new TreeMap<>();
		
		for(char c : charsOccurrencies.keySet())
		{
			long occurrencies = charsOccurrencies.get(c).value;
			double frequency = ((double)occurrencies / (double)totChars) * 100.0;
			charsFrequencies.put(c,frequency);
		}
		
		return charsFrequencies;
	}
	
	public List<Double> getCharsDistribution()
	{
		Map<Character, Double> frequencies = this.getCharsFrequencies();
		List<Double> distribution = new ArrayList<>();
		
		double counter = 0.0;
		
		for(char c : frequencies.keySet())
		{
			double f = frequencies.get(c);
			counter += f;
			distribution.add(Double.valueOf(counter));
		}
		
		return distribution;
	}
	
}
