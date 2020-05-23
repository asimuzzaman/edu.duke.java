package weektwo;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();

        for(String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }

    private int findIndexOfMax() {
        int largest = -1;
        int largestIndex = -1;

        for(int i=0; i<myFreqs.size(); i++) {
            if(myFreqs.get(i) > largest) {
                largest = myFreqs.get(i);
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public void test() {
        findUnique();
        System.out.println("#Unique words: " + myWords.size());
        for (int i=0; i<myWords.size();i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +myWords.get(max) +" "+myFreqs.get(max));
    }
}
