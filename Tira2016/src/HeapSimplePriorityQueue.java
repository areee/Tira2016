

/*
Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 */
public class HeapSimplePriorityQueue implements PriorityQueueInterface {

    // Keko T, jossa ovat solmuissa talletettuina prioriteettijonon alkiot
    // ja avaimet:
    BinaryTree T; // muuttujan nimi voisi olla yhtä hyvin "heap"
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    // Vertain, joka määrittelee avainten täydellisen järjestyksen relaation:
    Comparator comparator;
//    private int size;

    public HeapSimplePriorityQueue() {
        T = new BinaryTree();
//        last = new HeapNode(null, null); // tyhjässä keossa ei ole vielä viimeistä solmua
        comparator = new Comparator();
//        size = 0;
    }

    // Lisää uuden alkion e avaimella k P:hen.
    @Override
    public HeapNode insertItem(int k, String e) throws InvalidKeyException { // Onko poikkeus ok?
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Virheellinen syöte.");
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
        return z;
    }

    private boolean isLeftChild(HeapNode p) {
        try {
            return T.leftChild(T.parent(p)).equals(p);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HeapNode removeMinElement() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Jono on tyhjä.");
        }
        // Kesken! Tarkista, meneekö oikeaan suuntaan!
        HeapNode min = T.root();
        if (size() == 1) {
            T.replace(min, null); // heap.remove()
        } else {
            T.replace(min, null); // heap.replace(heap.root(),heap.remove())
            // downHeap(heap.root())
            while (T.isInternal(min)) {
                HeapNode s;
//                if(!T.hasRightChild(min)){
//                  s = T.leftChild(min);}
            }
        }
        return min;
    }

    @Override
    public HeapNode minKey() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Jono on tyhjä.");
        }
        return T.root(); // T.root().getKey()
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
        return T.size();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty(); // last == 0 // T.size() == 0
    }
}
