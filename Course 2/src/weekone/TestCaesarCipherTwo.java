package weekone;

public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i=0; i<message.length();i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1)
                counts[dex]++;
        }
        return counts;
    }

    private int maxIndex(int[] values) {
        int maxDex = 0;
        for(int i=0; i<values.length;i++) {
            if(values[i] > values[maxDex])
                maxDex = i;
        }
        return maxDex;
    }

    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();

        for (int i=start;i<message.length();i+=2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int getKey(String s) {
        int[] freq = countLetters(s);
        return maxIndex(freq);
    }

    private String breakCaeserCipher(String input) {
        String s1 = halfOfString(input,0);
        String s2 = null;
        if (input.length() > 1)
            s2 = halfOfString(input,1);
        int maxDex1 = getKey(s1);
        int maxDex2 = getKey(s2);

        int dkey1 = maxDex1 - 4;
        if(maxDex1 < 4)
            dkey1 = 26-(4-maxDex1);

        int dkey2 = maxDex2 - 4;
        if(maxDex2 < 4)
            dkey2 = 26-(4-maxDex2);

        CaesarCipherTwo cc = new CaesarCipherTwo(26-dkey1,26-dkey2);

        System.out.println("key1: "+dkey1+", key2: "+dkey2);
        return cc.encrypt(input);
    }

    public void simpleTests() {
        //FileResource fr = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc.encrypt(input);
        System.out.println("String: " + input);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + cc.decrypt(encrypted));
        System.out.println("Auto Decrypted: " + breakCaeserCipher(encrypted));
    }
}
