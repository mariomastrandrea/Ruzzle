package it.polito.tdp.ruzzle.model;

import java.util.List;
import java.util.Set;

public class PercorsiTrovati
{
	private Set<List<Cell>> percorsi;
	private boolean haSenso;
	
	
	public PercorsiTrovati(Set<List<Cell>> percorsi, boolean haSenso)
	{
		this.percorsi = percorsi;
		this.haSenso = haSenso;
	}
	
	public Set<List<Cell>> getPercorsi()
	{
		return this.percorsi;
	}
	
	public boolean haSenso()
	{
		return this.haSenso;
	}
}
