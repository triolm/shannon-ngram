import java.util.HashMap;

public abstract class NgramMap<T> {

    protected String txt;
    protected int order;
    HashMap<String, Ngram<T>> h;

    public NgramMap(String txt, int order) {
        this.txt = txt;
        this.order = order;
        h = new HashMap<String, Ngram<T>>();

    }

    public HashMap<String, Ngram<T>> generateNgramsUpToOrder() {
        for (int j = 1; j <= order; j++) {
            generateNgramsForOrder(j);
        }
        return h;
    }

    public void generateNgramsForOrder(int order) {
        for (int i = 0; i < txt.length() - order; i++) {
            addNgram(i, order);
        }
    }

    public abstract void addNgram(int start, int order);
}
