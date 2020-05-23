package weekone;

public class TestCaesarCipher {
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

    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26-(4-maxDex);

        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }

    public void simpleTests() {
        //FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + cc.decrypt(encrypted));
        System.out.println("Auto Decrypted: " + breakCaesarCipher(encrypted));
    }
}
