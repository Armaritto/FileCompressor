import java.util.HashMap;

public class Huffman {
    HashMap<String, String> dict = new HashMap<>();
    public HashMap<String, String> encode(HashMap<String, Integer> freq) {
        int n = freq.size();
        MinHeap minHeap = new MinHeap(n);
        for(String key : freq.keySet())
            minHeap.insert(new Node(key, freq.get(key)));
        for(int i=0; i<n-1; i++) {
            Node x = minHeap.extractMin();
            Node y = minHeap.extractMin();
            Node z = new Node(x,y,x.freq+y.freq);
            z.data = null;
            minHeap.insert(z);
        }
        Node root = minHeap.extractMin();
        createDict(root, "");
        return dict;
    }
    private void createDict(Node node, String code) {
        if(node.data != null)
            dict.put(node.data, code);
        else {
            createDict(node.left, code + "0");
            createDict(node.right, code + "1");
        }
    }
}
