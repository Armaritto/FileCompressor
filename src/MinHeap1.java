/**
 * Implementation of MinHeap from Data Structures and Algorithms Course
 */
public class MinHeap1 {
    Node1[] arr;
    int size;
    int capacity;
    MinHeap1(int c){
        size = 0;
        capacity = c;
        arr = new Node1[c];
    }
    int left(int i) {
        return (2*i + 1);
    }
    int right(int i) {
        return (2*i + 2);
    }
    int parent(int i) {
        return (i-1)/2;
    }
    void insert(Node1 x){
        if(size == capacity) return;
        size++;
        arr[size-1] = x;
        for(int i=size-1; i!=0 && arr[parent(i)].freq>arr[i].freq;){
            Node1 temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }
    void minHeapify(int i){
        int lt = left(i);
        int rt = right(i);
        int smalest = i;
        if(lt<size && arr[lt].freq<arr[i].freq)
            smalest = lt;
        if(rt<size && arr[rt].freq<arr[smalest].freq)
            smalest = rt;
        if(smalest != i){
            Node1 temp = arr[i];
            arr[i] = arr[smalest];
            arr[smalest] = temp;
            minHeapify(smalest);
        }
    }
    Node1 extractMin(){
        if(size == 0)
            return null;
        if(size == 1){
            size--;
            return arr[0];
        }
        Node1 temp = arr[0];
        arr[0] = arr[size-1];
        arr[size-1] = temp;
        size--;
        minHeapify(0);
        return arr[size];
    }
    void print(){
        for(int i=0; i<size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}