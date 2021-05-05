package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 */
public class Board implements Iterable<Cell>
{
	private List<Cell> cells;
	private int size;

	
	public Board(int size) 
	{
		this.size = size;

		//Definisco le "caselle" del gioco (e la forma del piano di gioco), inizialmente vuote
		this.cells = new ArrayList<>();
		for(int row = 0; row < this.size; row++) 
		{
			for (int col = 0; col < this.size; col++) 
			{
				Cell newCell = new Cell(row, col);
				this.cells.add(newCell);
			}
		}
	}
	
	public Cell getCell(int row, int col)
	{
		if( row < 0 || row > this.size - 1 ||
			col < 0 || col > this.size - 1)
			return null;
		
		int index = col + size * row;
		
		return this.cells.get(index);
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 * @param charsFrequencies 
	 */
	public void resetBoard(List<Double> charsFrequencies) 
	{
		for(Cell cell : this.cells) 
		{	
			//TODO: migliorare l'assegnazione secondo la probabiltà di ogni lettera di essere utilizzata nella lingua italiana
			double random = (double)(Math.random() * 100.0);
			char letter = this.getAlphabetChar(random, charsFrequencies);
			
			//grazie al "binding" fatto in FXMLController, la "set" modifica
			//direttamente il testo del bottone collegato alla posizione corrente
			cell.setChar(letter);
		}
	}
	
	private char getAlphabetChar(double random, List<Double> charsFrequencies)
	{
		for(int i=0; i<26; i++)
		{
			double d = charsFrequencies.get(i);
			
			if(random < d)
				return (char)('A' + i);
		}

		return 'Z';
	}
	
	/**
	 * Data una posizione, restituisce tutte le posizioni adiacenti ammissibili
	 * @param cell
	 * @return
	 */
	public List<Cell> getAdjacencies(Cell cell) 
	{
		List<Cell> result = new ArrayList<>();
		
		for(int r = -1; r<=1; r++) 
		{
			for(int c = -1; c<=1; c++) 
			{
				// tutte le 4 posizioni nell'intorno della cella: 1 sopra, 1 sotto, 1 a destra e 1 a sinistra				
				if(Math.abs(r+c) % 2 != 0) 
				{ 
					int newRow = cell.getRow() + r;
					int newCol = cell.getCol() + c;
					
					Cell adjCell = this.getCell(newRow, newCol);
					//controllo che gli indici non siano fuori dalla griglia
					if(adjCell != null)
						result.add(adjCell);
				}
			}
		}

		return result;
	}

	@Override
	public Iterator<Cell> iterator()
	{
		return this.cells.iterator();
	}
}
