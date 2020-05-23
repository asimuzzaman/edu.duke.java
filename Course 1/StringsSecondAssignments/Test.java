
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            System.out.println((index+1)+","+(index+4));
            if(index >= input.length() - 4) {
                break;
            }
            
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
       public void test() {
        //no code yet
        //findAbc("habchi");
        //findAbc("abcdabc");
        findAbc("abc");
    }
}
