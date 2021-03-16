public class Calculator {

    public  void calculator(Stack<Character>[] stacks, int sizeOfStacks) throws Exception {
        int sizePostArr = stacks[sizeOfStacks-1].getElementNumber();
        char [] postfixArr = new char[sizePostArr];
        Stack<Character> tempStack = new Stack<Character>();
        char tempCh;
        boolean control = true;
        boolean controlForOperations = true;
        boolean control2 = false;
        int i = 0;
        boolean controlForExt = false;
        while (stacks[sizeOfStacks-1].getElementNumber()!=0){
            switch (stacks[sizeOfStacks-1].peek()){
                case '(':tempStack.push(stacks[sizeOfStacks-1].pop());control2=true;break;
                case ')':
                    while (!tempStack.empty()){
                        if (tempStack.peek() != '(') {
                            postfixArr[i] = tempStack.pop();
                            i++;
                        }
                        else
                            tempStack.pop();
                    }
                    stacks[sizeOfStacks-1].pop();
                    control2=false;
                    break;
                case '+':
                    while(!tempStack.empty() && isLargerOperation(stacks[sizeOfStacks-1].peek(),tempStack.peek())){
                        postfixArr[i] = tempStack.pop();
                        i++;
                    }
                    tempStack.push(stacks[sizeOfStacks-1].pop());
                    control2=false;break;
                case '-':
                    while(!tempStack.empty() && isLargerOperation(stacks[sizeOfStacks-1].peek(),tempStack.peek())){
                        postfixArr[i] = tempStack.pop();
                        i++;
                    }
                    if (control2==true){
                        postfixArr[i] = '0';
                        tempStack.push(stacks[sizeOfStacks-1].pop());
                        i++;
                    }
                    else{
                        tempStack.push(stacks[sizeOfStacks-1].pop());
                    }
                    control2 = false;break;
                case '*':
                    while(!tempStack.empty() && isLargerOperation(stacks[sizeOfStacks-1].peek(),tempStack.peek())){
                        postfixArr[i] = tempStack.pop();
                        i++;
                    }
                    tempStack.push(stacks[sizeOfStacks-1].pop());control2=false;break;
                case '/':
                    while(!tempStack.empty() && isLargerOperation(stacks[sizeOfStacks-1].peek(),tempStack.peek())){
                        postfixArr[i] = tempStack.pop();
                        i++;
                    }

                    tempStack.push(stacks[sizeOfStacks-1].pop());control2=false;break;
                case '.':postfixArr[i] = stacks[sizeOfStacks-1].pop();i++;control2=false;break;
                default:
                    if ((stacks[sizeOfStacks-1].peek()>='a' && stacks[sizeOfStacks-1].peek()<='z') ||
                            (stacks[sizeOfStacks-1].peek()>='0' && stacks[sizeOfStacks-1].peek()<='9')) {
                        if(stacks[sizeOfStacks-1].peek() == 's'){
                            char temp = stacks[sizeOfStacks-1].pop();
                            temp = 'S';
                            if (stacks[sizeOfStacks-1].peek()=='i'){
                                while(!tempStack.empty() && isLargerOperation(temp,tempStack.peek())==false){
                                    postfixArr[i] = tempStack.pop();
                                    i++;
                                }
                                tempStack.push('S');
                                stacks[sizeOfStacks-1].pop();
                                stacks[sizeOfStacks-1].pop();
                            }
                            else{
                                postfixArr[i] = temp;
                                i++;
                            }
                            control2=false;
                        }
                        else if(stacks[sizeOfStacks-1].peek() == 'c'){
                            char temp = stacks[sizeOfStacks-1].pop();
                            temp = 'C';
                            if (stacks[sizeOfStacks-1].peek()=='o'){
                                while(!tempStack.empty() && isLargerOperation(temp,tempStack.peek())==false){
                                    postfixArr[i] = tempStack.pop();
                                    i++;
                                }
                                tempStack.push('C');
                                stacks[sizeOfStacks-1].pop();
                                stacks[sizeOfStacks-1].pop();
                            }
                            else{
                                postfixArr[i] = temp;
                                i++;
                            }
                            control2=false;
                        }
                        else if(stacks[sizeOfStacks-1].peek() == 'a'){
                            char temp = stacks[sizeOfStacks-1].pop();
                            temp = 'C';
                            if (stacks[sizeOfStacks-1].peek()=='b'){
                                while(!tempStack.empty() && isLargerOperation(temp,tempStack.peek())==false){
                                    postfixArr[i] = tempStack.pop();
                                    i++;
                                }
                                tempStack.push('A');
                                stacks[sizeOfStacks-1].pop();
                                stacks[sizeOfStacks-1].pop();
                            }
                            else{
                                postfixArr[i] = temp;
                                i++;
                            }
                            control2=false;
                        }
                        else{
                            postfixArr[i] = stacks[sizeOfStacks-1].pop();
                            i++;
                            control2=false;
                        }
                    }
                    else {
                        stacks[sizeOfStacks - 1].pop();
                    }
            }
        }
        while(tempStack.empty()!=true){
            postfixArr[i] = tempStack.pop();
            i++;
        }
        sizePostArr = i;
        for (int j=0;j<sizePostArr;j++){
            System.out.print(postfixArr[j] + " ");
        }
        System.out.println();
        System.out.println(calc(postfixArr, sizePostArr, stacks, sizeOfStacks));
    }

    public double calc(char [] postfixArr, int sizePostArr, Stack<Character> [] stacks, int sizeOfStacks) throws Exception {
        Stack<Double> stack = new Stack<Double>();
        double result = 0;
        double var1;
        double var2;
        for (int i=0;i<sizePostArr;i++){
            if (postfixArr[i]=='*'){
                var1 = stack.pop();
                var2 = stack.pop();
                stack.push(var1*var2);
            }
            else if (postfixArr[i]=='/') {
                var2 = stack.pop();
                var1 = stack.pop();
                stack.push(var1*var2);
            }
            else if (postfixArr[i]=='+'){
                var1 = stack.pop();
                var2 = stack.pop();
                stack.push(var1+var2);
            }
            else if (postfixArr[i]=='-'){
                var2 = stack.pop();
                var1 = stack.pop();
                stack.push(var1-var2);
            }
            else if (postfixArr[i] =='S'){
                Math temp = new Math();
                var1 = stack.pop();
                stack.push(temp.sin(var1));
            }
            else if (postfixArr[i] == 'C'){
                Math temp = new Math();
                var1 = stack.pop();
                stack.push(temp.cos(var1));
            }
            else if (postfixArr[i]=='A'){
                Math temp = new Math();
                var1 = stack.pop();
                stack.push(temp.abs(var1));
            }
            else if (postfixArr[i] =='0'){
                String temp = new String();
                boolean control = true;
                temp = temp + postfixArr[i];
                stack.push(Double.parseDouble(temp));
            }
            else{
                if (postfixArr[i]>='1' && postfixArr[i]<='9' || postfixArr[i] == '.'){
                    String temp = new String();
                    boolean control = true;
                    temp = temp + postfixArr[i];
                    i++;
                    int j = i;
                    while(control == true){
                        if ((postfixArr[j]>='0' && postfixArr[j]<='9') || postfixArr[j] == '.'){
                            temp = temp + postfixArr[j];
                            j++;
                        }
                        else{
                            control = false;
                        }
                    }
                    i = j-1;
                    stack.push(Double.parseDouble(temp));
                }
                else {
                    if (postfixArr[i] != '0') {
                        Stack<Character> tempStack = new Stack<Character>();
                        int indexOfStacks = 0;
                        boolean control = true;
                        while (control == true) {
                            if (stacks[indexOfStacks].peek() == postfixArr[i]) {
                                String temp = new String();
                                tempStack.push(stacks[indexOfStacks].pop());
                                tempStack.push(stacks[indexOfStacks].pop());
                                while (stacks[indexOfStacks].empty() != true) {
                                    tempStack.push(stacks[indexOfStacks].pop());
                                    temp = temp + tempStack.peek();
                                }
                                stack.push(Double.parseDouble(temp));
                                control = false;
                            } else {
                                indexOfStacks++;
                                if (indexOfStacks == sizeOfStacks) {
                                    control = false;
                                    System.out.println("hatali!!!!");
                                }
                            }
                        }
                        while (tempStack.empty() == false) {
                            stacks[indexOfStacks].push(tempStack.pop());
                        }
                        indexOfStacks = 0;
                    }
                }
            }
        }
        return stack.peek();
    }

    public boolean isLargerOperation(Character ch1, Character ch2){
        if(ch1 == 'S' || ch1 == 'A' || ch1 == 'C'){
            switch (ch2){
                case '-':return true;
                case '+':return true;
                case '*':return true;
                case '/':return true;

                default:return true;
            }
        }
        if (ch1=='*'){
            switch (ch2){
                case '-':return true;
                case '+':return true;
                default:return false;
            }
        }
        if (ch1 == '/'){
            switch (ch2){
                case '-':return true;
                case '+':return true;
                default:return false;
            }
        }
        if (ch1=='+'){
            switch (ch2){
                case '-':return true;
                case '+':return true;
                default:return false;
            }
        }
        if (ch1 == '-'){
            switch (ch2){
                case '-':return true;
                case '+':return true;
                default:return false;
            }
        }
        else{
            return false;
        }
    }
}
