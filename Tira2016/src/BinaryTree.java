
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Binääripuu on järjestetty puu, jossa solmuilla on joko ei yhtään tai kaksi
 * lasta.
 *
 * @author ylhaart
 */
public class BinaryTree implements BinaryTreeInterface { // aiemmin implementoi: SimpleTree

    private int size;
    private HeapNode root;

    /**
     * Kun luodaan uusi binääripuu, se on aluksi tyhjä (size = 0).
     */
    public BinaryTree() {
        size = 0; // oli aiemmin 1
//        root = new HeapNode(0, null, null, null, null);
        root = null;
    }

    /**
     * Tarkistaa solmun: jos solmu ei ole tyhjä ja se on oikanlainen tyypiltään,
     * palautetaan solmu sellaisenaan.
     *
     * @param v Object
     * @return HeapNode
     * @throws InvalidPositionException
     */
    private HeapNode checkPosition(Object v) throws InvalidPositionException {
        if (v == null || !(v instanceof HeapNode)) {
            throw new InvalidPositionException("Solmu ei kelpaa.");
        }
        return (HeapNode) v;
    }

    /**
     * Asettaa binääripuulle uuden koon.
     *
     * @param size int
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Asettaa binääripuulle uuden juuren.
     *
     * @param root HeapNode
     */
    public void setRoot(HeapNode root) {
        this.root = root;
    }

    @Override
    public HeapNode root() throws EmptyTreeException {
        if (isEmpty()) { // root == null
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return root;
    }

    @Override
    public HeapNode parent(HeapNode v) throws InvalidPositionException,
            BoundaryViolationException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode parent = checkPosition.getParent();
        if (parent == null) {
            throw new BoundaryViolationException("Vanhempaa ei ole.");
        }
        return parent;
    }

    @Override
    public boolean isInternal(HeapNode v) throws InvalidPositionException {
        checkPosition(v);
        // Solmu on sisäsolmu, jos sillä on lapsia.
        return hasLeft(v) || hasRight(v);
    }

    @Override
    public boolean isExternal(HeapNode v) throws InvalidPositionException {
        return !isInternal(v); // Kun solmu ei ole sisäsolmu, se on ulkosolmu.
    }

    @Override
    public boolean isRoot(HeapNode v) throws InvalidPositionException,
            EmptyTreeException {
        checkPosition(v);
        return (v == root());
    }

    @Override
    public int size() {
        return size / 2;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void swap(HeapNode r, HeapNode w) throws InvalidPositionException {
        HeapNode r1 = checkPosition(r);
        HeapNode w1 = checkPosition(w);

        KeyElementPair temp = w.getKeyAndElement();

        w1.setKeyAndElement(r.getKey(), r.getElement());
        r1.setKeyAndElement(temp.getKey(), temp.getElement());
    }

    @Override
    public Object replace(HeapNode v, KeyElementPair e) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        int key = v.getKey();
        String element = v.getElement();
        checkPosition.setKey(e.getKey());
        checkPosition.setElement(e.getElement());

        KeyElementPair oldKeyElementPair = new KeyElementPair(key, element);
        return oldKeyElementPair;
    }

    /**
     * Palauttaa avaimen arvon annetulle solmulle.
     *
     * @param v HeapNode
     * @return int
     */
    public int key(HeapNode v) {
        return v.getKey();
    }

    @Override
    public String element(HeapNode v) {
        return v.getElement();
    }

    @Override
    public HeapNode leftChild(HeapNode v) throws InvalidPositionException,
            BoundaryViolationException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        if (left == null) {
            throw new BoundaryViolationException("Vasenta lasta ei ole.");
        }
        return left;
    }

    @Override
    public HeapNode rightChild(HeapNode v) throws InvalidPositionException,
            BoundaryViolationException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode right = checkPosition.getRight();
        if (right == null) {
            throw new BoundaryViolationException("Oikeaa lasta ei ole.");
        }
        return right;
    }

    @Override
    public HeapNode sibling(HeapNode v) {
        if (v.getParent().getLeft().equals(v)) {
            return v.getParent().getRight();
        }
        return v.getParent().getLeft();
    }

    @Override
    public void expandExternal(HeapNode v, KeyElementPair l, KeyElementPair r)
            throws InvalidPositionException {
        if (!isExternal(v)) {
            throw new InvalidPositionException("Kyseessä on sisäsolmu.");
        }
        /* Laajentaa ulkosolmun sisäsolmuun, jossa kaksi ulkosolmulasta.*/
        insertLeft(v, l);
        insertRight(v, r);
    }

    @Override
    public HeapNode removeAboveExternal(HeapNode v) throws Exception {
        if (isInternal(v)) {
            throw new Exception("Kyseessä on sisäsolmu.");
        } else {
            HeapNode u = parent(v);
            HeapNode s = sibling(v);
            if (isRoot(u)) {
                // Jos u on juuri, poistetaan se asettamalla v:n sisaruksen
                // s vanhemmaksi null-viite ja asetetaan s itse juureksi.
                s.setParent(null);
                root = s;
            } else {
                HeapNode g = parent(u);
                if (u == leftChild(g)) {
                    g.setLeft(s);
                } else {
                    g.setRight(s);
                    s.setParent(g);
                }
            }
            size -= 2;
            return u;
        }
    }

    /**
     * Palauttaa tiedon siitä, onko annetulla solmulla v vasenta lasta.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean hasLeft(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        return checkPosition.getLeft() != null;
    }

    /**
     * Palauttaa tiedon siitä, onko annetulla solmulla v oikeaa lasta.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean hasRight(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        return checkPosition.getRight() != null;
    }

    /**
     * Palauttaa tiedon siitä, onko annetulla solmulla v vasenta lasta, jonka
     * avain ei ole arvoltaan nolla.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean hasLeftNotZero(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        return left != null && left.getKey() != 0;
    }

    /**
     * Palauttaa tiedon siitä, onko annetulla solmulla v oikeaa lasta, jonka
     * avain ei ole arvoltaan nolla.
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean hasRightNotZero(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode right = checkPosition.getRight();
        return right != null && right.getKey() != 0;
    }

    /**
     * Lisää uuden solmun solmun v vasempaan lapseen.
     *
     * @param v HeapNode
     * @param l KeyElementPair
     * @return HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode insertLeft(HeapNode v, KeyElementPair l) throws
            InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        if (left != null) {
            throw new InvalidPositionException("Solmulla on jo vasen lapsi.");
        }
        HeapNode createNode = createNode(l, checkPosition, null, null);
        checkPosition.setLeft(createNode);
        size++;
        return createNode;
    }

    /**
     * Lisää uuden solmun solmun v oikeaan lapseen.
     *
     * @param v HeapNode
     * @param r KeyElementPair
     * @return HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode insertRight(HeapNode v, KeyElementPair r) throws
            InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode right = checkPosition.getRight();
        if (right != null) {
            throw new InvalidPositionException("Solmulla on jo oikea lapsi.");
        }
        HeapNode createNode = createNode(r, checkPosition, null, null);
        checkPosition.setRight(createNode);
        size++;
        return createNode;
    }

    /**
     * Luo uuden solmun annettujen parametrien mukaisesti ja palauttaa sen.
     *
     * @param kep KeyElementPair
     * @param parent HeapNode
     * @param left HeapNode
     * @param right HeapNode
     * @return HeapNode
     */
    private HeapNode createNode(KeyElementPair kep, HeapNode parent,
            HeapNode left, HeapNode right) {
        return new HeapNode(kep.getKey(), kep.getElement(), left, right, parent);
    }

    public HeapNode addRoot(KeyElementPair e) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("Puu ei ole tyhjä.");
        }
        size = 1;
        root = createNode(e, null, null, null);
        return root;
    }

    @Override
    public Iterator<HeapNode> children(HeapNode v) throws
            InvalidPositionException {
        List<HeapNode> heapNodes = new ArrayList<>();

        if (hasLeftNotZero(v)) {
            heapNodes.add(leftChild(v));
        }
        if (hasRightNotZero(v)) {
            heapNodes.add(rightChild(v));
        }
        return heapNodes.iterator();
    }

    /**
     * Esijärjestyksen tulostaminen: kuljettaessa puuta esijärjestyksessä
     * käydään ensin juuressa ja sitten tämän alipuissa käymällä lapset
     * rekursiivisesti läpi.
     *
     * @param T BinaryTree
     * @param v HeapNode
     * @param output String
     * @param level int
     * @return String
     */
    public String preorderPrint(BinaryTree T, HeapNode v, String output, int level) {

        int key = T.key(v); // nykyisen solmun avain
        String indentations = ""; // määrittää, paljonko tulostusta sisennetään

        // Lisätään aina uusi rivi, jos ei ole kyse juuresta (eli taso 0:sta):
        if (level != 0) {
            indentations += "\n";
        }

        // Riippuen siitä, mikä on nykyinen taso, lisätään sisennystä:
        for (int i = 0; i < level; i++) {
            indentations += "  ";
        }

        // Jokaisella tasolla täydennetään tulostetta sisennyksillä ja avaimella:
        output += indentations + key;

        Iterator<HeapNode> children = null;
        try {
            children = T.children(v); // palauttaa nykyisen solmun lapset
        } catch (InvalidPositionException ex) {
            System.out.println("Virhe solmun sijainnissa.");
        }
        level++; // level = level+1
        while (children.hasNext()) {
            HeapNode nextElement = (HeapNode) children.next();
            output = preorderPrint(T, nextElement, output, level);
        }
        return output;
    }

    /**
     * Solmun poistamismetodi.
     *
     * @param r HeapNode
     * @return HeapNode
     * @throws InvalidPositionException
     */
    public HeapNode remove(HeapNode r) throws InvalidPositionException {
        HeapNode r1 = checkPosition(r);
        HeapNode left = r1.getLeft();
        HeapNode right = r1.getRight();

        if (!leftKeyZero(r1) && !rightKeyZero(r1)) {
            throw new InvalidPositionException("Ei voida poistaa, sillä solmussa"
                    + " on kaksi lasta.");
        }

        HeapNode helpNode = null;

        if (r1 == root) {
            root = helpNode;
            size = 0;
        } else { // poistettava solmu ei ole juuri
            HeapNode parent = r1.getParent();

            if (isLeftChild(r1)) { // jos solmu itse on vasen lapsi
                left.setParent(parent);
                parent.setLeft(left);
            } else { // jos solmu itse on oikea lapsi
                right.setParent(parent);
                parent.setRight(right);
            }
            size -= 2;
        }
        return r;
    }

    /**
     * Selvittää, onko annettu solmu p vasen lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean isLeftChild(HeapNode p) throws BoundaryViolationException,
            InvalidPositionException {
        return leftChild(parent(p)).equals(p);
    }

    /**
     * Selvittää, onko annettu solmu p oikea lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean isRightChild(HeapNode p) throws BoundaryViolationException,
            InvalidPositionException {
        return rightChild(parent(p)).equals(p);
    }

    /**
     * Selvittää, onko solmu v "lehtisolmu", eli onko solmun vasempana lapsena
     * pelkkä paikka uudelle solmulle (eli onko lapsen avain 0).
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean leftKeyZero(HeapNode v) throws
            InvalidPositionException {
        checkPosition(v);
        return v.getLeft().getKey() == 0;
    }

    /**
     * Selvittää, onko solmu v "lehtisolmu", eli onko solmun oikeana lapsena
     * pelkkä paikka uudelle solmulle (eli onko lapsen avain 0).
     *
     * @param v HeapNode
     * @return boolean
     * @throws InvalidPositionException
     */
    public boolean rightKeyZero(HeapNode v) throws
            InvalidPositionException {
        checkPosition(v);
        return v.getRight().getKey() == 0;
    }
}
