
import java.util.Enumeration;

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
        size = 1; // oli aiemmin 0
        root = new HeapNode(0, null, null, null, null);
//        root = null;
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

    /**
     *
     * Kuljettaessa puuta esijärjestyksessä käydään ensin juuressa ja sitten
     * tämän alipuissa käymällä lapset rekursiivisesti läpi.
     *
     * @param T SimpleTree
     * @param v HeapNode
     */
    public void preorderPrint(SimpleTree T, HeapNode v) {
        System.out.println(T.element(v));
        Enumeration children_of_v = T.children(v);
        while (children_of_v.hasMoreElements()) {
            HeapNode w = (HeapNode) children_of_v.nextElement();
            preorderPrint(T, w);
        }
    }

    @Override
    public HeapNode root() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return root;
    }

    @Override
    public HeapNode parent(HeapNode v) throws Exception {
        if (!isRoot(v)) {
            return v.getParent();
        }
        throw new Exception("Kyseessä on juuri.");
    }

    @Override
    public Enumeration children(HeapNode v) { // Enumeration vai HeapNode[]?
//        HeapNode[] heapNodes = new HeapNode[size];
//        for (int i = 0; i < heapNodes.length; i++) {
//            HeapNode heapNode = heapNodes[i];
//            
//        }
//        return heapNodes;
        return null;
    }

    @Override
    public boolean isInternal(HeapNode v) {
        return !isExternal(v); // Kun solmu ei ole ulkosolmu, se on sisäsolmu.
    }

    @Override
    public boolean isExternal(HeapNode v) {
        // Solmu on ulkosolmu (=lehti), jos sillä ei ole lapsia.
        if (v.getLeft() != null && v.getRight() != null) {
            return v.getLeft().getKey() == 0 && v.getRight().getKey() == 0;
        }
        return v.getLeft() == null && v.getRight() == null;
    }

    @Override
    public boolean isRoot(HeapNode v) {
        return v.getParent() == null;
        //return root.equals(v);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null; // size == 0
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
        HeapNode tmp = v;
        v = w;
        w = tmp;
    }

    @Override
    public Object replace(HeapNode v, HeapNode e) {
        HeapNode oldNode = v;
        v = e;
        return oldNode;
    }

    @Override
    public BinaryTree container() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int element(HeapNode v) {
        return v.getKey();
    }

    @Override
    public HeapNode leftChild(HeapNode v) {
        return v.getLeft();
    }

    @Override
    public HeapNode rightChild(HeapNode v) {
        return v.getRight();
    }

    @Override
    public HeapNode sibling(HeapNode v) {
        if (v.getParent().getLeft().equals(v)) {
            return v.getParent().getRight();
        }
        return v.getParent().getLeft();
    }

    @Override
    public void expandExternal(HeapNode v) throws Exception {
        if (isInternal(v)) {
            throw new Exception("Kyseessä on sisäsolmu.");
        } else {
//        if (isRoot(v)) {
            v.setLeft(new HeapNode(0, null, null, null, v)); // asetetaan v:n vasen lapsi
            v.setRight(new HeapNode(0, null, null, null, v)); // asetetaan v:n oikea lapsi
            size += 2;
//            return v;
//        }
//        v.setLeft(new HeapNode(0, null, null, null, v)); // asetetaan v:n vasen lapsi
//        v.setRight(new HeapNode(0, null, null, null, v)); // asetetaan v:n oikea lapsi
//        HeapNode left = v.getLeft(); // oikeaan lapseen pitäisi myös varmaan asettaa...
//
//        return left;
        }
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
}
