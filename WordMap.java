import java.util.Arrays;

public class WordMap extends NgramMap<String> {
    String[] split = txt.split("[\t\n\r]");

    public WordMap(String txt, int order) {
        super(txt, order);
    }

    public void generateNgramsForOrder(int order) {
        if (order == 1) {
            if (h.get("\n") == null) {
                h.put("\n", new Ngram<String>("\n"));
            }
            h.get("\n").add("the ");
        }
        for (int i = 0; i < split.length - order; i++) {
            addNgram(i, order);
        }
    }

    public void addNgram(int start, int order) {
        String[] firstArr = Arrays.copyOfRange(split, start, start + order);
        String first = String.join(" ", firstArr);
        String second = split[start + order];
        if (h.get(first) == null) {
            h.put(first, new Ngram<String>(first));
        }
        h.get(first).add(second);
    }
}
