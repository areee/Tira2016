
/**
 * Prioriteettijonon metodit.
 *
 * @author ylhaart
 */
public interface PriorityQueueInterface {

    /**
     * Palauttaa prioriteettijonon P alkioiden lukumäärän.
     *
     * @return size
     */
    public int size();

    /**
     * Testaa onko P tyhjä.
     *
     * @return boolean
     */
    public boolean isEmpty();

    /**
     * Lisää uuden alkion e avaimella k P:hen.
     *
     * @param k int
     * @param e String
     * @return HeapNode
     * @throws InvalidKeyException
     */
    public HeapNode insertItem(int k, String e) throws InvalidKeyException,
            KeyAlreadyInQueueException;

    /**
     * Palauttaa P:n alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     *
     * @return String
     */
    public HeapNode minElement() throws EmptyTreeException;

    /**
     * Palauttaa P:n pienimmän avaimen. Virhe esiintyy prioriteettijonon ollessa
     * tyhjä.
     *
     * @return int
     * @throws EmptyTreeException
     */
    public HeapNode minKey() throws EmptyTreeException;

    /**
     * Palauttaa ja poistaa P:stä alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     *
     * @return HeapNode
     * @throws EmptyTreeException
     */
    public HeapNode removeMinElement() throws EmptyTreeException;
}
