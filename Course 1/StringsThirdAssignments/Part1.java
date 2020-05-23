
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex;
        currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        while(currIndex != -1) {
            if((currIndex-startIndex) % 3 == 0)
                return currIndex;
            
            currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        
        return dna.length();
    }
    
    /*public void testFindStopCodon() {
        String dna = "GHJATGJTAAJFTAAJID";
        System.out.println("DNA: " + dna);
        System.out.println("Index of stopCodon: " +findStopCodon(dna, 0, "TAA"));
    }*/
    
    public String findGene(String dna) {
        String temp = dna.toLowerCase();
        
        int start = temp.indexOf("atg");
        if(start == -1) // no ATG found
            return "";
        
        int taa = findStopCodon(temp,start,"taa");
        int tag = findStopCodon(temp,start,"tag");
        int tga = findStopCodon(temp,start,"tga");
        int len = temp.length();
        
        if(taa == len && tag == len && tga == len) // stopCodon not found
            return "";
            
        int minIndex = Math.min(taa,Math.min(tag,tga));
        
        return dna.substring(start,minIndex+3);
    }
    
    /*public void testFindGene() {
        String dna = "GHJATGJTAAJFTGAJIDTCAJKDF";
        //System.out.println("DNA: " + dna);
        //System.out.println("Gene: " +findGene(dna));
        dna = "GHJATGJTAAJFTGAJIDTCATGJKDFHYLOPTAAJDFD";
        System.out.println("DNA: " + dna);
        
        StorageResource genes = getAllGenes(dna);
        
        for(String s: genes.data()) {
            System.out.println(s);
        }
    }*/
    
    public StorageResource getAllGenes(String dna) {
        int start = 0;
        int end = dna.length();
        StorageResource genes = new StorageResource();
        
        while(true) {
            String gene = findGene(dna.substring(start,end));
            if(gene.isEmpty())
                break;
            
            //System.out.println("Gene: " + gene);
            genes.add(gene);
            //moving pointer to find next ATG
            start = dna.indexOf(gene,start) + gene.length();
        }
        return genes;
    }
    
    public double cgRatio(String dna) {
        int count = 0, start = 0;
        String temp = dna.toLowerCase();
        
        while(true) {
            int index = temp.indexOf("c",start);
            if(index == -1)
                break;
            start = index + 1;
            count++;
        }
        start = 0;
        while(true) {
            int index = temp.indexOf("g",start);
            if(index == -1)
                break;
            start = index + 1;
            count++;
        }
        
        return (double)count/dna.length();
    }
    
    public int countCTG(String dna) {
        int count = 0, start = 0;
        String temp = dna.toLowerCase();
        
        while(true) {
            int index = temp.indexOf("ctg",start);
            if(index == -1)
                break;
            start = index + 3;
            count++;
        }
        return count;
    }
    
    public void processGenes(StorageResource sr) {
        System.out.println("print all the Strings in sr that are longer than 9 characters");
        int countNine = 0;
        for(String s: sr.data()){
            if(s.length() > 9) {
                System.out.println(s);
                countNine++;
            }
        }
        
        System.out.println("print the number of Strings in sr that are longer than 9 characters");
        System.out.println(countNine);
        
        System.out.println("print the Strings in sr whose C-G-ratio is higher than 0.35");
        int countCG = 0;
        for(String s: sr.data()){
            if(cgRatio(s) > 0.35) {
                System.out.println(s);
                countCG++;
            }
        }
        
        System.out.println("print the number of strings in sr whose C-G-ratio is higher than 0");
        System.out.println(countCG);
        
        System.out.println("print the length of the longest gene in sr");
        int largest = Integer.MIN_VALUE;
        
        for(String s: sr.data()){
            if(s.length() > largest) {
                largest = s.length();
            }
        }
        System.out.println(largest);
        
        System.out.println("print all the Strings in sr that are longer than 60 characters");
        int countSixty = 0;
        for(String s: sr.data()){
            if(s.length() > 60) {
                System.out.println(s);
                countSixty++;
            }
        }
        
        System.out.println("print the number of Strings in sr that are longer than 60 characters");
        System.out.println(countSixty);
    }
    
    public void testProcessGenes() {
        //String dna = "GHJATGJTAAJFTGAJIDTCATGJKDFHYLOPTAAJATGTAADFD";
        
        /*String dna = "ATGCCTGCFTGATAG";
        processGenes(getAllGenes(dna));*/
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        int i = 0;
        for(String s: sr.data()){
            System.out.println(++i);
        }
        //System.out.println(dna);
        //processGenes(getAllGenes(dna));
    }
    public void test() {
        /*
        //String dna = "GHJATGJTAAJFTGAJIDTCATGJKDFHYLOPTAAJDFD";
        String dna = "atgcctgctgatag";
        System.out.println(cgRatio(dna));*/
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(countCTG(dna));
    }
}
