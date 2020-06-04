package weekthree4;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
//        String st = "This is a very simple test. This is indeed a very easy test.";

        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200);

        MarkovWord markov = new MarkovWord(5);
        runModel(markov,st,1000,844);
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
        FileResource fr = new FileResource();
        String st = "this is a test yes this is really a test yes a test this is wow";
        st = fr.asString();
        st = st.replace('\n', ' ');

        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        markov.setTraining(st);
        markov.setRandom(65);
//        System.out.println(markov.getRandomText(1000));
        markov.getRandomText(1000);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovWord m1 = new MarkovWord(2);
        EfficientMarkovWord m2 = new EfficientMarkovWord(2);

        long time = System.nanoTime();
        runModel(m1,st,100,42);
        System.out.println("Time required 1: " + (System.nanoTime()-time));
        time = System.nanoTime();
        runModel(m2,st,100,42);
        System.out.println("Time required 2: " + (System.nanoTime()-time));
    }
}
