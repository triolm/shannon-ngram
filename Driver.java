import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Driver {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String infileName = "poe";
        String infilePath = "./input/" + infileName + ".txt";

        String text = new String(Files.readAllBytes(Paths.get(infilePath)), StandardCharsets.UTF_8);

        int n = 9;

        // TextBuilder<Character> t = new TextBuilder<Character>(new CharacterMap(text, n).generateNgramsUpToOrder());
        TextBuilder<String> t = new TextBuilder<String>(new WordMap(text, n).generateNgramsUpToOrder());

        for (int i = 0; i < 200; i++) {
            t.generateNext();
        }

        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./output/word/" + infileName + "-" + System.currentTimeMillis() + ".txt"));
        bw.write(t.getBuilt());
        bw.close();

        System.out.println("Finished in " + (System.currentTimeMillis() - start) + "ms.");

    }

}
