
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
    
    public void testFindStopCodon() {
        String dna = "GHJATGJTAAJFTAAJID";
        System.out.println("DNA: " + dna);
        System.out.println("Index of stopCodon: " +findStopCodon(dna, 0, "TAA"));
    }
    
    public String findGene(String dna) {
        int start = dna.indexOf("ATG");
        if(start == -1) // no ATG found
            return "";
        
        int taa = findStopCodon(dna,start,"TAA");
        int tag = findStopCodon(dna,start,"TAG");
        int tga = findStopCodon(dna,start,"TGA");
        int len = dna.length();
        
        if(taa == len && tag == len && tga == len) // stopCodon not found
            return "";
            
        int minIndex = Math.min(taa,Math.min(tag,tga));
        
        return dna.substring(start,minIndex+3);
    }
    
    public void testFindGene() {
        String dna = "GHJATGJTAAJFTGAJIDTCAJKDF";
        //System.out.println("DNA: " + dna);
        //System.out.println("Gene: " +findGene(dna));
        dna = "GHJATGJTAAJFTGAJIDTCATGJKDFHYLOPTAAJDFD";
        System.out.println("DNA: " + dna);
        printAllGenes("AATGCTAACTAGCTGACTAAT");
    }
    
    public void printAllGenes(String dna) {
        int start = 0;
        int end = dna.length();
        
        while(true) {
            String gene = findGene(dna.substring(start,end));
            if(gene.isEmpty())
                break;
            
            System.out.println("Gene: " + gene);
            //moving pointer to find next ATG
            start = dna.indexOf(gene,start) + gene.length();
        }
    }
}
