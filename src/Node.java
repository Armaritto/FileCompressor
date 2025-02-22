import java.nio.ByteBuffer;

public class Node {
    ByteBuffer data;
    int freq;
    Node left;
    Node right;

    Node(ByteBuffer data, int freq){
        this.data = data;
        this.freq = freq;
    }
    Node(Node left, Node right, int freq){
        this.left = left;
        this.right = right;
        this.freq = freq;
    }
}
