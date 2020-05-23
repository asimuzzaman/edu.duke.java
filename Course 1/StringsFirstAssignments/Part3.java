
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        
        if(index1 == -1) { //not found
            return false;
        } else {
            int index2 = stringb.indexOf(stringa,index1+stringa.length());
            
            if(index2 == -1)
                return false;
            else
                return true;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        
        if(index == -1)
            return stringb;
        
        return stringb.substring(index+stringa.length());
    }
    
    public void testing() {
        String stringa = "aa";
        String stringb = "abaaa";
        System.out.println("StrA: " + stringa);
        System.out.println("StrB: " + stringb);
        System.out.println("Result: "+ twoOccurrences(stringa,stringb));
        
        String strA = "zoo";
        String strB = "forest";
        System.out.println("The part of the string after "+strA+" in "+strB+" is "+lastPart(strA,strB)+".");
    }
}
