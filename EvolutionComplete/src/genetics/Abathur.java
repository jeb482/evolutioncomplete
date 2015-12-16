package genetics;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import util.Constants;





public class Abathur {
	
	public static void main(String[] args) {
		String rootDir = "default";
		int numGenerations = 10;
		
		
		if (args.length > 0)
			rootDir = args[0];
		if (args.length > 1) {
			numGenerations = Integer.parseInt(args[1]);
		}
		
		evolve(rootDir, numGenerations);
	}
	
	/**
	 * Enters the given directory and begins conducting genetic algorithms inside.
	 * 
	 * File format is <rootDir>/<generationNumber>/<strainNumber>
	 * 
	 * Consulted http://www.mkyong.com/java/how-to-create-directory-in-java/ for file processing
	 * 
	 * @param rootDir
	 */
	public static void evolve(String rootDir, int numGenerations) {
		// Set up the root directory
		System.out.println("Acquiring ecosystem.");
		File root = new File(rootDir);
		if (!root.exists()) {
			root.mkdir();
		} else if (!root.isDirectory()) {
			System.out.println("Failed to evolve strain. Cannot overwrite " + rootDir + " as a directory.");
			return;
		}
		
		// Acquire the most recent generation.
		System.out.println("Searching for viable organisms.");
		File[] generations = root.listFiles(new NumericalFilter());
		int startGen = -1;
		int gen;
		for (File f : generations) {
			gen = Integer.parseInt(f.getName());
			if (gen > startGen) startGen = gen;
		}
		
		// Create the population
		System.out.println("Spawning population.");
		Strain[] population;
		if (startGen == -1) {
			population = randomPopulation(Constants.POPULATION_SIZE);
		} else {
			population = loadPopulation(Paths.get(rootDir, ""+startGen));
		}
		startGen += 1;
		
		// Main loops
		for (int g = startGen; g < startGen + numGenerations; g++) {
			System.out.println("Advancing generation " + g + ".");
			
			// Select
			System.out.println("Ascertaining value of essence.");
			
			// Mutate
			System.out.println("Spinning sequences.");
			
			// Save
			System.out.println("Archiving genetic code.");
			
		
		}	
		
		
		System.out.println("Evolution complete.");
	}

	
	/**
	 * Produces an array of Strains, each with a single randomly generated Bevavior
	 * 
	 * @param size
	 * @return
	 */
	protected static Strain[] randomPopulation(int size) {
		Strain[] population =  new Strain[size];
		for (int i = 0; i < size; i++) {
			population[i] = new Strain();
			population[i].addRandomBehavior();
		}
		
		return population;
	}
	
	/**
	 * Loads every XML in the directory as a Strain and returns them in an array
	 *  
	 * @param dirname
	 * @return population
	 */
	protected static Strain[] loadPopulation(Path dir) {
		File d = dir.toFile();
		String[] filenames = d.list(new StrainXmlFilter());
		Strain[] population = new Strain[filenames.length];
		for (int i = 0; i < population.length; i++) {
			population[i] = Parser.loadStrain(Paths.get(dir.toString(), (filenames[i])));
		}
		return population;
	}
	
	/**
	 * Filters for acquiring files with numerical names 
	 * @author Jimmy
	 *
	 */
	protected static class NumericalFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String name) {
			return name.matches("\\d+");
		}
		
	}
	
	protected static class StrainXmlFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String name) {
			return name.matches(".+\\.xml");
		}
		
	}
}