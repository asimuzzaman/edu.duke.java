package weekone;

public class PhraseFilter implements Filter {
    private String type, phrase, name;

    public PhraseFilter(String type, String phrase) {
        this.type = type;
        this.phrase = phrase;
    }

    public PhraseFilter(String type, String phrase, String name) {
        this.type = type;
        this.phrase = phrase;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if (type.equals("start"))
            return qe.getInfo().startsWith(phrase);
        if (type.equals("end"))
            return qe.getInfo().endsWith(phrase);
        if (type.equals("any"))
            return qe.getInfo().contains(phrase);
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
