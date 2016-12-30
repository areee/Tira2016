
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* Tietorakenteet-kurssin harjoitustyö
(c)2016 Arttu Ylhävuori*/
public class Tira2016 {

    public static void main(String[] args) {

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            BufferedReader br2 = new BufferedReader(new FileReader(args[0])); // kopio count-laskentaa varten
            long count = br2.lines().count();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
                int size = 0;

                for (int i = 0; i < count; i++) {
                    line = br.readLine();
                    String[] values = line.split(" "); // toimii myös tyhjällä rivillä
                    switch (values[0]) {
                        case "i":
                            size++; // (väärässä paikassa, hae tämä tieto varsinaisesta keko-luokasta)
                            System.out.println("insertItem");
                            // tähän väliin insertItem-metodin suoritusta
                            bw.write("(" + values[1] + "," + values[2] + ") lis.");
                            bw.newLine();
                            break;
                        case "s":
                            System.out.println("size");
                            // tähän väliin size-metodin suoritusta
                            bw.write(size);
                            bw.newLine();
                            break;
                        case "r":
                            System.out.println("removeMinElement");
                            // tähän väliin removeMinElement-metodin suoritusta
                            bw.write("(3,kolme) poistettu.");
                            bw.newLine();
                            break;
                        case "m":
                            System.out.println("minKey");
                            // tähän väliin removeMinElement-metodin suoritusta
                            bw.write("(3,kolme) poistettu.");
                            bw.newLine();
                            break;
                        case "p":
                            System.out.println("print");
                            // tähän väliin removeMinElement-metodin suoritusta
                            bw.write("(3,kolme) poistettu.");
                            bw.newLine();
                            break;
                        case "q":
                            System.out.println("Ohjelma lopetettu.");
                            // tähän väliin removeMinElement-metodin suoritusta
                            bw.write("(3,kolme) poistettu.");
                            bw.newLine();
                            break;
                        default:
                            System.out.println("Virheellinen syöte.");
                            bw.write("Virheellinen syöte.");
                            bw.newLine();
                            break;
                    }

                }
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
            System.out.println("Kirjoitetaan...");
        } catch (IOException e) {
            System.out.println("File not found.");;
        }

        String s = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
            bw.write(s);
            bw.newLine();
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Kirjoitetaan...");

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
