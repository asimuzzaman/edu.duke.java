package weekthree4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> mapped;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        mapped = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        buildMap();
        printHashMapInfo();

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram kGram = new WordGram(myText,index,myOrder);

        sb.append(kGram.toString());
        sb.append(" ");

        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
//			System.out.println(kGram + " ,val: "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");

            kGram = kGram.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        return mapped.get(kGram);
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i=start; i<words.length-target.length(); i++) {
            WordGram compare = new WordGram(words,i,target.length());

            if (target.equals(compare))
                return i;
        }
        return -1;
    }

    private void buildMap() {
        mapped.clear();
        int pos = 0;
        while (true) {
            WordGram kGram = new WordGram(myText,pos,myOrder);

            if (mapped.containsKey(kGram)) {
                if ((pos+myOrder+1) <= myText.length)
                    mapped.get(kGram).add(myText[pos+myOrder]);
            } else {
                ArrayList<String> list = new ArrayList<>();
                if ((pos+myOrder+1) <= myText.length)
                    list.add(myText[pos+myOrder]);
                mapped.put(kGram,list);
            }
            pos++;
            if ((pos+myOrder)>myText.length)
                break;
        }
    }

    private void printHashMapInfo() {
        int largest = 0;

        for (WordGram wGram : mapped.keySet()) {
            if (mapped.get(wGram).size() > largest)
                largest = mapped.get(wGram).size();

//            System.out.println("Key: " + wGram);
//            System.out.println("Values: "+ mapped.get(wGram));
        }
        System.out.println("Number of keys: "+mapped.size());
        System.out.println("Largest value size: "+largest);
//        System.out.println("Keys with largest value: ");
//        for (WordGram wGram : mapped.keySet()) {
//            if (mapped.get(wGram).size() == largest)
//                System.out.println(wGram);
//        }
        System.out.println();
    }
}
