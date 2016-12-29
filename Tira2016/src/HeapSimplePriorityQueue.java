
import java.security.InvalidKeyException;

/*
Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 */
public class HeapSimplePriorityQueue {

    /* Keko T, jossa ovat solmuissa talletettuina prioriteettijonon
    alkiot ja avaimet.
     */
    BinaryTree T; // (luentomateriaalissa: BinaryTree)
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    Comparator comparator; // Vertain, joka määrittelee avainten täydellisen järjestyksen relaation.

    public HeapSimplePriorityQueue() {
    }

    
    // Lisää uuden alkion e avaimella k P:hen.
    public void insertItem(int k, String e) throws InvalidKeyException { // Onko poikkeus ok?

    }

    // Palauttaa ja postaa P:stä alkion, jolla on pienin avain. 
    // Virhe esiintyy prioriteettijonon ollessa tyhjä.
    public void removeMinElement() {
        try {

        } catch (Exception e) {
        }
    }

    // Palauttaa P:n pienimmän avaimen. 
    // Virhe esiintyy prioriteettijonon ollessa tyhjä.
    public int minKey() {
        try {

        } catch (Exception e) {
        }

        return 0;
    }

    // Palauttaa P:n alkion, jolla on pienin avain.
    // Virhe esiintyy prioriteettijonon ollessa tyhjä.
    public void minElement() {

    }

    public void print() {

    }
    
}
