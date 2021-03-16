import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E>{
    private HWColor [] data;
    private int capacity;
    private int size;
    Comparator<HWColor> comparator;

    public PriorityQueue(Comparator<HWColor> comp){
        capacity = 10;
        size = 1;
        data = new HWColor[capacity];
        comparator = comp;
    }
    public HWColor peek(){
        if (isEmpty())
            return null;
        return data[0];
    }
    public void offer(HWColor item){
        if (capacity==size){
            this.newCapacityForData();
        }
        data[size-1] = item;
        int child = size-1;
        int parent = (child-1) / 2;
        boolean control = true;
        while (control == true && this.comparator.compare(data[parent], data[child])<0){
            this.swap(parent,child);
            child = parent;
            parent = (child-1)/2;
            if (parent<0)
                control = false;
        }
        size++;
    }
    public HWColor remove(){
        if (this.isEmpty())
            throw new NoSuchElementException("Empty");
        else {
            HWColor temp = data[0];
            int parent = 0;
            size--;
            data[parent] = data[size-1];
            while(true){
                int leftChild = (parent*2) + 1;
                if (parent>=size-1)
                    return temp;
                if (leftChild>=size-1)
                    return temp;
                else {
                    int rightChild = leftChild+1;
                    if (this.comparator.compare(data[rightChild], data[leftChild])>0){
                        swap(parent,rightChild);
                        parent = rightChild;
                    }
                    else {
                        swap(parent,leftChild);
                        parent = leftChild;
                    }
                }
            }
        }
    }
    public HWColor poll(){
        if (isEmpty())
            return null;
        return remove();
    }
    public HWColor element(){
        if (isEmpty())
            throw new NoSuchElementException("Empty");
        return data[0];
    }
    public void swap(int parent, int child){
        HWColor temp;
        temp = data[parent];
        data[parent] = data[child];
        data[child] = temp;
    }
    public void newCapacityForData(){
        capacity = capacity * 2;
        HWColor[] temp = new HWColor[capacity];
        for (int i =0;i<size-1;i++)
            temp[i] = data[i];
        data = new HWColor[capacity];
        for (int i =0;i<size-1;i++)
            data[i] = temp[i];
    }
    public boolean isEmpty(){
        if (size<=1)
            return true;
        return false;
    }
    public int getSize(){return size;}
    public int getCapacity(){return capacity;}
    @Override
    public String toString() {
        String temp = new String();
        for (int i=0;i<size-1;i++)
            temp = temp + data[i] + "\n";
        return temp;
    }
}