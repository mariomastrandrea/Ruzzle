package it.polito.tdp.ruzzle.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe che identifica la posizione di una casella del gioco
 */
public class Cell 
{
	private final int row;
	private final int col;
	private final StringProperty stringProperty;

	
	public Cell(int row, int col) 
	{
		this.row = row;
		this.col = col;
		this.stringProperty = new SimpleStringProperty();
	}

	public int getRow() 
	{
		return this.row;
	}

	public int getCol() 
	{
		return this.col;
	}
	
	public final StringProperty getStringProperty()
	{
		return this.stringProperty;
	}
	
	public void setChar(char c)
	{
		String ch = Character.toString(c);
		this.stringProperty.set(ch);
	}
	
	public char getChar()
	{
		return this.stringProperty.get().charAt(0);
	}

	@Override
	public String toString() 
	{
		return String.format("[%s,%s]", row, col);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
