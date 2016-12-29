
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
     * @throws java.security.InvalidKeyException
     */
    public void insertItem(int k, String e) throws InvalidKeyException;

    /**
     * Palauttaa P:n alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     */
    public void minElement();

    /**
     * Palauttaa P:n pienimmän avaimen. Virhe esiintyy prioriteettijonon ollessa
     * tyhjä.
     *
     * @return int
     */
    public int minKey();

    /**
     * Palauttaa ja poistaa P:stä alkion, jolla on pienin avain. Virhe esiintyy
     * prioriteettijonon ollessa tyhjä.
     */
    public void removeMinElement();
}
