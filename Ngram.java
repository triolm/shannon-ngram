import java.util.HashMap;

public class Ngram<T> {
    int n;
    String first;
    HashMap<T, Integer> lasts;
    Weightings<T> weighter;

    public Ngram(String first) {
        n = first.length() + 1;
        this.first = first;
        lasts = new HashMap<T, Integer>();
        weighter = new Weightings<T>(lasts);
    }

    public int add(T c) {
        if (lasts.get(c) == null) {
            lasts.put(c, 1);
            weighter.updateTotal();
            return 1;
        }
        lasts.put(c, lasts.get(c) + 1);
        weighter.updateTotal();
        return lasts.get(c);
    }

    public T getRand() {
        return weighter.rand();
    }

}