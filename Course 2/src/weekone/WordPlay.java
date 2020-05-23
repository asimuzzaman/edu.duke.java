package weekone;

public class WordPlay {

    public boolean isVowel(char ch) {
        char temp = Character.toLowerCase(ch);

        switch (temp) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);

        for (int i=0; i<sb.length(); i++) {
            if (isVowel(sb.charAt(i)))
                sb.setCharAt(i, ch);
        }
        return sb.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        ch = Character.toLowerCase(ch);

        for (int i=0; i<sb.length(); i++) {
            char currChar = Character.toLowerCase(sb.charAt(i));
            if(currChar == ch) {
                if((i+1)%2 == 0)
                    sb.setCharAt(i,'+');
                else
                    sb.setCharAt(i,'*');
            }
        }

        return sb.toString();
    }
}
