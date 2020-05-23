
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if(start == -1)
            return "";
        int stop = dna.indexOf("TAA", start+3);
        if(stop == -1)
            return "";
        
        if((stop-start)%3 == 0)
            return dna.substring(start,stop+3);
        
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna));
        /*
        dna = "HGATGJKJFFTAAIOKD";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna));
        
        dna = "HGATGJKRJFFTAIOKD";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna));
        */
    }
}
