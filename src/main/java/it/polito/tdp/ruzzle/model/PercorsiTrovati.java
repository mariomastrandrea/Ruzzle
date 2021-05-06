package it.polito.tdp.ruzzle.model;

import java.util.List;
import java.util.Set;

public class PercorsiTrovati
{
	private Set<List<Cell>> percorsi;
	private boolean haSenso;
	private boolean giaTrovata;
	
	
	public PercorsiTrovati(Set<List<Cell>> percorsi, boolean haSenso, boolean giaTrovata)
	{
		this.percorsi = percorsi;
		this.haSenso = haSenso;
		this.giaTrovata = giaTrovata;
	}
	
	public Set<List<Cell>> getPercorsi()
	{
		return this.percorsi;
	}
	
	public boolean haSenso()
	{
		return this.haSenso;
	}
	
	public boolean parolaGiaTrovata()
	{
		return this.giaTrovata;
	}
}
