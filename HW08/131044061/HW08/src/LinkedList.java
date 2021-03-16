import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>{

    private Node<E> head = null;
    private int size = 0;

    public LinkedList(){head = null;}

    public LinkedList(Node<E> node){ head = node; }

    public LinkedList(LinkedList<E> list){ head = list.head; }

    @Override
    public Iterator<E> iterator() {
        LinkedListIterator iter = new LinkedListIterator();
        return iter;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node(){
            data = null;
            next = null;
        }
        private Node(E data){
            this.data = data;
            next = null;
        }
    }

    public class LinkedListIterator implements Iterator<E>{
        private LinkedList.Node<E> temp;
        LinkedListIterator(){temp = head;}
        @Override
        public boolean hasNext() {
            if (temp==null)
                return false;
            if (temp.next!=null)
                return true;
            return false;
        }
        @Override
        public E next() {
             if (temp==null)
                 throw new NoSuchElementException();
            LinkedList.Node<E> result = temp;
            temp = temp.next;
            return result.data;
        }
        public int size(){
            LinkedList.Node<E> temp2 = head;
            int size = 0;
            while (temp2.next!=null){
                size++;
                temp2 = temp2.next;
            }
            return size+1;
        }
    }

    public void add(E element){
        if (head == null){
            head = new Node<E>(element);
            size++;
            return;
        }
        Node<E> temp = new Node<>();
        temp = head;
        temp.next = head.next;
        while (temp.next!=null)
            temp = temp.next;
        temp.next = new Node<>(element);
        temp.next.next = null;
        size++;
    }

    public void add(int index, E element){
        if (head==null && index!=0)
            throw new NullPointerException();
        if (index>size+1)
            throw new NullPointerException();
        Node<E> temp = new Node<>();
        temp = head;
        temp.next = head.next;
        if (index==size){
            while (temp.next!=null)
                temp = temp.next;
            temp.next = new Node<>(element);
        }
        else{
            while (index>0){
                temp = temp.next;
                index--;
            }
            Node<E> temp2 = new Node<>();
            temp2 = temp.next;
            temp2.next = temp.next.next;
            temp.next = new Node<>(element);
            temp.next.next = temp2;
            temp.next.next.next = temp2.next;
        }
        size++;
    }

    public E get(int index){
        if (head==null && index!=0)
            throw new NullPointerException();
        if (index>size+1)
            throw new NullPointerException();
        Node<E> temp = new Node<>();
        temp = head;
        temp.next = head.next;
        if (index==size){
            while (temp.next!=null)
                temp = temp.next;
            return temp.data;
        }
        else{
            while (index>0){
                temp = temp.next;
                index--;
            }
            return temp.data;
        }
    }

    public Node<E> getHead() { return head; }

    public boolean contains(E target){
        Node<E> temp = head;
        if (temp==null)
            throw new NoSuchElementException();
        while (temp!=null){
            if (temp.data.equals(target))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public int size() { return size; }

    @Override
    public String toString() {
        String string = new String();
        Node<E> temp = head;
        while (temp!= null) {
            string = string + temp.data.toString() + "\n";
            temp = temp.next;
        }
        return string;
    }
}
