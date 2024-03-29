
/**
 * Puun rajapinta. Puun geneeriset metodit.
 *
 * @author ylhaart
 */
public interface PositionalContainer {

    /**
     * Palauttaa puun T solmujen lukumäärän.
     *
     * @return int
     */
    public int size();

    /**
     * Testaa onko puussa T solmuja vai ei.
     *
     * @return boolean
     */
    public boolean isEmpty();

    /**
     * Vaihtaa keon T solmujen v ja w alkiot keskenään.
     *
     * @param v HeapNode
     * @param w HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode swap(HeapNode v, HeapNode w) throws InvalidPositionException;

    /**
     * Korvaa solmun v alkion alkiolla e ja palauttaa aiemman alkion.
     *
     * @param v HeapNode
     * @param e HeapNode
     * @return HeapNode v
     * @throws InvalidPositionException
     */
    public Object replace(HeapNode v, KeyElementPair e) throws InvalidPositionException;
}
