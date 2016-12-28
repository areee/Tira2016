
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Tietorakenteet-kurssin harjoitustyö
// (c)2016 Arttu Ylhävuori
public class Tira2016 {

    public static void main(String[] args) {

        if (args.length == 2) {
            // 1.) Lue input.txt args[0]:sta
            Reader reader = new Reader(args[0]); // Oletus: input.txt löytyy args[0]:sta
            reader.readInput();
        } else {
            System.out.println("Virheellinen syöte.");
        }

        // 2.) Muodosta keko ja 3.) kirjoita output.txt args[1]:een
    }

}
