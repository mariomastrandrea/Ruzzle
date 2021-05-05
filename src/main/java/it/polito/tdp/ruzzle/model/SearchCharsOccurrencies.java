package it.polito.tdp.ruzzle.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.ruzzle.db.DizionarioDAO;

public class SearchCharsOccurrencies
{
	public static void main(String[] args)
	{
		Map<Character, LongObj> charsOccurrencies = new TreeMap<>();
		
		for(int i=0; i<26; i++)
		{
			Character c = Character.valueOf((char)('a' + i));
			charsOccurrencies.put(c, new LongObj());
		}
		
		DizionarioDAO dao = new DizionarioDAO();
		List<String> dictionary = dao.listParola();
		
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
		
		double sum = 0;
		for(char c : charsFrequencies.keySet())
		{
			double f = charsFrequencies.get(c);
			System.out.println(c + "  -->  " + f);
			
			sum += f;
		}
		System.out.println("Sum: "+sum);
	}
}
