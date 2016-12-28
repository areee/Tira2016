
public class HeapNode {

    private final int key;
    private final String data;
    private HeapNode left;
    private HeapNode right;
    private HeapNode parent;

    public HeapNode(int key, String data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public String getData() {
        return data;
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

    public void setLeft(HeapNode left) {
        this.left = left;
    }

    public void setRight(HeapNode right) {
        this.right = right;
    }

    public void setParent(HeapNode parent) {
        this.parent = parent;
    }

}
