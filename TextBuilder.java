import java.util.HashMap;

public class TextBuilder<T> {
    HashMap<String, Ngram<T>> h;
    String built;

    public TextBuilder(HashMap<String, Ngram<T>> h) {
        this.h = h;
        built = "";

    }

    public String getBuilt() {
        return built;
    }

    public void generateNext() {
        built += getNextCharacter(built.substring(built.length() > 9 ? built.length() - 10 : 0, built.length()), 0.01,
                h);
    }

    public  Ngram<T> getNextNgram(String ngramSubstring, double noise, HashMap<String, Ngram<T>> h) {
        Ngram<T> ngram = null;
        while (ngram == null && ngramSubstring.length() > 0) {
            if (Math.random() < noise * ((2 * ngramSubstring.length()) - 1)) {
                continue;
            }
            ngram = h.get(ngramSubstring);
            if (ngram != null) {
                return ngram;
            }
            ngramSubstring = ngramSubstring.substring(1);
        }
        System.out.println();
        return h.get("\n");
    }

    public T getNextCharacter(String ngramSubstring, double noise, HashMap<String, Ngram<T>> h) {
        return getNextNgram(ngramSubstring, noise, h).getRand();
    }


}
