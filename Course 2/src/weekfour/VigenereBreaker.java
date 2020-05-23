package weekfour;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();

        for(int i=whichSlice; i<message.length(); i+=totalSlices) {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        for (int i=0; i<klength; i++) {
            String slice = sliceString(encrypted,i,klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

//    OLD method 1
//    public void breakVigenere () {
//        FileResource fr = new FileResource();
//        String encrypted = fr.asString();
//        int[] key = tryKeyLength(encrypted,4,'e');
//        VigenereCipher vc = new VigenereCipher(key);
//        String decrypted = vc.decrypt(encrypted);
//        System.out.println("Decrypted: " + decrypted);
//    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet();

        for(String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for(String word : message.split("\\W+")) {
            if(dictionary.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = -1, rightKeyLength = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        System.out.println("MOST COMMON: " + mostCommon);

        for(int i=1; i<=100; i++) {
            int[] key = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int curr = countWords(decrypted,dictionary);
            if(curr > max) {
                max = curr;
                rightKeyLength = i;
            }
            //TEST
//            if(i==38)
//                System.out.println("DEBUG: " + curr);
        }
        System.out.println("DEBUG: Valid words = " + max +" | Key Length = " + rightKeyLength); // TEST

        int[] key = tryKeyLength(encrypted,rightKeyLength,'e');
        VigenereCipher vc = new VigenereCipher(key);
        return vc.decrypt(encrypted);
    }

//    OLD Method 2
//    public void breakVigenere() {
//        FileResource fr = new FileResource();
//        String encrypted = fr.asString();
//        FileResource dict = new FileResource();
//        HashSet<String> dictionary = readDictionary(dict);
//        System.out.println(breakForLanguage(encrypted,dictionary));
//    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character,Integer> charCounter = new HashMap();

        for(String word : dictionary) {
            for(char c : word.toCharArray()) {
                if(charCounter.containsKey(c))
                    charCounter.put(c,charCounter.get(c)+1);
                else
                    charCounter.put(c,1);
            }
        }

        int max = -1;
        char commonChar = ' ';
        for (char c : charCounter.keySet()) {
            int count = charCounter.get(c);
            if(count > max) {
                max = count;
                commonChar = c;
            }
        }
        return commonChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        int most = -1;
        String bestLang = null;

        for(String lang : languages.keySet()) {
            String decrypted = breakForLanguage(encrypted,languages.get(lang));
            int count = countWords(decrypted,languages.get(lang));

            if(count > most) {
                most = count;
                bestLang = lang;
            }
        }
        System.out.println("Identified message: " + bestLang);
        System.out.println("Decrypted:");
        System.out.println(breakForLanguage(encrypted,languages.get(bestLang)));
    }

    public void breakVigenere() {
        //for message
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        //for dictionary
        String[] langs = {"Danish",  "Dutch",  "English",  "French",  "German",  "Italian",  "Portuguese",  "Spanish"};
        HashMap<String,HashSet<String>> allDict = new HashMap();

        for (String lang : langs) {
            FileResource dict = new FileResource("data/VigenereCipher/program/dictionaries/"+lang);
            allDict.put(lang,readDictionary(dict));
            System.out.println("INFO: " +lang+" reading done.");
        }

        breakForAllLangs(encrypted,allDict);
    }
}
