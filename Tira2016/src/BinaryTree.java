
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

    // Binääripuun luonti, jossa juuri on erikseen määritelty.
    public BinaryTree(HeapNode element) {
        size = 1;
        root = element;
    }

    // Binääripuun luonti, jossa juuri on erikseen määritelty, samoin alipuut.
    public BinaryTree(HeapNode element, BinaryTree leftTree, BinaryTree rightTree) {
        size = 1;
        root = element;
        root.setLeft(leftTree.root);
        root.setRight(rightTree.root);
    }

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

    public void setRoot(HeapNode root) {
        this.root = root;
    }

    /**
     * Solmun v syvyys on sen esivanhempien lukumäärä poislukien solmu itse.
     *
     * @param T SimpleTree
     * @param v HeapNode
     * @return int
     * @throws java.lang.Exception
     */
    public int depth(SimpleTree T, HeapNode v) throws Exception {
        if (T.isRoot(v)) {
            return 0;
        }
        return (1 + depth(T, T.parent(v)));
    }

    /**
     * Solmun v korkeus puussa T. Koko puun korkeus on sama kuin juuren korkeus.
     *
     * @param T SimpleTree
     * @return int h
     * @throws java.lang.Exception
     */
    public int height1(SimpleTree T) throws Exception {
        int h = 0;
        Enumeration nodes_of_T = T.positions();

        while (nodes_of_T.hasMoreElements()) {
            HeapNode v = (HeapNode) nodes_of_T.nextElement();
            if (T.isExternal(v)) {
                h = Math.max(h, depth(T, v));
            }
        }
        return h;
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

//        if (!isRoot(v)) {
//            return v.getParent();
//        }
//        throw new Exception("Kyseessä on juuri.");
//        return null;
    }

    @Override
    public boolean isInternal(HeapNode v) throws InvalidPositionException {
//        return !isExternal(v); // Kun solmu ei ole ulkosolmu, se on sisäsolmu.
        checkPosition(v);
        return hasLeft(v) || hasRight(v);
    }

    @Override
    public boolean isExternal(HeapNode v) throws InvalidPositionException {
        // Solmu on ulkosolmu (=lehti), jos sillä ei ole lapsia.
//        if (v.getLeft() != null && v.getRight() != null) {
//            return v.getLeft().getKey() == 0 && v.getRight().getKey() == 0;
//        }
//        return v.getLeft() == null && v.getRight() == null;
        return !isInternal(v);
    }

    @Override
    public boolean isRoot(HeapNode v) throws InvalidPositionException,
            EmptyTreeException {
//        return v.getParent() == null;
        checkPosition(v);
        return (v == root());
    }

    @Override
    public int size() {
        return size / 2;
    }

    @Override
    public boolean isEmpty() {
        return root == null; //  size == 0
    }

    @Override
    public Enumeration elements() {
        HeapNode[] heapNodes = new HeapNode[size];
        HeapNode rootNode = this.root;
        // Kesken, tee loppuun!
        return null;
    }

    @Override
    public Enumeration positions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void swap(HeapNode v, HeapNode w) throws InvalidPositionException {
        //        HeapNode tmp = v;
//        v = w;
//        w = tmp;
        HeapNode checkPosition1 = checkPosition(v);
        HeapNode checkPosition2 = checkPosition(w);
//        int key = w.getKey();
//        String element = w.getElement();
        KeyElementPair temp = w.getKeyAndElement();
//        checkPosition2.setKey(v.getKey());
//        checkPosition2.setElement(v.getElement());
        checkPosition2.setKeyAndElement(v.getKey(), v.getElement());
        checkPosition1.setKeyAndElement(temp.getKey(), temp.getElement());

    }

    @Override
    public Object replace(HeapNode v, KeyElementPair e) throws InvalidPositionException {
//        HeapNode oldNode = v;
//        v = e;
//        return oldNode;
//...
//        HeapNode newNode = new HeapNode(e.getKey(), e.getElement(), v.getLeft(), v.getRight(), v.getParent());
//        e = newNode;
//        return v;
//...
        HeapNode checkPosition = checkPosition(v);
        int key = v.getKey();
        String element = v.getElement();
        checkPosition.setKey(e.getKey());
        checkPosition.setElement(e.getElement());

        KeyElementPair oldKeyElementPair = new KeyElementPair(key, element);
        return oldKeyElementPair;
    }

    @Override
    public BinaryTree container() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
//        return v.getLeft();
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
    public void expandExternal(HeapNode v, KeyElementPair l, KeyElementPair r) throws
            InvalidPositionException {
        if (!isExternal(v)) {
            throw new InvalidPositionException("Kyseessä on sisäsolmu.");
        }
        // Laajentaa ulkosolmun sisäsolmuun, jossa kaksi ulkosolmulasta.
        insertLeft(v, l);
        insertRight(v, r);
//        if (isRoot(v)) {
//...
//        v.setLeft(new HeapNode(0, null, null, null, v)); // asetetaan v:n vasen lapsi
//        v.setRight(new HeapNode(0, null, null, null, v)); // asetetaan v:n oikea lapsi
//        size += 2;
//...
//            return v;
//        }
//        v.setLeft(new HeapNode(0, null, null, null, v)); // asetetaan v:n vasen lapsi
//        v.setRight(new HeapNode(0, null, null, null, v)); // asetetaan v:n oikea lapsi
//        HeapNode left = v.getLeft(); // oikeaan lapseen pitäisi myös varmaan asettaa...
//
//        return left;
    }

    @Override
    public HeapNode removeAboveExternal(HeapNode v) throws Exception {
        if (isInternal(v)) {
            throw new Exception("Kyseessä on sisäsolmu.");
        } else {
            HeapNode u = parent(v);
            HeapNode s = sibling(v);
            if (isRoot(u)) {
                // jos u on juuri, poistetaan se asettamalla v:n sisaruksen
                // s vanhemmaksi null-viite ja s itse juureksi
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

    public boolean hasLeft(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        return checkPosition.getLeft() != null;
    }

    public boolean hasLeftNotZero(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        return left != null && left.getKey() != 0;
    }

    public boolean hasRight(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        return checkPosition.getRight() != null;
    }

    public boolean hasRightNotZero(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode right = checkPosition.getRight();
        return right != null && right.getKey() != 0;
    }

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

    private HeapNode createNode(KeyElementPair element, HeapNode parent, HeapNode left,
            HeapNode right) {
        return new HeapNode(element.getKey(), element.getElement(), left, right,
                parent);
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
    public Iterator<HeapNode> children(HeapNode v) throws InvalidPositionException { // Enumeration vai Iterable<HeapNode>?
//        HeapNode[] heapNodes = new HeapNode[size];
//        for (int i = 0; i < heapNodes.length; i++) {
//            HeapNode heapNode = heapNodes[i];
//            
//        }
//        return heapNodes;

//        List<HeapNode> children = new ArrayList<>();
//        if (hasLeft(v)) {
//            children.add(leftChild(v));
//        }
//        if (hasRight(v)) {
//            children.add(rightChild(v));
//        }
//        Hashtable<HeapNode, HeapNode> children = new Hashtable<>();
//        if (hasLeftNotZero(v)) {
//            children.put(leftChild(v), leftChild(v));
//        }
//        if (hasRightNotZero(v)) {
//            children.put(rightChild(v), rightChild(v));
//        }
//        return children.elements();
//...
//        HeapNode[] heapNodes = new HeapNode[2];
//        if (hasLeftNotZero(v)) {
//            heapNodes[0] = leftChild(v);
//        }
//        if (hasRightNotZero(v)) {
//            heapNodes[1] = rightChild(v);
//        }
//        return heapNodes;
//...
//        Vector vector = new Vector();
//        if (hasLeftNotZero(v)) {
//            vector.add(leftChild(v));
//        }
//        if (hasRightNotZero(v)) {
//            vector.add(rightChild(v));
//        }
//        return vector.elements();
//...
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
     *
     * Kuljettaessa puuta esijärjestyksessä käydään ensin juuressa ja sitten
     * tämän alipuissa käymällä lapset rekursiivisesti läpi.
     *
     * @param T BinaryTree
     * @param v HeapNode
     * @param s String
     * @param level int
     * @return String
     */
    public String preorderPrint(BinaryTree T, HeapNode v, String s, int level) {

        int key = T.key(v);
        String sisennykset = "";

        if (level != 0) {
            sisennykset += "\n";
        }

        for (int i = 0; i < level; i++) {
            sisennykset += "  ";
        }

        s += sisennykset + key;

        Iterator<HeapNode> children = null;
        try {
            children = T.children(v);
        } catch (InvalidPositionException ex) {
            System.out.println("Virhe solmun sijainnissa.");
        }
        level++;
        while (children.hasNext()) {
            HeapNode nextElement = (HeapNode) children.next();
            s = preorderPrint(T, nextElement, s, level);
        }
//...
//        HeapNode[] children = null;
//        try {
//            children = T.children(v);
//        } catch (InvalidPositionException ex) {
//            System.out.println("Virhe solmun sijainnissa.");
//        }
//        
//        for (HeapNode heapNode : children) {
//            s = preorderPrint(T, heapNode, s);
//        }

        return s;
    }

    /**
     * Palauttaa avaimen kyseisestä solmusta.
     *
     * @param v HeapNode
     * @return int
     */
    public int key(HeapNode v) {
        return v.getKey();
    }

    public HeapNode remove(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        HeapNode right = checkPosition.getRight();

        if (left.getKey() != 0 && right.getKey() != 0) {
//        if (left != null && right != null) {
            throw new InvalidPositionException("Ei voida poistaa, sillä solmussa on kaksi lasta.");
        }
        HeapNode helpNode;
        if (left.getKey() != 0) {
//        if (left != null) {
            helpNode = left;
        } else if (right.getKey() != 0) {
//            } else if (right != null) {
            helpNode = right;
        } else {
            helpNode = null;
        }
        if (checkPosition == root) {
            if (helpNode != null) {
                helpNode.setParent(null);
            }
            root = helpNode;
        } else {
            HeapNode parent = checkPosition.getParent();
            if (checkPosition == parent.getLeft()) {
                parent.setLeft(helpNode);
            } else {
                parent.setRight(helpNode);
            }
            if (helpNode != null) {
                helpNode.setParent(parent);
            }
        }
        size -= 3;
        return v;
    }

    public HeapNode remove2(HeapNode v) throws InvalidPositionException {
        HeapNode checkPosition = checkPosition(v);
        HeapNode left = checkPosition.getLeft();
        HeapNode right = checkPosition.getRight();
        if (left.getKey() != 0 && right.getKey() != 0) {
            throw new InvalidPositionException("Ei voida poistaa, sillä solmussa on kaksi lasta.");
        }
        HeapNode helpNode = null;

        if (checkPosition == root) {
            root = helpNode;
            size = 0;
        } else { // poistettava solmu ei ole juuri
            HeapNode parent = checkPosition.getParent();
            left.setParent(parent);
            parent.setLeft(left);
            size -= 2;
        }
        return v;
    }

}
