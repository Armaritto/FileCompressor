import java.nio.ByteBuffer;
import java.util.HashMap;

public class Huffman {
    HashMap<ByteBuffer, Integer> dict = new HashMap<>();
    public HashMap<ByteBuffer, Integer> encode(HashMap<ByteBuffer, Integer> freq) {
        int n = freq.size();
        MinHeap minHeap = new MinHeap(n);
        for(ByteBuffer key : freq.keySet())
            minHeap.insert(new Node(key, freq.get(key)));
        for(int i=0; i<n-1; i++) {
            Node x = minHeap.extractMin();
            Node y = minHeap.extractMin();
            Node z = new Node(x,y,x.freq+y.freq);
            z.data = null;
            minHeap.insert(z);
        }
        Node root = minHeap.extractMin();
        createDict(root, 1); //dummy bit to make leading zeros arent lost
        return dict;
    }
    private void createDict(Node node, int code) {
        if(node.data != null)
            dict.put(node.data, code);
        else {
            createDict(node.left, (code << 1) | 0);
            createDict(node.right, (code << 1) | 1);
        }
    }
}
