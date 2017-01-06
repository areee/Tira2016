
/**
 * Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 *
 * @author ylhaart
 */
public class HeapSimplePriorityQueue implements PriorityQueueInterface {

    /* Keko T, jossa ovat solmuissa talletettuina prioriteettijonon alkiot 
    ja avaimet:*/
    BinaryTree T;
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    /* Vertain, joka määrittelee avainten täydellisen järjestyksen relaation:*/
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
    public HeapNode insertItem(int k, String e) throws InvalidKeyException {
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Avain ei kelpaa.");
        }
        HeapNode z;

        if (isEmpty()) { //root == null
            z = T.addRoot(new KeyElementPair(k, e));
        } else {
            /* Jos avain löytyy jo keosta:*/
            if (keyIsAlreadyInBT(k)) {
                throw new InvalidKeyException("Avain on jo keossa.");
            } else if (keyIsZero(k)) {
                /*Jos avain on nolla:*/
                throw new InvalidKeyException("Avain on nolla.");
            }
            z = last;
            try {
                while (!T.isRoot(z) && !isLeftChild(z)) {
                    try {
                        z = T.parent(z);
                    } catch (BoundaryViolationException
                            | InvalidPositionException ex) {
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
                    } catch (BoundaryViolationException
                            | InvalidPositionException ex) {
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
        try {
            T.expandExternal(z, new KeyElementPair(), new KeyElementPair());
        } catch (InvalidPositionException ex) {
            System.out.println("Kyseessä on sisäsolmu:\n" + ex);
        }
        try {
            T.replace(z, new KeyElementPair(k, e));
        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        }

        last = z;
        z = bubbleUp(z);
        return z;
    }

    private boolean keyIsAlreadyInBT(int key) {

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

    private boolean keyIsZero(int key) {
        return key == 0;
    }

    private HeapNode bubbleUp(HeapNode z) {
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
            return T.isLeftChild(p);

        } catch (BoundaryViolationException | InvalidPositionException ex) {
            System.out.println("Poikkeus:\n" + ex);
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
            return T.isRightChild(p);
        } catch (BoundaryViolationException | InvalidPositionException ex) {
            System.out.println("Poikkeus:\n" + ex);
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
        HeapNode w = last;

        switch (size()) {
            case 1:
                /*Jos alkio on keon ainoa solmu, riittää pelkästään poistaa 
                solmu:*/
                try {
                    T.remove(removedRoot);
                    last = null;
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                break;
            case 2:
                // Jos keossa on kaksi solmua:
                try {
                    T.swap(removedRoot, w);
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                removedRoot = w;
                try {
                    T.remove(removedRoot);
                    /* Tämän jälkeen vanha juuri on poistettu ja vanha last on 
                    uusi juuri.*/
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                last = T.root();
                break;
            default: // jos keon koko on suurempi kuin 2:
                /*1.) Jos alkio ei ole keon ainoa solmu, haetaan keon viimeinen solmu,
        siirretään sen avain-alkio -pari juureen ja poistetaan viimeinen solmu.
        Tämän jälkeen viittaus viimeiseen solmuun on päivitettävä.*/
                try {
                    T.swap(removedRoot, w);
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                removedRoot = w;

                // etsitään uusi last-solmu:
                /* Jos removedRoot on oikeanpuoleinen lapsi, asetetaan last-muuttuja
                sen vanhemman vasemmanpuoleiseen lapseen.*/
                if (isRightChild(removedRoot)) {
                    HeapNode parent = removedRoot.getParent();
                    HeapNode left = parent.getLeft();
                    last = left;
                } else {
                    /* removedRoot on itse vasemmanpuoleinen lapsi vanhempaansa nähden.*/
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
                        // removedRoot.getRight().getKey() != 0:
                        while (!T.isExternal_KeyZeroRight(removedRoot)) {
                            removedRoot = T.rightChild(removedRoot);
                        }
                    } catch (InvalidPositionException ex) {
                        System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                    }
                    last = removedRoot;
                }
                try {
                    T.remove(removedRoot);
                    /* Tämän jälkeen vanha juuri on poistettu ja vanha last on 
                    uusi juuri.*/
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                /*2.) Tee Down-heap bubbling.*/ // kesken, tee valmiiksi!

                HeapNode r = T.root();
                HeapNode s;
                try { // testaa, toimiiko!
                    while (!T.isExternal_KeyZeroLeft(r)
                            && !T.isExternal_KeyZeroRight(r)) { // niin kauan kuin juuri on sisäsolmu:

                        /*Jos juuren r vasen lapsi on sen ainoa lapsisolmu, olkoon
                        s juuren r vasen lapsi.*/
                        if (!T.hasRight(r)) {
                            s = r.getLeft();
                        } else { // juurella on myös oikea lapsi
                            HeapNode left = r.getLeft();
                            HeapNode right = r.getRight();
                            // Jos vasen lapsi < oikea lapsi:
                            if (comparator.isLessThan(keyOfPosition(left),
                                    keyOfPosition(right))) {
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
                break;
        }
        return removedRoot;
    }

    @Override
    public HeapNode minKey() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return T.root();
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
        return T.preorderPrint(T, T.root(), print, 0);
    }

    @Override
    public int size() {
        return T.size();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty();
    }

}
