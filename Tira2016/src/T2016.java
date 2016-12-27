
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;

public class T2016 {

    private void readInput() {
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            for (int i = 0; i < 6; i++) {
                line = br.readLine();
                String[] values = line.split(",");
                System.out.println(values[0]);
            }

        } catch (IOException e) {
            System.out.println("File not found.");;
        }
    }

    private void writeOutput() {

        String s = "Masa";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write(s);
            bw.newLine();
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Kirjoitetaan...");
    }

    public static void main(String[] args) {
        T2016 ht = new T2016();
        ht.readInput();
        ht.writeOutput();
    }

}
