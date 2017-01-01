
import java.util.Enumeration;

/**
 * Puun rajapinta. Laajentaa puun geneerisiä metodeja. Perustuu paikan (solmun)
 * abstraktioon.
 *
 * @author ylhaart
 */
public interface SimpleTree extends PositionalContainer {

    /**
     * Palauttaa puun T juurisolmun. Puun ollessa tyhjä esiintyy virhe.
     *
     * @return HeapNode
     */
    public HeapNode root() throws EmptyPriorityQueueException;

    /**
     * Palauttaa solmun v vanhemman. Solmun v ollessa juuri esiintyy virhe.
     *
     * @param v HeapNode
     * @return HeapNode
     * @throws java.lang.Exception
     */
    public HeapNode parent(HeapNode v) throws Exception;

    /**
     * Palauttaa luettelon solmun v lapsista.
     *
     * @param v HeapNode
     * @return HeapNode[]
     */
    public Enumeration children(HeapNode v);

    /**
     * Testaa, onko solmu v sisäsolmu.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isInternal(HeapNode v);

    /**
     * Testaa, onko solmu v ulkosolmu eli lehti.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isExternal(HeapNode v);

    /**
     * Testaa, onko solmu v juuri.
     *
     * @param v HeapNode
     * @return boolean
     */
    public boolean isRoot(HeapNode v);


    /*
--------------------------------------------------------------------------------
     */
    /**
     * Palauttaa avaimen arvon kyseisestä solmusta.
     *
     * @param v HeapNode
     * @return int
     */
    public int element(HeapNode v);

    /**
     * Palauttaa viittauksen puuhun, joka sisältää kyseisen solmun.
     *
     * @return BinaryTree
     */
    public BinaryTree container();
}
