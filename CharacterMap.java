public class CharacterMap extends NgramMap<Character> {
    public CharacterMap(String txt, int order) {
        super(txt, order);
    }

    public void addNgram(int start, int order) {
        String first = txt.substring(start, start + order);
        char second = txt.charAt(start + order);
        if (h.get(first) == null) {
            h.put(first, new Ngram<Character>(first));
        }
        h.get(first).add(second);
    }
}
