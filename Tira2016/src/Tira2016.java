
/**
 * Tietorakenteet-kurssin harjoitustyö. (c) 2016-2017 Arttu Ylhävuori.
 *
 * @author ylhaart
 */
public class Tira2016 {

    public static void main(String[] args) {

        ReaderWriter rw = new ReaderWriter(args);
        try {
            rw.readAndWrite();
        } catch (ArgsLengthZeroException ex) {
            System.out.println("Komentoriviargumentteja ei ole annettu!"
                    + "\nSuorita ohjelma komennolla \"java Tira2016 input.txt "
                    + "output.txt\".");
        }

    }
}
