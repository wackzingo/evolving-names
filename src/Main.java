/*
 * Zachariah Wingo
 * TCSS 342 Spring 2016
 */
public class Main {

	public static void main(String[] args) {

		////**** Uncomment next three lines to run test methods. ****\\\\
		//Main test1 = new Main();
		//test1.testGenome();
		//test1.testPopulation();
		
		Population p1 = new Population(100, 0.05);
		long startTime = System.currentTimeMillis();	
		int i = 1;
		
		// Evolves our names one virtual day at a time.
		do {
			p1.day();
			i++;
			System.out.println("(" + p1.mostFit().toString() + ")");
		} while(p1.mostFit().fitness() > 0);
		
		
		// Displays number of generations and running time.
		System.out.println("Generations: " + i);
		System.out.println("Running Time: " + (System.currentTimeMillis()-startTime) + " miliseconds");
	}

	/**
	 * Private method for testing the Genome class.
	 */
	private void testGenome() {
		Genome g1 = new Genome(0.05);
		
		// Should print out 'A' as default value and fitness of 49
		System.out.println("Initial Test: " + g1.toString());
		
		// Let's mutate it 100 times we should see some mutation.
		for(int i = 0; i < 100; i++) {
			g1.mutate();
		}
		System.out.println("Mutation Test: " + g1.toString());
		
		// Test the fitness. If there are no matching values we should see
		// 50-N where n is hte number of characters in the genome. We
		// Will display TRUE if no characters match. If false we should see
		// one or more matching characters.
		System.out.print("Fitness Test: " + g1.toString());
		System.out.println((g1.fitness()+g1.getGenomeType().length() == 50) ? " FALSE":" TRUE");
		
		
		// Let's test the crossover. Create a genome, mutate it 100 times,
		// perform a crossover and see the results.
		Genome g2 = new Genome(0.05);
		for(int i = 0; i < 100; i++) {
			g2.mutate();
		}
		System.out.println("Genome 1: " + g1.toString());
		System.out.println("Genome 2: " + g2.toString());
		g1.crossover(g2);
		System.out.println("Crossover: " + g1.toString());
		
		// Last let's check the clone constructor. Should have matching values
		// but g1.equals(g3) should return false.
		Genome g3 = new Genome(g1);
		System.out.println("Genome 1: " + g1.toString());
		System.out.println("Genome 2: " + g3.toString());
		System.out.println("Are they equal: " + g1.equals(g3));
	}
	
	private void testPopulation() {
		//Create a population of different sizes and see if it creates the correct number
		Population p1 = new Population(50, 0.5);
		System.out.println("Population: " + p1.populationSize());
		
		// Let's create a small population of 5 genomes, mutate it for 10 50 days,
		// then see if most fit matches the one at the top of the list.
		Population p2 = new Population(5, 0.5);
		for(int i = 0; i < 50; i++) {
			p2.day();
		}
		
		System.out.println("Most Fit: " + p2.mostFit().toString());
		for(Genome g : p2.getPopulation()) {
			System.out.println(g.toString());
		}

	}
}
