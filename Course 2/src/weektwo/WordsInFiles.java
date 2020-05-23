package weektwo;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;

    public WordsInFiles() {
        wordMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        String fname = f.getName();
        FileResource fr = new FileResource(f);

        for(String word : fr.words()) {
            if (wordMap.containsKey(word)) {
                int index = wordMap.get(word).indexOf(fname);
                if(index == -1) {
                    wordMap.get(word).add(fname);
                }
            } else {
                wordMap.put(word,new ArrayList<String>());
                wordMap.get(word).add(fname);
            }
        }
    }

    private void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
            addWordsFromFile(f);
    }

    private int maxNumber() {
        int max = -1;
        for(ArrayList<String> files: wordMap.values()) {
            int numFiles = files.size();
            if(numFiles > max)
                max = numFiles;
        }
        return max;
    }

    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();

        for (String word : wordMap.keySet()) {
            int size = wordMap.get(word).size();
            if(size == number)
                words.add(word);
        }
        return words;
    }

    private void printFilesIn(String word) {
        ArrayList<String> files = wordMap.get(word);
        for (int i=0; i<files.size(); i++) {
            System.out.println(files.get(i));
        }
    }

    public void test() {
        buildWordFileMap();
        //System.out.println(maxNumber());
        //System.out.println(wordsInNumFiles(4).size());
        printFilesIn("tree");

        //print whole map
//        for(String word : wordMap.keySet()) {
//            System.out.println(word + " in Files: " + wordMap.get(word).toString());
//        }
    }
}
