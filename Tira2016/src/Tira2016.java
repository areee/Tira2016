
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Tietorakenteet-kurssin harjoitustyö. (c) 2016-2017 Arttu Ylhävuori.
 *
 * @author ylhaart
 */
public class Tira2016 {

    public static void main(String[] args) {

        String line;
        try {
            BufferedReader input1 = new BufferedReader(new FileReader(args[0]));
            // Lisätään myös kopio count-laskentaa varten:
            BufferedReader input2 = new BufferedReader(new FileReader(args[0]));
            long count = input2.lines().count();
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
                HeapSimplePriorityQueue heap = new HeapSimplePriorityQueue();

                for (int i = 0; i < count; i++) {
                    line = input1.readLine();
                    String[] values = line.split(" ");
                    switch (values[0]) {
                        case "i":

                            if (values.length == 3) {
                                try {
                                    try {
                                        int parseInt = Integer.parseInt(values[1]);
                                        HeapNode insertItem = heap.insertItem(parseInt, values[2]);
                                        System.out.println(insertItem + " lis.");// aputuloste
                                        output.write(insertItem + " lis.");
                                        output.newLine();
                                    } catch (NumberFormatException e) {
                                        System.out.println("Virheellinen syöte."); // aputuloste
                                        output.write("Virheellinen syöte.");
                                        output.newLine();
                                    }
                                } catch (InvalidKeyException ex) {
                                    System.out.println("Virheellinen syöte."); // aputuloste
                                    output.write("Virheellinen syöte.");
                                    output.newLine();
                                }
                            } else {
                                System.out.println("Virheellinen syöte."); // aputuloste
                                output.write("Virheellinen syöte.");
                                output.newLine();
                            }
                            break;
                        case "s":
                            int size = heap.size();
                            if (size == 0) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                output.write("Jono on tyhjä.");
                                output.newLine();
                            } else {
                                System.out.println("" + size); // aputuloste
                                output.write("" + size);
                                output.newLine();
                            }
                            break;
                        case "r":
                            HeapNode removeMinElement;
                            try {
                                removeMinElement = heap.removeMinElement();
                                System.out.println(removeMinElement + " poistettu."); // aputuloste
                                output.write(removeMinElement + " poistettu.");
                                output.newLine();
                            } catch (EmptyTreeException ex) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                output.write("Jono on tyhjä.");
                                output.newLine();
                            }
                            break;
                        case "m":
                            try {
                                HeapNode minKey = heap.minKey();
                                System.out.println("Pienin alkio on " + minKey + "."); // aputuloste
                                output.write("Pienin alkio on " + minKey + ".");
                                output.newLine();
                            } catch (EmptyTreeException ex) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                output.write("Jono on tyhjä.");
                                output.newLine();
                            }
                            break;
                        case "p":
                            try {
                                String print = heap.print();
                                System.out.println(print); // aputuloste
                                output.write(print);
                                output.newLine();
                            } catch (EmptyTreeException ex) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                output.write("Jono on tyhjä.");
                                output.newLine();
                            }
                            break;
                        case "q":
                            System.out.println("Ohjelma lopetettu."); // aputuloste
                            output.write("Ohjelma lopetettu.");
                            break;
                        default:
                            System.out.println("Virheellinen syöte.");
                            output.write("Virheellinen syöte.");
                            output.newLine();
                            break;
                    }
                }
                output.close();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } catch (IOException e) {
            System.out.println("File not found.");;
        }
    }

}
