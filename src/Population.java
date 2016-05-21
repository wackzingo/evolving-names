/*
 * Zachariah Wingo
 * TCSS 342 Spring 2016
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population {
	/** Most fit genome. */
	private Genome mostFit;
	
	/** List of genome population. */
	private List<Genome> myPopulation;
	
	/** Size of the genome population. */
	private int populationSize;
	
	/** Primary random generator. */
	private Random random;
	
	
	/**
	 * Constructs a population of genomes.
	 * @param numGenomes the max population of genomes.
	 * @param mutationRate the rate or chance of a mutation.
	 */
	Population(final Integer numGenomes, final Double mutationRate) {
		super();
		populationSize = numGenomes;
		random = new Random();
		myPopulation = new ArrayList<Genome>();
		createPopulation(numGenomes, mutationRate);
		
	}
	
	/**
	 * Private helper method for creating an initial population of genomes.
	 * @param theStartingPopulation the max number of genomes to create.
	 * @param theMutationRate the rate or chance of mutation.
	 */
	private final void createPopulation(int theStartingPopulation, double theMutationRate) {
		for(int i = 0; i < theStartingPopulation; i++) {
			myPopulation.add(new Genome(theMutationRate));
		}
	}
	
	/**
	 * Simulates a single cycles of evolution.
	 */
	public void day() {

		// Remove half the elements which are least fit.
		for(int i = 0; i < populationSize/2; i++) {
			myPopulation.remove(myPopulation.size()-1);
		}

		// Fills the population with additional genomes.
		while (myPopulation.size() < populationSize) {
			 
			if(random.nextBoolean()) {
				Genome genome = new Genome(myPopulation.get(random.nextInt(populationSize/2)));
				genome.mutate();
				myPopulation.add(genome);
			} else {
				Genome genomeOne = new Genome(myPopulation.get(random.nextInt(populationSize/2)));
				Genome genomeTwo = new Genome(myPopulation.get(random.nextInt(populationSize/2)));
				genomeOne.crossover(genomeTwo);
				genomeOne.mutate();
				myPopulation.add(genomeOne);
			}
		}


		// Sort the list of genomes.
		myPopulation.sort(new Comparator<Genome>() {
			@Override
			public int compare(Genome t1, Genome t2) {
				if(t1.fitness() < t2.fitness()) {
					return -1;
				} else if (t1.fitness() == t2.fitness()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		// Get Most Fit
		mostFit = myPopulation.get(0);
	}
	
	/**
	 * Returns the most fit genome.
	 * @return returns the most fit genome as a genome.
	 */
	public Genome mostFit() {
		return mostFit;
	}
	
	/**
	 * Returns the size of the population.
	 * @return returns the size of the poulation.
	 */
	public int populationSize() {
		return populationSize;
	}
	
	/**
	 * Returns a list of genomes in the poulation.
	 * @return returns a list of the population.
	 */
	public List<Genome> getPopulation() {
		return myPopulation;
	}
}
