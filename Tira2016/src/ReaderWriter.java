
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lukee syötteitä ja kirjoittaa tulosteita.
 *
 * @author ylhaart
 */
public class ReaderWriter {

    private final String[] args;

    public ReaderWriter(String[] args) {
        this.args = args;
    }

    public void readAndWrite() throws ArgsLengthZeroException {
        String line;

        if (args.length == 2) {
            try {
                BufferedReader input1 = new BufferedReader(new FileReader(args[0]));
                long count = countLines();
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
                    HeapSimplePriorityQueue heap = new HeapSimplePriorityQueue();

                    for (int i = 0; i < count; i++) {
                        String[] values = readLineAndSplit(input1);
                        switch (values[0]) {
                            case "i":
                                insert(values, heap, output);
                                break;
                            case "s":
                                size(heap, output);
                                break;
                            case "r":
                                remove(heap, output);
                                break;
                            case "m":
                                minKey(heap, output);
                                break;
                            case "p":
                                print(heap, output);
                                break;
                            case "q":
                                quit(output);
                                break;
                            default:
                                error(output);
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
        } else {
            throw new ArgsLengthZeroException("Komentoriviargumentteja ei ole annettu!"
                    + "\nSuorita ohjelma komennolla \"java Tira2016 input.txt output.txt\".");
        }
    }

    private long countLines() throws FileNotFoundException {
        BufferedReader input2 = new BufferedReader(new FileReader(args[0]));
        long count = input2.lines().count();
        return count;
    }

    private String[] readLineAndSplit(BufferedReader input1) throws IOException {
        String line;
        line = input1.readLine();
        String[] values = line.split(" ");
        return values;
    }

    private void error(BufferedWriter output) throws IOException {
        output.write("Virheellinen syöte.");
        output.newLine();
    }

    private void quit(BufferedWriter output) throws IOException {
        System.out.println("Ohjelma lopetettu. "
                + "Tarkista tulokset " + args[1] + "-tiedostosta.");
        output.write("Ohjelma lopetettu.");
    }

    private void print(HeapSimplePriorityQueue heap, BufferedWriter output)
            throws IOException {
        try {
            String print = heap.print();
            output.write(print);
            output.newLine();
        } catch (EmptyTreeException ex) {
            writeQueueIsEmpty(output);
        }
    }

    private void minKey(HeapSimplePriorityQueue heap, BufferedWriter output)
            throws IOException {
        try {
            HeapNode minKey = heap.minKey();
            output.write("Pienin alkio on " + minKey + ".");
            output.newLine();
        } catch (EmptyTreeException ex) {
            writeQueueIsEmpty(output);
        }
    }

    private void remove(HeapSimplePriorityQueue heap, BufferedWriter output)
            throws IOException {
        HeapNode removeMinElement;
        try {
            removeMinElement = heap.removeMinElement();
            output.write(removeMinElement + " poistettu.");
            output.newLine();
        } catch (EmptyTreeException ex) {
            writeQueueIsEmpty(output);
        }
    }

    private void writeQueueIsEmpty(BufferedWriter output) throws IOException {
        output.write("Jono on tyhjä.");
        output.newLine();
    }

    private void size(HeapSimplePriorityQueue heap, BufferedWriter output)
            throws IOException {
        int size = heap.size();
        if (size == 0) {
            writeQueueIsEmpty(output);
        } else {
            output.write("" + size);
            output.newLine();
        }
    }

    private void insert(String[] values, HeapSimplePriorityQueue heap,
            BufferedWriter output) throws IOException {
        if (values.length == 3) {
            try {
                try {
                    int parseInt = Integer.parseInt(values[1]);
                    HeapNode insertItem = heap.insertItem(parseInt, values[2]);
                    output.write(insertItem + " lis.");
                    output.newLine();
                } catch (NumberFormatException e) {
                    error(output);
                } catch (KeyAlreadyInQueueException ex) {
                    output.write("" + ex.getMessage());
                    output.newLine();
                }
            } catch (InvalidKeyException ex) {
                error(output);
            }
        } else {
            error(output);
        }
    }

}
