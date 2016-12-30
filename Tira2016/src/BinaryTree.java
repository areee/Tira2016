
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
        size = 0;
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
     * Solmun v syvyys on sen esivanhempien lukumäärä poislukien solmu itse.
     *
     * @param T SimpleTree
     * @param v HeapNode
     * @return int
     */
    public int depth(SimpleTree T, HeapNode v) {
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
     */
    public int height1(SimpleTree T) {
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
    public HeapNode root() {
        try {
            return root;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HeapNode parent(HeapNode v) {
        try {
            return v.getParent();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Enumeration children(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternal(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isExternal(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // root == null
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
    public Object replace(HeapNode v, Object e) {
        // ei valmis, täydennä!
        return v;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeapNode rightChild(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeapNode sibling(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void expandExternal(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeapNode removeAboveExternal(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
