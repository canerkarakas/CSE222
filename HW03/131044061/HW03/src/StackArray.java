public class StackArray {

    public  void test2(Element [][] elements , int row, int column, int sizeOfStack) throws Exception {
        Stack stack = new Stack();
        char id = 'A';
        int whiteCompNum = 0;
        boolean control = false;
        Element temp = new Element();
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (elements[i][j].getValue() == '1' && elements[i][j].getId() == '1'){
                    elements[i][j].setId(id);
                    stack.push(stack.create(elements[i][j]));
                    while (!stack.empty()){
                        control = false;
                        temp = stack.getElement();
                        if (temp.getColumn()-1!=-1
                                && (elements[temp.getRow()][temp.getColumn()-1].getValue() == '1'
                                && elements[temp.getRow()][temp.getColumn()-1].getId() =='1')){
                            elements[temp.getRow()][temp.getColumn()-1].setId(stack.getElement().getId());
                            stack.push(stack.create(elements[temp.getRow()][temp.getColumn()-1]));
                            control = true;
                        }
                        if (temp.getColumn()+1!=column
                                && (elements[temp.getRow()][temp.getColumn()+1].getValue() == '1'
                                && elements[temp.getRow()][temp.getColumn()+1].getId() =='1')){
                            elements[temp.getRow()][temp.getColumn()+1].setId(stack.getElement().getId());
                            stack.push(stack.create(elements[temp.getRow()][temp.getColumn()+1]));
                            control = true;
                        }
                        if (temp.getRow()+1!=row
                                && (elements[temp.getRow()+1][temp.getColumn()].getValue() == '1'
                                && elements[temp.getRow()+1][temp.getColumn()].getId() =='1')){
                            elements[temp.getRow()+1][temp.getColumn()].setId(stack.getElement().getId());
                            stack.push(stack.create(elements[temp.getRow()+1][temp.getColumn()]));
                            control = true;
                        }
                        if (temp.getRow()-1!=-1
                                && (elements[temp.getRow()-1][temp.getColumn()].getValue() == '1'
                                && elements[temp.getRow()-1][temp.getColumn()].getId() =='1')){
                            elements[temp.getRow()-1][temp.getColumn()].setId(stack.getElement().getId());
                            stack.push(stack.create(elements[temp.getRow()-1][temp.getColumn()]));
                            control = true;
                        }
                        if (control==false){
                            stack.pop();
                        }
                    }
                    id++;
                    whiteCompNum++;
                }
            }
        }
        System.out.println(whiteCompNum);
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                System.out.print(elements[i][j].getId());
            }
            System.out.println();
        }
    }

    public void test() throws Exception {
        Element element = new Element('1', 1, 2,'a');
        Stack stack = new Stack();
        System.out.println(stack.getElementNum());
        stack.push(stack.create(element));
        System.out.println(stack.getElementNum());
        stack.push(stack.create(element));
        System.out.println(stack.getElementNum());
        stack.push(stack.create(element));
        System.out.println(stack.getElementNum());
        stack.push(stack.create(element));
        System.out.println(stack.getElementNum());
        stack.pop();
        System.out.println(stack.getElementNum());
        stack.pop();
        System.out.println(stack.getElementNum());
        stack.pop();
        System.out.println(stack.getElementNum());

        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
