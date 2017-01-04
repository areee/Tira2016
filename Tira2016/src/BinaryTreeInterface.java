
/**
 * Binääripuun abstrakti tietotyyppi käsittää yleisen puun metodien lisäksi
 * paikkametodeja (paikka = solmu).
 *
 * @author ylhaart
 */
public interface BinaryTreeInterface extends SimpleTree {

    /**
     * Palauttaa solmun v vasemman lapsen. Virhe esiintyy, mikäli v on lehti.
     *
     * @param v HeapNode
     * @return HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode leftChild(HeapNode v) throws InvalidPositionException,
            BoundaryViolationException;

    /**
     * Palauttaa solmun v oikean lapsen. Virhe esiintyy, mikäli v on lehti.
     *
     * @param v HeapNode
     * @return HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode rightChild(HeapNode v) throws InvalidPositionException,
            BoundaryViolationException;

    /**
     * Palauttaa solmun v sisaruksen. Virhe esiintyy, mikäli v on juuri.
     *
     * @param v HeapNode
     * @return HeapNode
     */
    public HeapNode sibling(HeapNode v);

    /**
     * Muuttaa lehden v sisäsolmuksi luomalla kaksi uutta lehteä ja asettamalla
     * nämä solmun v lapsiksi. Virhe esiintyy, mikäli v on sisäsolmu.
     *
     * @param v HeapNode
     * @param l KeyElementPair
     * @param r KeyElementPair
     * @throws java.lang.Exception
     */
    public void expandExternal(HeapNode v, KeyElementPair l, KeyElementPair r) throws Exception;

    /**
     * Poistaa lehden v ja tämän vanhemman u sekä sijoittaa v:n sisaruksen u:n
     * paikalle. Operaatio palauttaa solmun u. Virhe esiintyy, mikäli v on
     * sisäsolmu.
     *
     * @param v HeapNode
     * @return HeapNode
     * @throws java.lang.Exception
     */
    public HeapNode removeAboveExternal(HeapNode v) throws Exception;
}
