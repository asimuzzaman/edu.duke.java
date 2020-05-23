package weektwo;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedWords; //for tracking used words
    private int countReplaced;
    private ArrayList<String> considerList; //to track considered LABELS

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/GladLibs/data";

    public GladLibMap(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        considerList = new ArrayList();
        usedWords = new ArrayList();
        countReplaced = 0;
        myRandom = new Random();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color",
                "country", "name", "animal", "timeframe", "verb", "fruit"};

        for(String item : categories) {
            ArrayList<String> list = readIt(source+"/"+item+".txt");
            myMap.put(item,list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        //generating unique word
        String sub = "";
        String label = w.substring(first+1,last);
        //memorizing used labels
        int find = considerList.indexOf(label);
        if(find == -1)
            considerList.add(label);

        while(true) {
            sub = getSubstitute(label);
            if(!isUsed(sub))
                break;
        }
        countReplaced++;
        return prefix+sub+suffix;
    }

    private boolean isUsed(String word) {
        if(usedWords.indexOf(word) == -1) {
            usedWords.add(word);
            return false;
        }
        return true;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap() {
        int total = 0;
        for(ArrayList list : myMap.values()) {
            total += list.size();
        }
        return total;
    }

    private int totalWordsConsidered() {
        int total = 0;
        for(String label : considerList) {
            total += myMap.get(label).size();
        }
        return total;
    }

    public void makeStory(){
        countReplaced = 0;
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/GladLibs/data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("Total replaced: "+countReplaced);
        System.out.println("Total possible: " +totalWordsInMap());
        System.out.println("Total considered: " + totalWordsConsidered());
    }
}