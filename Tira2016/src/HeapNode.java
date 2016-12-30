
public class HeapNode {

    private int key; // onko final?
    private String element; // onko final?
    private HeapNode left;
    private HeapNode right;
    private HeapNode parent;

    public HeapNode(int key, String element) {
        this.key = key;
        this.element = element;
    }

    public int getKey() {
        return key;
    }

    public String getElement() {
        return element;
    }

    public HeapNode getLeft() {
        return left;
    }

    public HeapNode getRight() {
        return right;
    }

    public HeapNode getParent() {
        return parent;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setLeft(HeapNode left) {
        this.left = left;
    }

    public void setRight(HeapNode right) {
        this.right = right;
    }

    public void setParent(HeapNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + getKey() + "," + getElement() + ")"; //esim. (7,seiska)
    }

}
