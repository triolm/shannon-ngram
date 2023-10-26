import java.util.HashMap;
import java.util.Set;

public class Weightings {
    int total;
    HashMap<Character, Integer> h;

    public Weightings(HashMap<Character, Integer> h) {
        this.h = h;
        total = totalWeight(h);
    }

    public void updateTotal() {
        total = totalWeight(h);
    }

    public char rand() {
        double rand = Math.random() * total;
        Set<Character> keySet = h.keySet();
        for (char c : keySet) {
            rand -= h.get(c);
            if (rand <= 0.0) {
                return c;
            }
        }
        return (char) keySet.toArray()[keySet.toArray().length - 1];
    }

    public int totalWeight(HashMap<Character, Integer> h) {
        int w = 0;
        for (char c : h.keySet()) {
            w += h.get(c);
        }
        return w;
    }

    public static HashMap<String, Ngram> generateNgramsUpToOrder(String text, int order) {
        HashMap<String, Ngram> h = new HashMap<String, Ngram>();
        return generateNgramsUpToOrder(text, h, order);
    }

    public static HashMap<String, Ngram> generateNgramsUpToOrder(String text, HashMap<String, Ngram> h, int order) {
        for (int j = 1; j <= order; j++) {
            generateNgramsForOrder(text, h, j);
        }
        return h;
    }

    public static void generateNgramsForOrder(String text, HashMap<String, Ngram> h, int order) {
        for (int i = 0; i < text.length() - order; i++) {
            addNgram(text, h, i, order);
        }
    }

    public static void addNgram(String text, HashMap<String, Ngram> h, int start, int order) {
        String first = text.substring(start, start + order);
        char second = text.charAt(start + order);
        if (h.get(first) == null) {
            h.put(first, new Ngram(first));
        }
        h.get(first).add(second);
    }
}