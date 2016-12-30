

/*
Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 */
public class HeapSimplePriorityQueue implements PriorityQueueInterface {

    /* Keko T, jossa ovat solmuissa talletettuina prioriteettijonon
    alkiot ja avaimet.
     */
    BinaryTree T;
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    Comparator comparator; // Vertain, joka määrittelee avainten täydellisen järjestyksen relaation.
//    private int size;

    public HeapSimplePriorityQueue() {
//        size = 0;
    }

    // Lisää uuden alkion e avaimella k P:hen.
    @Override
    public HeapNode insertItem(String k, String e) throws InvalidKeyException { // Onko poikkeus ok?
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
        return null;
    }

    private boolean isLeftChild(HeapNode p) {
        try {
            return T.leftChild(T.parent(p)).equals(p);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HeapNode removeMinElement() {
        try {

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public HeapNode minKey() {
        try {

        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public HeapNode minElement() {
        return null;
    }

    public String print() {
        return null;
    }

    @Override
    public int size() {
        return T.getSize();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty(); // return last == 0;
    }
}
