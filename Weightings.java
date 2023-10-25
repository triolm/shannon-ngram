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
        return (char)keySet.toArray()[ keySet.toArray().length -1];

    }

    public int totalWeight(HashMap<Character, Integer> h) {
        int w = 0;
        for (char c : h.keySet()) {
            w += h.get(c);
        }
        return w;
    }
}