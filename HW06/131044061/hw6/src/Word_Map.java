import java.util.*;

public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    private Node head = new Node();
    private Node last = new Node();
    private int size = 0;

    public Word_Map() {
        this.table = new Node[INITCAP];
        head = null;
        last = null;
    }

    @Override
    public Iterator iterator() {
        HWIterator iter = new HWIterator();
        return iter;
    }

    static class Node{
        private String key;
        private File_Map value;
        private Node nextNode;
        public Node(){
            key = null;
            value = null;
            nextNode = null;
        }
        public Node(String otherKey, File_Map otherValue){
            key = otherKey;
            value = otherValue;
            nextNode = null;
        }
        public void setKey(String key) { this.key = key; }
        public String getKey() { return key; }
        public void setValue(File_Map value) { this.value = value; }
        public File_Map getValue() { return value; }
        public void setNextNode(Node nextNode) { this.nextNode = nextNode; }
        public Node getNextNode() { return nextNode; }

        @Override
        public String toString() {
            return key + "\n" + value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size()==0)
            return true;
        return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        if (isEmpty())
            return false;
        int index = findIndex(key);
        if (table[index] == null)
            return false;
        if (table[index].key.equals(key))
            return true;
        return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        for (int i=0;i<table.length;i++){
            if (table[i] != null){
                if (table[i].getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        if (containsKey(key)) {
            int index = findIndex(key);
            return table[index].getValue();
        }
        return null;
    }

    private int findIndex(Object key){
        int index = key.hashCode()%table.length;
        if (index<0)
            index = index + table.length;
        while ((table[index] != null) && (!key.equals(table[index].key))) {
            index++;
            if (index>= table.length)
                index = 0;
        }
        return index;
    }

    private void rehash(){
        Node tempTable[] = table;
        table = new Node[2*tempTable.length+1];
        size = 0;
        CURRCAP = table.length;
        for (int i=0;i<tempTable.length;i++){
            if ((tempTable[i] != null))
                put(tempTable[i].key, tempTable[i].value);
        }
    }

    private class HWIterator implements Iterator {
        Node temp;
        HWIterator(){
            temp=head;
        }

        @Override
        public boolean hasNext() {
            return (temp != null);
        }

        @Override
        public Object next() {
            if (temp == null)
                throw new NoSuchElementException();
            Node res = temp;
            temp = temp.nextNode;
            return res;
        }
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
        int index = findIndex(key);
        if (table[index] == null){
            table[index] = new Node((String) key,(File_Map) value);
            if (head == null) {
                head = table[index];
                last = head;
                last.nextNode = head.nextNode;
            }
            else {
                last.setNextNode(table[index]);
                last = last.nextNode;
            }
            CURRCAP--;
            size++;
            double loadFactor = (double)(size) / (double) table.length;
            if (loadFactor>LOADFACT)
                rehash();
            return null;
        }
        //size++;
        File_Map temp = table[index].getValue();
        table[index].value = (File_Map) value;
        return temp;
    }

    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {
        Collection values = new ArrayList(m.values());
        Set<String> keys = new TreeSet();
        keys = m.keySet();
        Object keysArr[] = new String[keys.size()];
        keysArr = keys.toArray();
        for (int i=0;i<keys.size();i++)
            this.put(keysArr[i], ((ArrayList) values).get(i));
    }

    @Override
    public void clear() {
        table = new Node[INITCAP];
        head = null;
        last = null;
        CURRCAP = INITCAP;
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    public Node getLast() {
        return last;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        Set<String> result = new TreeSet<String >();
        Node temp = new Node();
        temp = head;
        while (temp!=null){
            result.add(temp.key);
            temp = temp.nextNode;
        }
        return result;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Collection<File_Map> result = new ArrayList<File_Map>();
        Node temp = new Node();
        temp = head;
        while (temp != null){
            ((ArrayList<File_Map>) result).add(temp.value);
            temp = temp.nextNode;
        }
        return result;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        HashMap<String, File_Map> result = new HashMap<>();
        for (int i=0;i<table.length;i++) {
            if (table[i] != null)
                result.put(table[i].key, table[i].value);

        }
        return (Set)result.entrySet();
    }
}
