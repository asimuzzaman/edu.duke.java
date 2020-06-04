package weekthree4;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
//        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        int index = myRandom.nextInt(myText.length-myOrder);
//        String key = myText[index];
        WordGram kGram = new WordGram(myText,index,myOrder);

//        sb.append(key);
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
//            key = next;
            kGram = kGram.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;

        while (true) {
            int index = indexOf(myText,kGram,pos);
//            System.out.println("WG: "+kGram+" ,index = "+index);
            if (index == -1 || (index+1)>=myText.length)
                break;
            follows.add(myText[index+kGram.length()]);
            pos = index + 1;
        }

        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i=start; i<words.length-target.length(); i++) {
            WordGram compare = new WordGram(words,i,target.length());

            if (target.equals(compare))
                return i;
        }
        return -1;
    }
}
