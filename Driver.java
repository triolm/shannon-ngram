import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Driver {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String text = new String(Files.readAllBytes(Paths.get("union.txt")), StandardCharsets.UTF_8).toLowerCase();
        HashMap<String, Ngram> h = new HashMap<String, Ngram>();

        int n = 10;
        for (int j = 1; j <= n; j++) {
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

        int[] counts = new int[n];

        for (int i = built.length(); i < 2000; i++) {
            Ngram ngram = null;
            int j;
            for (j = n; j > 0 && ngram == null; j --) {
                if(j > i || built.length() < j){
                    continue;
                }
                //add some more noise
                if(Math.random() < 0.01 * ((3 * j)-1)){
                    continue;
                }
                String first = built.substring(i - j, i);
                ngram = h.get(first);
            }
            counts[j]++;
            built += ngram.getRand();
        }
        System.out.println(built);
        for(int count = 0; count < counts.length; count ++ ){
            System.out.println(count + "-gram used " + (counts[count] + 1) + " times.");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("./outputs/" + System.currentTimeMillis() + ".txt"));
        bw.write(built);
        bw.close();

        System.out.println("Finished in " + (System.currentTimeMillis() - start) + "ms.");

    }
}
