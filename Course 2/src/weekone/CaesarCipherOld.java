package weekone;

public class CaesarCipherOld {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet = alphabet.substring(key)+
                alphabet.substring(0,key);

        for(int i = 0; i < encrypted.length(); i++) {

            char currChar = encrypted.charAt(i);

            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if(idx != -1){

                char newChar = shiftedAlphabet.charAt(idx);

                if(Character.isUpperCase(currChar))
                    encrypted.setCharAt(i, newChar);
                else
                    encrypted.setCharAt(i,Character.toLowerCase(newChar));
            }

        }

        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet1 = alphabet.substring(key1)+
                alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+
                alphabet.substring(0,key2);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if(idx != -1){
                char newChar;
                if (i%2 == 0)
                    newChar = shiftedAlphabet1.charAt(idx);
                else
                    newChar = shiftedAlphabet2.charAt(idx);

                if(Character.isUpperCase(currChar))
                    encrypted.setCharAt(i, newChar);
                else
                    encrypted.setCharAt(i,Character.toLowerCase(newChar));
            }
        }

        return encrypted.toString();
    }

    public void test() {
        CaesarCipherOld ciph = new CaesarCipherOld();

        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        //String encrypted = cyph.encrypt(message, key);
        //System.out.println("key is " + key + "\n" + encrypted);
        //System.out.println(cyph.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));

        System.out.println(ciph.encryptTwoKeys(message,21, 8));
        //System.out.println(ciph.encryptTwoKeys("First Legion", 23, 17));
    }
}
