
/**
 * Write a description of Part2 here.
 * 
 * @author Md. Asimuzzaman
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0, i = 0;
        
        while(true) {
            i = stringb.indexOf(stringa,i);
            
            if(i == -1)
                break;
            i += stringa.length();
            count ++;
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
    }
}
