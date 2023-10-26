import java.util.HashMap;
import java.util.Set;

public class Weightings <T>{
    int total;
    HashMap<T, Integer> h;

    public Weightings(HashMap<T, Integer> h) {
        this.h = h;
        total = totalWeight(h);
    }

    public void updateTotal() {
        total = totalWeight(h);
    }

    public T rand() {
        double rand = Math.random() * total;
        Set<T> keySet = h.keySet();
        for (T c : keySet) {
            rand -= h.get(c);
            if (rand <= 0.0) {
                return c;
            }
        }
        return (T) keySet.toArray()[keySet.toArray().length - 1];
    }

    public int totalWeight(HashMap<T, Integer> h) {
        int w = 0;
        for (T c : h.keySet()) {
            w += h.get(c);
        }
        return w;
    }
}