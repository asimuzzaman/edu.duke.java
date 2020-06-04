package weekthree2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }

//    public void setTraining(String s){
//        myText = s.trim();
//    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        sb.append(key);

        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0)
                break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "MarkovModel of order 1";
    }
}
