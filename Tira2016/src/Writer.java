
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private final String arg;

    public Writer(String arg) {
        this.arg = arg;
    }

    public void writeOutput() {
        String s = "Masa";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(arg));
            bw.write(s);
            bw.newLine();
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Kirjoitetaan...");
    }

}
