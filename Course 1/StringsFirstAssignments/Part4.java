import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author Md. Asimuzzaman
 * @version (a version number or a date)
 */
public class Part4 {
    public void findYoutube() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String s: ur.lines()) {
            String temp = s.toLowerCase();
            
            int index = temp.indexOf("youtube.com");
            if(index != -1) { //youtube.com exists
                int endQuote = temp.indexOf("\"",index);
                
                if(endQuote != -1) {
                   int startQuote = temp.lastIndexOf("\"",endQuote-1);
                   
                   if(startQuote != -1) {
                       //System.out.println(startQuote +" "+endQuote+ " "+temp);
                       System.out.println(s.substring(startQuote+1,endQuote));
                   }
                }
            }
        }
    }
}
