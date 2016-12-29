
/**
 * Binääripuu on järjestetty puu, jossa solmuilla on joko ei yhtään tai kaksi
 * lasta.
 *
 * @author ylhaart
 */
public class BinaryTree {

    private int size;

    /**
     * Kun luodaan uusi binääripuu, se on aluksi tyhjä (size = 0).
     */
    public BinaryTree() {
        size = 0;
    }

    /**
     * Palauttaa alkion kyseisestä solmusta.
     *
     * @return HeapNode
     */
    public HeapNode element() {
        return null;
    }

    /**
     * Palauttaa viittauksen puuhun, joka sisältää kyseisen solmun.
     *
     * @return BinaryTree
     */
    public BinaryTree container() {
        return null;
    }

    /**
     * Palauttaa puun T juurisolmun. Puun ollessa tyhjä esiintyy virhe.
     *
     * @return HeapNode
     */
    public HeapNode root() {
        return null;
    }

    /**
     * Palauttaa solmun v vanhemman. Solmun v ollessa juuri esiintyy virhe.
     *
     * @param v HeapNode
     * @return HeapNode
     */
    public HeapNode parent(HeapNode v) {
        return null;
    }

    /**
     * Palauttaa luettelon solmun v lapsista.
     *
     * @param v HeapNode
     * @return HeapNode[]
     */
    public HeapNode[] children(HeapNode v) {
        return null;
    }

    /**
     * Testaa, onko solmu v sisäsolmu.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isInternal(HeapNode v) {
        return false;
    }

    /**
     * Testaa, onko solmu v ulkosolmu eli lehti.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isExternal(HeapNode v) {
        return false;
    }

    /**
     * Testaa, onko solmu v juuri.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isRoot(HeapNode v) {
        return false;
    }

    /**
     * Palauttaa puun T solmujen lukumäärän.
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Testaa onko puussa T solmuja vai ei.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Palauttaa kaikki puun T solmuihin talletetut alkiot luettelona.
     *
     * @return HeapNode[]
     */
    public HeapNode[] elements() {
        return null;
    }

    /**
     * Palauttaa puun T kaikkien solmujen (paikkojen) luettelon.
     *
     * @return HeapNode[]
     */
    public HeapNode[] positions() {
        return null;
    }

    /**
     * Vaihtaa keon T solmujen v ja w alkiot keskenään.
     *
     * @param v HeapNode
     * @param w HeapNode
     */
    public void swap(HeapNode v, HeapNode w) {

    }

    /**
     * Korvaa solmun v alkion alkiolla e ja palauttaa aiemman alkion.
     *
     * @param v HeapNode
     * @param e HeapNode
     * @return HeapNode v
     */
    public HeapNode replace(HeapNode v, HeapNode e) {
        return v;
    }

}
