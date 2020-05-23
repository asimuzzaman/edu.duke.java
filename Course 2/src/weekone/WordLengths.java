package weekone;

import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] count) {
        for(String word : resource.words()) {
            int len = getLength(word);
            if(len >= count.length)
                count[count.length-1]++;
            else if(len >= 0)
                count[len]++;
        }
    }

    private int getLength(String s) {
        int len = s.length();

        if (!Character.isLetter(s.charAt(len-1)))
            len--;
        if (!Character.isLetter(s.charAt(0)))
            len--;

        return len;
    }

    public int indexOfMax(int[] values) {
        int largest = Integer.MIN_VALUE;
        int largeIndex = -1;

        for(int i=0;i<values.length;i++) {
            if(largest < values[i]) {
                largeIndex = i;
                largest = values[i];
            }
        }
        return largeIndex;
    }

    public void test() {
        FileResource fr = new FileResource();
        int[] count = new int[31];
        countWordLengths(fr, count);
        for (int i=0; i<count.length;i++)
            System.out.println(i+" "+count[i]);

        System.out.println(indexOfMax(count));
    }
}
