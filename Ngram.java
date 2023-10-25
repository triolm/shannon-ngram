import java.util.HashMap;

public class Ngram {
    int n;
    String first;
    HashMap<Character, Integer> lasts;
    Weightings weighter;

    public Ngram(String first) {
        n = first.length() + 1;
        this.first = first;
        lasts = new HashMap<Character, Integer>();
        weighter = new Weightings(lasts);
    }

    public int add(char c) {
        if (lasts.get(c) == null) {
            lasts.put(c, 1);
            weighter.updateTotal();
            return 1;
        }
        lasts.put(c, lasts.get(c) + 1);
        weighter.updateTotal();
        return lasts.get(c);
    }

    public char getRand() {
        return weighter.rand();
    }

}