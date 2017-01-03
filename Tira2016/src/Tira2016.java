
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* Tietorakenteet-kurssin harjoitustyö
(c)2016-2017 Arttu Ylhävuori*/
public class Tira2016 {

    public static void main(String[] args) {

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            BufferedReader br2 = new BufferedReader(new FileReader(args[0])); // kopio count-laskentaa varten
            long count = br2.lines().count();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
                HeapSimplePriorityQueue hspq = new HeapSimplePriorityQueue();

                for (int i = 0; i < count; i++) {
                    line = br.readLine();
                    String[] values = line.split(" "); // toimii myös tyhjällä rivillä
                    switch (values[0]) {
                        case "i":

                            if (values.length == 3) {
                                try {
                                    int parseInt = Integer.parseInt(values[1]);
                                    HeapNode insertItem = hspq.insertItem(parseInt, values[2]);
                                    System.out.println(insertItem + " lis.");// aputuloste
                                    bw.write(insertItem + " lis.");
                                    bw.newLine();
                                } catch (InvalidKeyException ex) {
                                    System.out.println("Virheellinen syöte."); // aputuloste
                                    bw.write("Virheellinen syöte.");
                                    bw.newLine();
                                }
                            } else {
                                System.out.println("Virheellinen syöte."); // aputuloste
                                bw.write("Virheellinen syöte.");
                                bw.newLine();
                            }
                            break;
                        case "s":
                            int size = hspq.size();
                            if (size == 0) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                bw.write("Jono on tyhjä.");
                                bw.newLine();
                            } else {
                                System.out.println("" + size); // aputuloste
                                bw.write("" + size);
                                bw.newLine();
                            }
                            break;
                        case "r":
                            HeapNode removeMinElement;
                            try {
                                removeMinElement = hspq.removeMinElement();
                                System.out.println(removeMinElement + " poistettu."); // aputuloste
                                bw.write(removeMinElement + " poistettu.");
                                bw.newLine();
                            } catch (EmptyTreeException ex) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                bw.write("Jono on tyhjä.");
                                bw.newLine();
                            }
                            break;
                        case "m":
                            try {
                                HeapNode minKey = hspq.minKey();
                                System.out.println("Pienin alkio on " + minKey + "."); // aputuloste
                                bw.write("Pienin alkio on " + minKey + ".");
                                bw.newLine();
                            } catch (EmptyTreeException ex) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                bw.write("Jono on tyhjä.");
                                bw.newLine();
                            }
                            break;
                        case "p":
                            String print = hspq.print();
                            if (print.isEmpty()) {
                                System.out.println("Jono on tyhjä."); // aputuloste
                                bw.write("Jono on tyhjä.");
                                bw.newLine();
                            } else {
                                System.out.println(print); // aputuloste
                                bw.write(print);
                                bw.newLine();
                            }
                            break;
                        case "q":
                            System.out.println("Ohjelma lopetettu."); // aputuloste
                            bw.write("Ohjelma lopetettu.");
                            break;
                        default:
                            System.out.println("Virheellinen syöte.");
                            bw.write("Virheellinen syöte.");
                            bw.newLine();
                            break;
                    }
                }
                bw.close();

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } catch (IOException e) {
            System.out.println("File not found.");;
        }

//        if (args.length == 2) {
//            // 1.) Lue input.txt args[0]:sta
//            Reader reader = new Reader(args[0]); // Oletus: input.txt löytyy args[0]:sta
//            reader.readInput();
//
//            // 2.) Muodosta keko
//            HeapSimplePriorityQueue hspq = new HeapSimplePriorityQueue();
//
//            // 3.) kirjoita output.txt args[1]:een
//            Writer writer = new Writer(args[1]);
//            writer.writeOutput();
//
//        } else {
//            System.out.println("Virheellinen syöte.");
//        }
    }

}
