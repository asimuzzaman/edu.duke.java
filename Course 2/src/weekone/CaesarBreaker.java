package weekone;

import edu.duke.FileResource;

public class CaesarBreaker {
    public int[] countLetters(String message) {
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

    public int maxIndex(int[] values) {
        int maxDex = 0;
        for(int i=0; i<values.length;i++) {
            if(values[i] > values[maxDex])
                maxDex = i;
        }
        return maxDex;
    }

    public String decrypt(String encrypted) {
        CaesarCipherOld cc = new CaesarCipherOld();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26-(4-maxDex);

        return cc.encrypt(encrypted, 26-dkey);
    }

    public String halfOfString(String message, int start) {
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

    public String decryptTwoKeys(String encrypted) {
        String s1 = halfOfString(encrypted,0);
        String s2 = null;
        if (encrypted.length() > 1)
            s2 = halfOfString(encrypted,1);
        int maxDex1 = getKey(s1);
        int maxDex2 = getKey(s2);

        int dkey1 = maxDex1 - 4;
        if(maxDex1 < 4)
            dkey1 = 26-(4-maxDex1);

        int dkey2 = maxDex2 - 4;
        if(maxDex2 < 4)
            dkey2 = 26-(4-maxDex2);

        CaesarCipherOld cc = new CaesarCipherOld();

        System.out.println("key1: "+dkey1+", key2: "+dkey2);
        return cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
    }

    public void test() {
        FileResource fr = new FileResource();
        //weekone.CaesarCipherOld cc = new weekone.CaesarCipherOld();
//
//        String encrypted = cc.encrypt(fr.asString(), 23);
//        System.out.println("Encrypted: " + encrypted);
//        System.out.println("Decrypted: " + decrypt(encrypted));

        //Two keys
//        String encrypted = cc.encryptTwoKeys(fr.asString(), 23, 2);
//        System.out.println("Encrypted: " + encrypted);
        //System.out.println(decryptTwoKeys(fr.asString()));
//        weekone.CaesarCipherOld cc = new weekone.CaesarCipherOld();
//        System.out.println(cc.encrypt(fr.asString(),12));
//        System.out.println(decrypt(cc.encrypt(fr.asString(),12)));
        System.out.println(decryptTwoKeys(fr.asString()));
    }
}
