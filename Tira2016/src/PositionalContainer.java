
import java.util.Enumeration;

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
     * Palauttaa kaikki puun T solmuihin talletetut alkiot luettelona.
     *
     * @return HeapNode[]
     */
    public Enumeration elements();

    /**
     * Palauttaa puun T kaikkien solmujen (paikkojen) luettelon.
     *
     * @return HeapNode[]
     */
    public Enumeration positions();

    /**
     * Vaihtaa keon T solmujen v ja w alkiot keskenään.
     *
     * @param v HeapNode
     * @param w HeapNode
     */
    public void swap(HeapNode v, HeapNode w);

    /**
     * Korvaa solmun v alkion alkiolla e ja palauttaa aiemman alkion.
     *
     * @param v HeapNode
     * @param e HeapNode
     * @return HeapNode v
     */
    public Object replace(HeapNode v, Object e);
}
