import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Driver {

    public static void main(String[] args) throws Exception {
        String text = new String(Files.readAllBytes(Paths.get("text.txt")), StandardCharsets.UTF_8).toLowerCase();
        HashMap<String, Ngram> h = new HashMap<String, Ngram>();

        int n = 10;
        for (int j = 0; j <= n; j++) {
            for (int i = 0; i < text.length() - j; i++) {
                String first = text.substring(i, i + j);
                char second = text.charAt(i + j);
                if (h.get(first) == null) {
                    h.put(first, new Ngram(first));
                }
                h.get(first).add(second);
            }
        }

        String built = "t";

        for (int i = built.length(); i < 2000; i++) {
            Ngram ngram = null;
            for (int j = n; j > 0 && ngram == null; j --) {
                if(j > i || built.length() < j){
                    continue;
                }
                String first = built.substring(i - j, i);
                ngram = h.get(first);
            }
            built += ngram.getRand();
        }
        System.out.println(built);

    }
}
