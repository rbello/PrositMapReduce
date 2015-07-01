package exia.mapreduce.wordcount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import exia.mapreduce.common.Job;

public class Main {

	public static void main(String[] args) {
		
//		// On fabrique un cluster
//		Cluster cluster = new Cluster(10);
//		
//		// On fabrique un objet qui va permettre la lecture des données en entrée
//		DataReader dr = new DataReader.Folder("/");
//
//		// On lance l'opération
//		cluster.input(dr);
		
		// On fabrique un premier cluster avec des Mappers
		ExecutorService mappers = Executors.newFixedThreadPool(10);
		
		// On fabrique un autre cluster avec des Reducers
		ExecutorService reducers = Executors.newFixedThreadPool(1);

		
		try {
			
			final Scanner scanner = new Scanner(new FileInputStream(new File("")));
			
			final Iterator<String> input = new Iterator<String>() {
				public boolean hasNext() {
					return scanner.hasNextLine();
				}
				public String next() {
					return scanner.nextLine();
				}
			};
			
			final WordsLengthCount job = new WordsLengthCount();
			
			final List<Integer> mappedData;
			
			final Runnable mapperJob = new Runnable() {
				public void run() {
					job.map(input, mappedData);
					if (input.hasNext())
				}
			};
			
			mappers.execute();
			
			scanner.close();
			
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		catch (Throwable e) {
			System.err.println("File not found: " + e.getMessage());
		}
		
	}
	
}
