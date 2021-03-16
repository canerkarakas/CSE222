public class Stack<E> {

    private NodeElement headStack = null;
    private NodeElement lastStack = null;
    private int elementNum = 0;

    private static class NodeElement<E> {
        private E element;
        private NodeElement next;
        private NodeElement prev;

        private NodeElement(E otherElem, NodeElement nodeNext, NodeElement nodePrev) {
            element = otherElem;
            next = nodeNext;
            prev = nodePrev;
        }

        private NodeElement(){
            element = null;
            next = null;
            prev = null;
        }

        @Override
        public String toString() {
            String str = new String();
            str = str + element;
            return str;
        }
    }

    public E lastElement(){return (E) lastStack.element;}

    public NodeElement create(E element){
        NodeElement temp = new NodeElement(element, null, null);
        return temp;
    }

    public E push(E newElement){
        NodeElement<E> newNode = new NodeElement<E>();
        newNode.element = newElement;
        if(this.empty() == true){
            lastStack = newNode;
            headStack = newNode;
            headStack.next = null;
            headStack.prev = null;
            lastStack.next = headStack.next;
            lastStack.prev = headStack.prev;
            elementNum++;
        }
        else{
            headStack.next = newNode;
            headStack.next.prev = headStack;
            headStack = headStack.next;
            elementNum++;
        }
        return (E)this.headStack.element;
    }

    public int getElementNumber(){return elementNum;}

    public E pop() throws Exception {
        NodeElement temp = new NodeElement();
        if(this.empty() == true){
            throw new Exception();
        }
        if (elementNum==1){
            temp = headStack;
            headStack = null;
            lastStack = null;
            elementNum--;
            return (E)temp.element;
        }
        temp = headStack;
        headStack = headStack.prev;
        headStack.next = null;
        elementNum--;
        return (E)temp.element;
    }

    public E peek(){ return (E)headStack.element; }

    public boolean empty(){
        if (elementNum>0){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = new String();
        NodeElement temp = new NodeElement();
        temp = headStack;
        while(temp != null){
            str = str + temp.element;
            temp = temp.prev;
        }
        return str;
    }
}
