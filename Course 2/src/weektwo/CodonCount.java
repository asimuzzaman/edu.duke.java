package weektwo;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String,Integer> map;

    public CodonCount() {
        map = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna) {
        map.clear();
        while(true) {
            if ((start+3) > dna.length())
                break;
            String codon = dna.substring(start,start+3);
            start += 3;

            if(map.containsKey(codon)) {
                map.put(codon,map.get(codon)+1);
            } else
                map.put(codon,1);
        }
    }

    private String getMostCommonCodon() {
        int largest = -1;
        String common = null;

        for(String s: map.keySet()) {
            if(map.get(s) > largest) {
                largest = map.get(s);
                common = s;
            }
        }
        return common;
    }

    private void printCodonCounts(int start, int end) {
        for (String s: map.keySet()) {
            int count = map.get(s);
            if(count >= start && count <= end)
                System.out.println(s + " " + count);
        }
    }

    public void test() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();

        for (int i=0; i<3; i++) {
            buildCodonMap(i,dna);
            System.out.println("Reading frame starting with "+i+" results in "+map.size()+" unique codons");
            System.out.println("and most common codon is "+getMostCommonCodon()+" with count "+map.get(getMostCommonCodon()));
            printCodonCounts(7,7);
        }
    }
}
