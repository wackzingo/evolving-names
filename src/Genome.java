/*
 * Zachariah Wingo
 * TCSS 342 Spring 2016
 */
import java.util.Random;

public class Genome {
	
	/** Possible Characters. */
	private static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
											 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
											 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-'}; 
	
	/** Default target name. */
	private static final String DEFAULT_NAME = "CHRISTOPHER PAUL MARRIOTT";
	
	/** Max mutation rate. */
	private static final double MAX_MUTATION_RATE= 1.0;
	
	/** Min mutation rate. */
	private static final double MIN_MUTATION_RATE = 0.0;
	
	/** Default mutation rate. */
	private static final double DEFAULT_MUTATION_RATE = 0.05;
	
	/** Chance modifier. */
	private static final int CHANCE_MULTIPLIER = 100;
	
	/** Minimum size of Genome String. */
	private static final int MIN_SIZE = 2;

	/** Evolution target. */
	private final String myTarget;
	
	/** The Genome type. */
	private String myGenomeType;
	
	/** The Mutation Rate. */
	private double myMutationRate;
	
	/* Random Object. */
	private final Random myRandom;
	
	
	
	/**
	 * Constructs a Genome that will evolve to a target name at a specified mutation raDEFAULTte.
	 * @param mutationRate accepts a value between 0.0 and 1.0 as the mutation rate.
	 */
	Genome(final double mutationRate) {
		super();
		myTarget = DEFAULT_NAME;
		myGenomeType = "A";
		myRandom = new Random();
		setMutationRate(mutationRate);
	}
	
	
	/**
	 * Copy constructor creates a new Genome.
	 * @param gene creates a new Genome with the supplied Genome.
	 */
 	Genome(final Genome gene) {
 		super();
 		myGenomeType = gene.myGenomeType;
 		myMutationRate = gene.myMutationRate;
 		myTarget = gene.myTarget;
 		myRandom = gene.myRandom;
 	}
 	
 	
 	/**
 	 * Sets the mutation rate of the Genome.
 	 * @param theMutationRate is a value between 0.0 and 1.0 to represent the mutation rate.
 	 */
	private final void setMutationRate(final double theMutationRate) throws IllegalArgumentException {
		if (theMutationRate >= MIN_MUTATION_RATE && theMutationRate <= MAX_MUTATION_RATE) {
			myMutationRate = theMutationRate;
		} else {
			myMutationRate = DEFAULT_MUTATION_RATE;
		}
	}
	
	/**
	 * Mutates the string by randomly adding/deleting or chancing characters
	 * according to the mutation rate.
	 * 
	 */
	public final void mutate() {
		
		// StringBuilder allows to modify the string.
		final StringBuilder tmpStr = new StringBuilder(myGenomeType);
		
		// Inserts a random character according to the mutationRate chance.
		if (myRandom.nextInt(CHANCE_MULTIPLIER) <= myMutationRate * CHANCE_MULTIPLIER) {
			tmpStr.insert(myRandom.nextInt(tmpStr.length()+1), ALPHABET[myRandom.nextInt(28)]);
		}
		
		// Deletes a random character according to the mutationRate chance.
		if (myRandom.nextInt(CHANCE_MULTIPLIER) <= myMutationRate * CHANCE_MULTIPLIER && myGenomeType.length() >= MIN_SIZE) {
			tmpStr.deleteCharAt(myRandom.nextInt(myGenomeType.length()));
		}
		
		// Loop through each character and possibly change according to the mutationRate chance.
		for (int i = 0; i < tmpStr.length(); i++) {
			if(myRandom.nextInt(CHANCE_MULTIPLIER) <= myMutationRate * CHANCE_MULTIPLIER) {
				tmpStr.setCharAt(i, ALPHABET[myRandom.nextInt(28)]);
			}

		}
		
		myGenomeType = tmpStr.toString();
	}
	
	
	/**
	 * Crossover takes two Genomes and merges with this Genome.
	 */
	public final void crossover(final Genome other) {
		final char[] thisGenome = this.myGenomeType.toCharArray();
		final char[] theOtherGenome = other.myGenomeType.toCharArray();
		final int length = Math.max(thisGenome.length, theOtherGenome.length);
		StringBuilder newGenome = new StringBuilder();
		
		for(int i=0; i < length; i++) {
			
			// First choose a random parent.
			if(myRandom.nextBoolean()) {
				
				// If we've reached the end of the parent we break from the loop,
				// otherwise we can just copy the next character over to newGenome.
				if(i >= thisGenome.length)
					break;
				else
					newGenome.append(thisGenome[i]);
				
			} else {
				
				if(i >= theOtherGenome.length)
					break;
				else
					newGenome.append(theOtherGenome[i]);
				
			}
		}
		
		myGenomeType = newGenome.toString();
	}
	
	
	/**
	 * Calculates the fitness of the string to the target string.
	 */
	public final Integer fitness() {
		final int n = myGenomeType.length();
		final int m = myTarget.length();
		final int l = Math.max(n, m);
		final int z = Math.min(n, m);
		int f = Math.abs(m-n);
		
		for(int i = 0; i < l; i++) {
			if(i < z && !(myGenomeType.charAt(i) == myTarget.charAt(i))) {
				f++;
			} else if (i >= z) {
				f++;
			}
		}
		return f;
	}
	
	/**
	 * Get Genome Type.
	 * @return returns type of Genome.
	 */
	public final String getGenomeType() {
		return myGenomeType;
	}
	
	
	/**
	 * Gets the mutation rate.
	 * @return returns the mutation rate as a double.
	 */
	public final double getMutationRate() {
		return myMutationRate;
	}
	
	
	/**
	 * Gets the target name.
	 * @return returns the target name as a String.
	 */
	public final String getTargetName() {
		return myTarget;
	}
	
	
	/**
	 * To String
	 */
	@Override
	public String toString() {
		return "\"" + myGenomeType + "\", " +fitness();
	}
	
	
}
