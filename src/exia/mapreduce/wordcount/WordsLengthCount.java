package exia.mapreduce.wordcount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exia.mapreduce.common.Job;

/**
 * 
 * @author rbello
 * @see http://ksat.me/map-reduce-a-really-simple-introduction-kloudo/
 */
public class WordsLengthCount implements Job<
		String, // Input type
		List<Integer>, // Mapped type
		Map<Integer, List<String>>, // Combined type
		Map<Integer, Integer>> // Output type
	{
	
	private static final int CHUNCK_SIZE = 20;

	@Override
	public void map(Iterator<String> input, List<Integer> output) {
		
		// On fabrique la portion de données que ce noeud va traiter
		final List<String> chunck = new ArrayList<String>();
		while (chunck.size() < CHUNCK_SIZE && input.hasNext()) {
			chunck.add(input.next());
		}
		
		// Aucun job
		if (chunck.size() == 0) return;
		
		// On parcours chaque mot
		for (String word : chunck) {
			// On enregistre le mot et sa taille
			map(output, word);
		}
		
	}

	private void map(List<Integer> output, String word) {
		output.add(word.length());
	}

//	@Override
//	public void combine(List<Entry<String, Integer>> input, Map<Integer, List<String>> ouput) {
//		// On parcours chaque couple mot/taille, et on l'ajoute dans une liste associée à la taille
//		for (Entry<String, Integer> item : input) {
//			ouput.get(item.getValue()).add(item.getKey());
//		}
//	}

	@Override
	public void reduce(Map<Integer, List<String>> input, Map<Integer, Integer> output) {
		// Il suffit maintenant de parcourir les tailles et de compter le nombre de mots
		for (Integer length : input.keySet()) {
			output.put(length, input.get(length).size());
		}
	}


}
