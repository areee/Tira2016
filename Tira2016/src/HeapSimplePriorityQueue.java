
import java.security.InvalidKeyException;
import java.util.Enumeration;

/*
Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 */
public class HeapSimplePriorityQueue implements PositionalContainer {

    /* Keko T, jossa ovat solmuissa talletettuina prioriteettijonon
    alkiot ja avaimet.
     */
    BinaryTree T;
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    Comparator comparator; // Vertain, joka määrittelee avainten täydellisen järjestyksen relaation.
    private int size;

    public HeapSimplePriorityQueue() {
        size = 0;
    }

    // Lisää uuden alkion e avaimella k P:hen.
    public void insertItem(int k, String e) throws InvalidKeyException { // Onko poikkeus ok?
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Invalid Key");
        }
        HeapNode z;
        if (isEmpty()) {
            z = T.root();
        } else {
            z = last;
            while (!T.isRoot(z) && !isLeftChild(z)) {
                z = T.parent(z);
            }
            if (!T.isRoot(z)) {
                z = T.rightChild(T.parent(z));
            }
            while (!T.isExternal(z)) {
                z = T.leftChild(z);
            }
        }
        T.expandExternal(z);
        T.replace(z, new HeapNode(k, e));
        last = z;
        HeapNode u;
        while (!T.isRoot(z)) {
            u = T.parent(z);
            if (comparator.isLessThanOrEqualTo(u.getKey(), z.getKey())) {
                break;
            }
            T.swap(u, z);
            z = u;
        }
    }

    private boolean isLeftChild(HeapNode p) {
        try {
            return T.leftChild(T.parent(p)).equals(p);
        } catch (Exception e) {
            return false;
        }
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Enumeration elements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enumeration positions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void swap(HeapNode v, HeapNode w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object replace(HeapNode v, Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
