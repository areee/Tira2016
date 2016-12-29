
public class Comparator {

    public Comparator() {
    }

    // Tosi, jos ja vain jos a on pienempi kuin b.
    public boolean isLessThan(int a, int b) {
        return a < b;
    }

    // Tosi, jos ja vain jos a on pienempi tai yhtä suuri kuin b.
    public boolean isLessThanOrEqualTo(int a, int b) {
        return a <= b;
    }

    // Tosi, jos ja vain jos a on yhtä suuri kuin b.
    public boolean isEqualTo(int a, int b) {
        return a == b;
    }

    // Tosi, jos ja vain jos a on suurempi kuin b.
    public boolean isGreaterThan(int a, int b) {
        return a > b;
    }

    // Tosi, jos ja vain jos a on suurempi tai yhtä suuri kuin b.
    public boolean isGreaterThanOrEqualTo(int a, int b) {
        return a >= b;
    }

    // Tosi, jos ja vain jos a on vertailtavissa.
    // Tässä: onko objekti a kokonaisluku (=Integer).
    public boolean isComparable(Object a) {
        return (a instanceof Integer); // tarkista, onko ok!
    }

}
