
import java.util.Iterator;
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

    public boolean keyIsAlreadyInBT(int key) {

        String print = "";
        print = T.preorderPrint(T, T.root(), print, 0);
        String[] split = print.split("\n");

        for (String string : split) {
            String[] split1 = string.split("  ");

            for (String string1 : split1) {
                if (string1.equals("" + key)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean keyIsZero(int key) {
        return key == 0;
    }

    @Override
    public HeapNode insertItem(int k, String e) throws InvalidKeyException { // Onko poikkeus ok?
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Avain ei kelpaa.");
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
            if (keyIsAlreadyInBT(k) || keyIsZero(k)) {
                throw new InvalidKeyException("Avain ei kelpaa.");
            }
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
        } // end of: if !isEmpty() = else
        try { // aseta null-arvot kuntoon!
            T.expandExternal(z, new KeyElementPair(), new KeyElementPair()); // Onko z:n asettaminen tarpeen? z = T.expandExternal(z)
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

//        } // end of: if !isEmpty() = else
        last = z;
        z = bubbleUp(z);
        return z;
    }

    private HeapNode bubbleUp(HeapNode z) {
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
        } catch (BoundaryViolationException | InvalidPositionException e) {
            System.out.println("Poikkeus:\n" + e);
            return false;
        }
    }

    /**
     * Selvittää, onko annettu solmu p oikea lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidKeyException
     */
    public boolean isRightChild(HeapNode p) throws InvalidKeyException {
        try {
            return T.rightChild(T.parent(p)).equals(p);
        } catch (BoundaryViolationException | InvalidPositionException e) {
            System.out.println("Poikkeus:\n" + e);
            return false;
        }
    }

    @Override
    public HeapNode removeMinElement() throws EmptyTreeException {
        // Jos tyhjä, ei voida poistaa:
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        // Pienimmän avaimen alkio on juuressa:
        HeapNode removedRoot = T.root();
        // Jos alkio on keon ainoa solmu, riittää pelkästään poistaa solmu:
        if (size() == 1) {

            try {
//                T.replace(min, null); // heap.remove()
                T.remove2(removedRoot);
                last = null;
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
        } /*1.) Jos alkio ei ole keon ainoa solmu, haetaan keon viimeinen solmu, 
        siirretään sen avain-alkio -pari juureen ja poistetaan viimeinen solmu.
        Tämän jälkeen viittaus viimeiseen solmuun on päivitettävä.
         */ else {
            HeapNode w = last;
            try {
                T.swap(removedRoot, w);
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
            removedRoot = w;
            try {
                T.remove2(removedRoot); // tämän jälkeen vanha juuri on poistettu ja vanha last on uusi juuri
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
            // etsitään uusi last-solmu:

            if (isRightChild(removedRoot)) {
                HeapNode parent = removedRoot.getParent();
                HeapNode left = parent.getLeft();
                last = left;
            } else {
                try {
                    while (!T.isRoot(removedRoot) && (!isLeftChild(removedRoot) && !isRightChild(removedRoot))) {
                        removedRoot = T.parent(removedRoot);
                    }
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                try {
                    if (!T.isRoot(removedRoot) && isLeftChild(removedRoot)) {
                        removedRoot = T.rightChild(T.parent(removedRoot));
                    } else { // !T.isRoot(r) && isRightChild(r)
                        removedRoot = T.leftChild(T.parent(removedRoot));
                    }
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }

                try {
                    while (!isExternal_KeyZeroRight(removedRoot)) { // removedRoot.getRight().getKey() != 0
                        removedRoot = T.rightChild(removedRoot);
                    }
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                last = removedRoot;
            }
            /*2.) Tee Down-heap bubbling.*/ // kesken, tee valmiiksi!
            // ...
            HeapNode r = T.root();
            HeapNode s;

            try { // testaa, toimiiko!
                while (!isExternal_KeyZeroLeft(r) && !isExternal_KeyZeroRight(r)) { // niin kauan kuin juuri on sisäsolmu:

                    if (!T.hasRight(r)) {
                        s = r.getLeft();
                    } else {
                        HeapNode left = r.getLeft();
                        HeapNode right = r.getRight();
                        if (comparator.isLessThan(keyOfPosition(left), keyOfPosition(right))) {
                            s = left;
                        } else {
                            s = right;
                        }
                    }

                    if (comparator.isLessThan(keyOfPosition(r), keyOfPosition(s))) {
                        break;
                    }
                    T.swap(r, s);
                    r = s;
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }

            // ...
            //            try {
            //                T.replace(min, null); // heap.replace(heap.root(),heap.remove())
            //            } catch (InvalidPositionException ex) {
            //                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            //            }
            //            try {
            //                // downHeap(heap.root())
            //                while (T.isInternal(min)) {
            //                    HeapNode s;
            ////                if(!T.hasRightChild(min)){
            ////                  s = T.leftChild(min);}
            //                }
            //            } catch (InvalidPositionException ex) {
            //                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            //            }
            {

            }
        }
        return removedRoot;
    }

    @Override
    public HeapNode minKey() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return T.root(); // T.root().getKey()
    }

    @Override
    public HeapNode minElement() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return T.root();
    }

    public String print() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        String print = "";

//        HeapNode root = T.root();
//        int key = root.getKey();
//        printPreOrder += key + "\n\t";
        return T.preorderPrint(T, T.root(), print, 0);
    }

    @Override
    public int size() {
        return T.size();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty(); // last == 0 // T.size() == 0
    }

    public boolean isExternal_KeyZeroRight(HeapNode v) {
        return v.getRight().getKey() == 0;
    }

    public boolean isExternal_KeyZeroLeft(HeapNode v) {
        return v.getLeft().getKey() == 0;
    }
}
