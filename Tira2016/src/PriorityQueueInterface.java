
import java.security.InvalidKeyException;

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
     * @throws java.security.InvalidKeyException
     */
    public HeapNode insertItem(String k, String e) throws InvalidKeyException;

    /**
     * Palauttaa P:n alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     *
     * @return String
     */
    public HeapNode minElement();

    /**
     * Palauttaa P:n pienimmän avaimen. Virhe esiintyy prioriteettijonon ollessa
     * tyhjä.
     *
     * @return int
     */
    public int minKey();
    // public Entry<K,V> min() throws EmptyPriorityQueueException // K = key, V = value

    /**
     * Palauttaa ja poistaa P:stä alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     *
     * @return HeapNode
     */
    public HeapNode removeMinElement();
    // public Entry<K,V> removeMin() throws EmptyPriorityQueueException // K = key, V = value
}
