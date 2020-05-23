
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String tempDna = dna.toLowerCase();
        startCodon = startCodon.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
        
        int start = tempDna.indexOf(startCodon);
        if(start == -1)
            return "";
        int stop = tempDna.indexOf(stopCodon, start+3);
        if(stop == -1)
            return "";
        
        if((stop-start)%3 == 0)
            return dna.substring(start,stop+3);
        
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "HGATGJKJFDFTAAIOKD";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "HGATGJKJFFTAAIOKD";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "gatgctataat";
        System.out.println("DNA: "+ dna);
        System.out.println("Gene: "+findSimpleGene(dna,"ATG","TAA"));
    }
}
