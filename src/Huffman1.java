import java.util.HashMap;

public class Huffman1 {
    HashMap<Byte, Integer> dict = new HashMap<>();
    public HashMap<Byte, Integer> encode(HashMap<Byte, Integer> freq) {
        int n = freq.size();
        MinHeap1 minHeap = new MinHeap1(n);
        for(byte key : freq.keySet())
            minHeap.insert(new Node1(key, freq.get(key)));
        for(int i=0; i<n-1; i++) {
            Node1 x = minHeap.extractMin();
            Node1 y = minHeap.extractMin();
            Node1 z = new Node1(x,y,x.freq+y.freq,true);
            z.data = (byte) 0;
            minHeap.insert(z);
        }
        Node1 root = minHeap.extractMin();
        createDict(root, 1); //dummy bit to make leading zeros arent lost
        return dict;
    }
    private void createDict(Node1 node, int code) {
        if(!node.internalNode)
            dict.put(node.data, code);
        else {
            createDict(node.left, (code << 1) | 0);
            createDict(node.right, (code << 1) | 1);
        }
    }
}
