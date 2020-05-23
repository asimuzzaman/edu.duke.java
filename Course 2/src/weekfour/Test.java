package weekfour;

import edu.duke.FileResource;

public class Test {
    public static void main(String[] args) {
//        FileResource fr = new FileResource();

//        CaesarCipher cc = new CaesarCipher(15);
//        System.out.println(cc.encrypt(fr.asString()));
//        System.out.println(cc.encryptLetter('C'));

//        CaesarCracker cb = new CaesarCracker('a');
//        System.out.println(cb.decrypt(fr.asString()));

//        int[] key = {17, 14, 12, 4};
//        VigenereCipher vc = new VigenereCipher(key);
//        System.out.println(vc.encrypt(fr.asString()));

        VigenereBreaker vb = new VigenereBreaker();
//        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));

//        int[] keyArr = vb.tryKeyLength(fr.asString(),4,'e');
//        for (int i=0;i<keyArr.length;i++) {
//            System.out.print(keyArr[i] +",");
//        }

//        System.out.println(vb.readDictionary(fr).size());

        vb.breakVigenere();

        //Part 3
//        System.out.println(vb.mostCommonCharIn(vb.readDictionary(fr)));
    }
}
