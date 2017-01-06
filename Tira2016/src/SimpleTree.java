
import java.util.Iterator;

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
     * @throws EmptyTreeException
     */
    public HeapNode root() throws EmptyTreeException;

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
     * @return Enumeration
     * @throws InvalidPositionException
     */
    public Iterator<HeapNode> children(HeapNode v) throws InvalidPositionException;

    /**
     * Testaa, onko solmu v sisäsolmu.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean isInternal(HeapNode v) throws InvalidPositionException;

    /**
     * Testaa, onko solmu v ulkosolmu eli lehti.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean isExternal(HeapNode v) throws InvalidPositionException;

    /**
     * Testaa, onko solmu v juuri.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     * @throws EmptyTreeException
     */
    public boolean isRoot(HeapNode v) throws InvalidPositionException,
            EmptyTreeException;

    /**
     * Palauttaa merkkijonon annetulle solmulle.
     *
     * @param v HeapNode
     * @return String
     */
    public String element(HeapNode v);
}
