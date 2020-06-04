package weekthree;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;

    public MarkovModel(int N) {
        myRandom = new Random();
        this.N = N;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
//        if (myText == null){
//            return "";
//        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);

        for(int k=0; k < numChars-N; k++){
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

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;

        while (true) {
            int index = myText.indexOf(key,pos);
            if (index == -1 || (index+key.length())>=myText.length())
                break;
            follows.add(myText.substring(index+key.length(),index+key.length()+1));
            pos = index + 1;
        }
        return follows;
    }
}
