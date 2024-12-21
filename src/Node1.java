public class Node1 {
    byte data;
    int freq;
    Node1 left;
    Node1 right;
    boolean internalNode;

    Node1(byte data, int freq) {
        this.data = data;
        this.freq = freq;
    }
    Node1(Node1 left, Node1 right, int freq, boolean internalNode) {
        this.left = left;
        this.right = right;
        this.freq = freq;
        this.internalNode = internalNode;
    }
}
