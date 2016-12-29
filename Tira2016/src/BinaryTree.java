
import java.util.Enumeration;

/**
 * Binääripuu on järjestetty puu, jossa solmuilla on joko ei yhtään tai kaksi
 * lasta.
 *
 * @author ylhaart
 */
public class BinaryTree implements SimpleTree {

    private int size;

    /**
     * Kun luodaan uusi binääripuu, se on aluksi tyhjä (size = 0).
     */
    public BinaryTree() {
        size = 0;
    }

    /**
     * Palauttaa alkion kyseisestä solmusta.
     *
     * @return HeapNode
     */
    public HeapNode element() {
        return null;
    }

    /**
     * Palauttaa viittauksen puuhun, joka sisältää kyseisen solmun.
     *
     * @return BinaryTree
     */
    public BinaryTree container() {
        return null;
    }

    @Override
    public HeapNode root() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeapNode parent(HeapNode v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        // ei valmis, täydennä!
        return v;
    }
}
