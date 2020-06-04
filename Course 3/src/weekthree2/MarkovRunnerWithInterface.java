package weekthree2;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 10);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 10);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 10);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 10);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap() {
//    	String st = "yes-this-is-a-thin-pretty-pink-thistle";
//    	String st = "this is a text.";
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');

    	EfficientMarkovModel em = new EfficientMarkovModel(5);
    	runModel(em,st,1000,615);
	}

	public void compareMethods() {
    	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovModel m1 = new MarkovModel(2);
		EfficientMarkovModel m2 = new EfficientMarkovModel(2);

		System.out.println("TIME : " + System.nanoTime());
		runModel(m1,st,1000,42);
		System.out.println("TIME : " + System.nanoTime());
		runModel(m2,st,1000,42);
		System.out.println("TIME : " + System.nanoTime());
	}

	public void testEfficientMarkov() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');

		EfficientMarkovModel markov = new EfficientMarkovModel(5);
//		markov.setTraining(st);
//		markov.setRandom(792);
//		markov.getRandomText(1000);
		runModel(markov,st,1000,531);
	}
}
