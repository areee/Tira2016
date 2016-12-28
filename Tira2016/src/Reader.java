
import java.io.*;

public class Reader {

    private final String arg;

    public Reader(String arg) {
        this.arg = arg;
    }

    public void readInput() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(arg));
            BufferedReader br2 = new BufferedReader(new FileReader(arg)); // kopio count-laskentaa varten
            long count = br2.lines().count();

            for (int i = 0; i < count; i++) {
                line = br.readLine(); //esim. i, 7, Seiska
                String[] values = line.split(" "); // esim. values[0]: "i", values[1]: " 7", values[2]: " Seiska"
//                    System.out.println(values[0]);
                for (String value : values) {
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.");;
        }
    }

}
