
public class Comparator {

    /**
     * Vertain: olio, joka vertaa kahta avainta.
     */
    public Comparator() {
    }

    /**
     * Tosi, jos ja vain jos a on pienempi kuin b.
     *
     * @param a
     * @param b
     * @return boolean
     */
    public boolean isLessThan(int a, int b) {
        return a < b;
    }

    /**
     * Tosi, jos ja vain jos a on pienempi tai yhtä suuri kuin b.
     *
     * @param a
     * @param b
     * @return boolean
     */
    public boolean isLessThanOrEqualTo(int a, int b) {
        return a <= b;
    }

    /**
     * Tosi, jos ja vain jos a on yhtä suuri kuin b.
     *
     * @param a
     * @param b
     * @return boolean
     */
    public boolean isEqualTo(int a, int b) {
        return a == b;
    }

    /**
     * Tosi, jos ja vain jos a on suurempi kuin b.
     *
     * @param a
     * @param b
     * @return boolean
     */
    public boolean isGreaterThan(int a, int b) {
        return a > b;
    }

    /**
     * Tosi, jos ja vain jos a on suurempi tai yhtä suuri kuin b.
     *
     * @param a
     * @param b
     * @return boolean
     */
    public boolean isGreaterThanOrEqualTo(int a, int b) {
        return a >= b;
    }

    /**
     * Tosi, jos ja vain jos a on vertailtavissa. Tässä: onko objekti a
     * kokonaisluku (=Integer).
     *
     * @param a Object
     * @return boolean
     */
    public boolean isComparable(Object a) {
        return (a instanceof Integer); // tarkista, onko ok!
    }

}
