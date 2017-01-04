
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 *
 * @author ylhaart
 */
public class HeapSimplePriorityQueue implements PriorityQueueInterface {

    // Keko T, jossa ovat solmuissa talletettuina prioriteettijonon alkiot
    // ja avaimet:
    BinaryTree T; // muuttujan nimi voisi olla yhtä hyvin "heap"
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    // Vertain, joka määrittelee avainten täydellisen järjestyksen relaation:
    Comparator comparator;

    /**
     * Keko, joka on toteutettu käyttäen linkitettyä binääripuuta. Kun luodaan
     * uusi keko, luodaan myös uusi binääripuu, viimeiseen kekosolmuun viittaava
     * solmu sekä vertain.
     */
    public HeapSimplePriorityQueue() {
        T = new BinaryTree();
        last = null; // tyhjässä keossa ei ole vielä viimeistä solmua
        comparator = new Comparator();
    }

    @Override
    public HeapNode insertItem(int k, String e) throws InvalidKeyException { // Onko poikkeus ok?
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Virheellinen syöte.");
        }
//        HeapNode z = new HeapNode(k, e, null, null, null);
        HeapNode z;
        if (isEmpty()) { //root == null
//            try {
//            z = T.root();
//            T.setRoot(z);
            z = T.addRoot(new KeyElementPair(k, e));
//            } catch (EmptyTreeException ex) {
//                System.out.println("Tyhjä jono:\n" + ex);
//            }

        } else {
            z = last;
            try {
                //            last = z;
                while (!T.isRoot(z) && !isLeftChild(z)) {
                    try {
                        z = T.parent(z);
                    } catch (BoundaryViolationException | InvalidPositionException ex) {
                        System.out.println("Solmu on juuri:\n" + ex);
                    }
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            } catch (EmptyTreeException ex) {
                System.out.println("Puu on tyhjä:\n" + ex);
            }
            try {
                if (!T.isRoot(z)) {
                    try {
                        z = T.rightChild(T.parent(z));
                    } catch (BoundaryViolationException | InvalidPositionException ex) {
                        System.out.println("Solmu on lehti:\n" + ex);
                    }
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            } catch (EmptyTreeException ex) {
                System.out.println("Puu on tyhjä:\n" + ex);
            }
            try {
                while (!T.isExternal(z)) {
                    z = T.leftChild(z);
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
        }
        try { // aseta null-arvot kuntoon!
            T.expandExternal(z, new HeapNode(), new HeapNode()); // Onko z:n asettaminen tarpeen? z = T.expandExternal(z)
        } catch (InvalidPositionException ex) {
            System.out.println("Kyseessä on sisäsolmu:\n" + ex);
        }
        try {
//            T.replace(z, new HeapNode(k, e));
            T.replace(z, new KeyElementPair(k, e));
        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        }
//        T.replace(T.leftChild(z), new HeapNode(k, e)); // oli: z korvataan new HeapNode(k, e)
        last = z;
//        T.setSize(T.size() + 1); // kasvatetaan keon kokoa yhdellä
        HeapNode u = null;
        try {
            while (!T.isRoot(z)) {
                try {
                    u = T.parent(z);
                } catch (Exception ex) {
                    System.out.println("Kyseessä on juuri:\n" + ex);
                }
                if (comparator.isLessThanOrEqualTo(keyOfPosition(u),
                        keyOfPosition(z))) {
                    break;
                }
                T.swap(u, z);
                z = u;
            }
        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        } catch (EmptyTreeException ex) {
            System.out.println("Puu on tyhjä:\n" + ex);
        }
        return z;
    }

    private int keyOfPosition(HeapNode p) throws InvalidPositionException {
        return p.getKey();
    }

    /**
     * Selvittää, onko annettu solmu p vasen lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidKeyException
     */
    public boolean isLeftChild(HeapNode p) throws InvalidKeyException {
        try {
            return T.leftChild(T.parent(p)).equals(p);
        } catch (Exception e) {
            System.out.println("Poikkeus:\n" + e);
            return false;
        }
    }

    @Override
    public HeapNode removeMinElement() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Jono on tyhjä.");
        }
        // Kesken! Tarkista, meneekö oikeaan suuntaan!
        HeapNode min = T.root();
        if (size() == 1) {
            try {
                T.replace(min, null); // heap.remove()
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
        } else {
            try {
                T.replace(min, null); // heap.replace(heap.root(),heap.remove())
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
            try {
                // downHeap(heap.root())
                while (T.isInternal(min)) {
                    HeapNode s;
//                if(!T.hasRightChild(min)){
//                  s = T.leftChild(min);}
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
        }
        return min;
    }

    @Override
    public HeapNode minKey() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Jono on tyhjä.");
        }
        return T.root(); // T.root().getKey()
    }

    @Override
    public HeapNode minElement() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Jono on tyhjä.");
        }
        return T.root();
    }

    public String print() {
        return null;
    }

    @Override
    public int size() {
        return T.size();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty(); // last == 0 // T.size() == 0
    }
}
