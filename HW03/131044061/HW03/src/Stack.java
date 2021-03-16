import org.w3c.dom.Node;

public class Stack {

    private NodeElement headStack = null;
    private NodeElement lastStack = null;
    private int elementNum = 0;
    //private boolean isEmpty = true;
    private char stackID;

    private static class NodeElement {
        private Element element;
        private NodeElement next;
        private NodeElement prev;

        /** Creates a new node that references another node and prev node
         @param otherElem The data stored
         @param nodeNext The node referenced by new node
         */
        private NodeElement(Element otherElem, NodeElement nodeNext, NodeElement nodePrev) {
            element = otherElem;
            next = nodeNext;
            prev = nodePrev;
        }

        private NodeElement(){
            element = null;
            next = null;
            prev = null;
        }

        private Element getElement(){ return element; }

        @Override
        public String toString() {
            String str = new String();
            //str = str + "id= " + element.getId() + " row=" + element.getRow() + " column=" + element.getColumn();
            str = str + element;
            return str;
        }
    }

    public Stack(){
        headStack = null;
        lastStack = null;
        elementNum = 0;
       // isEmpty = true;
    }

    public Stack(Stack other){
        this.headStack = other.headStack;
        this.lastStack = other.lastStack;
        elementNum = other.elementNum;
       // this.isEmpty = other.isEmpty;
    }

    public int getElementNum() {
        return elementNum;
    }

    public void setHeadStack(Stack other){
        this.headStack = other.headStack;
       // this.isEmpty = other.isEmpty;
        this.elementNum = other.elementNum;
    }

    public NodeElement getHeadStack() {
        return headStack;
    }

    public void setLastStack(Stack other) {
        this.lastStack = other.lastStack;
       // this.isEmpty = other.isEmpty;
    }

    public NodeElement getLastStack() {
        return lastStack;
    }

    public void setStackID(char id){
        stackID = id;
    }

    public char getStackID(){return stackID;}

    public Element getElement(){ return peek().getElement(); }

    public Element lastElement(){return lastStack.getElement();}

    public NodeElement create(Element element){
        NodeElement temp = new NodeElement(element, null, null);
        return temp;
    }

    public NodeElement push(NodeElement newNode){
        if(this.empty() == true){
            lastStack = newNode;
            headStack = newNode;
            headStack.next = null;
            headStack.prev = null;
            lastStack.next = headStack.next;
            lastStack.prev = headStack.prev;
          //  this.isEmpty = false;
            elementNum++;
        }
        else{
           /* NodeElement temp = new NodeElement();
            temp.prev = headStack.prev;
            temp.next = headStack.next;
            temp = headStack;*/
            headStack.next = newNode;
            headStack.next.prev = headStack;
            headStack = headStack.next;
            elementNum++;
        }
        return this.headStack;
    }

    public NodeElement pop() throws Exception {
        NodeElement temp = new NodeElement();
        if(this.empty() == true){
            throw new Exception();
        }
        if (elementNum==1){
            temp = headStack;
            headStack = null;
            elementNum--;
            return temp;
        }
        temp = headStack;
        headStack = headStack.prev;
        headStack.next = null;
        elementNum--;
        return temp;
    }

    public NodeElement peek(){ return headStack; }

    public boolean empty(){
        if(elementNum==0)
            return true;
        return false;
    }

    @Override
    public String toString() {
        String str = new String();
        NodeElement temp = new NodeElement();
        temp = headStack;
        while(temp != null){
            str = str + temp.getElement().getId();
            temp = temp.prev;
        }
        return str;
    }
}
