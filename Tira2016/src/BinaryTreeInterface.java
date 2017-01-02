
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
     */
    public HeapNode leftChild(HeapNode v);

    /**
     * Palauttaa solmun v oikean lapsen. Virhe esiintyy, mikäli v on lehti.
     *
     * @param v HeapNode
     * @return HeapNode
     */
    public HeapNode rightChild(HeapNode v);

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
     * @throws java.lang.Exception
     */
    public void expandExternal(HeapNode v) throws Exception;

    /**
     * Poistaa lehden v ja tämän vanhemman u sekä sijoittaa v:n sisaruksen u:n
     * paikalle. Operaatio palauttaa solmun u. Virhe esiintyy, mikäli v on
     * sisäsolmu.
     *
     * @param v HeapNode
     * @return HeapNode
     */
    public HeapNode removeAboveExternal(HeapNode v) throws Exception;
}
