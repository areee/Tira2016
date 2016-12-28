
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    private final String arg;

    public Reader(String arg) {
        this.arg = arg;
    }

    public void readInput() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(arg));
            for (int i = 0; i < 6; i++) {
                line = br.readLine(); //esim. i, 7, Seiska
                String[] values = line.split(" "); // esim. values[0]: "i", values[1]: " 7", values[2]: " Seiska"
//                    System.out.println(values[0]);
                for (String value : values) {
                    System.out.println(value);
                }
                System.out.println("");
            }

        } catch (IOException e) {
            System.out.println("File not found.");;
        }
    }

}
