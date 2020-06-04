package weekthree2;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> mapped;

    public EfficientMarkovModel(int N) {
        this.N = N;
        mapped = new HashMap<>();
    }

    private void buildMap() {
        mapped.clear();
        int pos = 0;
        while (true) {
            String key = myText.substring(pos,pos+N);

            if (mapped.containsKey(key)) {
                if ((pos+N+1) <= myText.length())
                    mapped.get(key).add(myText.substring(pos+N,pos+N+1));
            } else {
                ArrayList<String> list = new ArrayList<>();
                if ((pos+N+1) <= myText.length())
                    list.add(myText.substring(pos+N,pos+N+1));
                mapped.put(key,list);
            }
            pos++;
            if ((pos+N)>myText.length())
                break;
        }
    }

    @Override
    protected ArrayList<String> getFollows(String key) {
        return mapped.get(key);
    }

    @Override
    public String getRandomText(int numChars) {
        buildMap();
        printHashMapInfo();

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

    @Override
    public String toString() {
        return "Markov model of "+N;
    }

    private void printHashMapInfo() {
        int largest = 0;

        for (String key : mapped.keySet()) {
            if (mapped.get(key).size() > largest)
                largest = mapped.get(key).size();

//            System.out.println("Key: " + key);
//            System.out.println("Values: "+ mapped.get(key));
        }
        System.out.println("Number of keys: "+mapped.size());
        System.out.println("Largest value size: "+largest);
//        System.out.println("Keys with largest value: ");
//        for (String key : mapped.keySet()) {
//            if (mapped.get(key).size() == largest)
//                System.out.print(key+" ");
//        }
        System.out.println();
    }
}
