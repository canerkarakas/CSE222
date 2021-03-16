import org.w3c.dom.Node;

import java.sql.Time;
import java.util.Iterator;

public class ExpLinkedList implements Iterable<ExpLinkedList>, Iterator<ExpLinkedList>{

    private NodeExp headExp = null;
    private NodeDay headDay = null;
    private NodeExp iteration = null;

    public ExpLinkedList(NodeExp exp){
        headExp = exp;
        /*headDay = new NodeDay(headExp);
        headDay.dayExp = headExp;*/
    }

    public ExpLinkedList(){
        headExp = null;
        headDay = null;
    }

    public ExpLinkedList(ExpLinkedList list){
        headExp = list.headExp;
        headDay = list.headDay;
    }

    @Override
    public Iterator<ExpLinkedList> iterator() {
        iteration = headExp;
        iteration.next = headExp.next;
        return this;
    }

    @Override
    public boolean hasNext() {
        if(iteration.next != null){
            return true;
        }
        return false;
    }

    @Override
    public ExpLinkedList next() {
        if (iteration.next == null){
            throw new NullPointerException("has not node");
        }
        else
            iteration = iteration.next;
        return this;
    }

    public MachineLearningExp getIter(){
        return iteration.exp;
    }

    private static class NodeExp {
        private MachineLearningExp exp;
        private NodeExp next;
        /** Creates a new node with a null next field
         @param expItem The data stored
         */
        private NodeExp(MachineLearningExp expItem) {
            exp = expItem;
            next = null;
        }
        /** Creates a new node that references another node
         @param expItem The data stored
         @param nodeRef The node referenced by new node
         */
        private NodeExp(MachineLearningExp expItem, NodeExp nodeRef) {
            exp = expItem;
            next = nodeRef;
        }

        private NodeExp(){
            exp = new MachineLearningExp();
            next = null;
        }
    }

    private static class NodeDay {
        private NodeExp dayExp;
        private NodeDay next;
        /** Creates a new node with a null next field
         */
        private NodeDay(){
            dayExp = null;
            next = null;
        }
        private NodeDay(NodeExp dayItem) {
            dayExp = dayItem;
            next = null;
        }
        private NodeDay(NodeDay dayItem) {
            dayExp = dayItem.dayExp;
            next = dayItem.next;
        }
        /** Creates a new node that references another node
         @param dayItem The data stored
         @param nodeRef The node referenced by new node
         */
        private NodeDay(NodeExp dayItem, NodeDay nodeRef) {
            dayExp = dayItem;
            next = nodeRef;
        }
    }

    public void addExp(MachineLearningExp exp){
        if (headExp == null){
            headExp = new NodeExp(exp);
            headDay = new NodeDay(headExp);
            headDay.dayExp = headExp;
            headDay.dayExp.next = headExp.next;
        }
        else{
            NodeDay temp = new NodeDay(headDay);
            temp = headDay;
            temp.next = headDay.next;
            int i = 1;
            int counter = 0;
            boolean control = true;
            while(temp.next != null){
                temp = temp.next;
                i++;
            }
            counter = temp.dayExp.exp.getDay();
            temp = headDay;
            temp.next = headDay.next;
            if(i==1){
                if(counter < exp.getDay()){
                    while(temp.next != null){
                        temp = temp.next;
                    }
                    while(temp.dayExp.next != null){
                        temp.dayExp = temp.dayExp.next;
                    }
                    temp.dayExp.next = new NodeExp(exp);
                    temp.next = new NodeDay();
                    temp.next.dayExp = temp.dayExp.next;
                    temp.next.dayExp.next = temp.dayExp.next.next;
                }
                else if (counter == exp.getDay()){
                    while(temp.dayExp.next != null){
                        temp.dayExp = temp.dayExp.next;
                    }
                    NodeExp newExp = new NodeExp(exp);
                    temp.dayExp.next = newExp;
                    temp.dayExp.next.next = newExp.next;
                   // temp.dayExp.next = new NodeExp(exp);
                }
                else{
                    NodeExp tempExp = new NodeExp(exp);
                    tempExp.next = headExp;
                    NodeDay tempDay = new NodeDay();
                    tempDay.next = headDay;
                    tempDay.dayExp = tempExp;
                    tempDay.dayExp.next = tempExp.next;
                    headExp = tempExp;
                    headExp.next = tempExp.next;
                    headDay = tempDay;
                    headDay.next = tempDay.next;
                }
            }
            else{
                if(counter < exp.getDay()){////////en sona ekleme///////
                    NodeDay temp2 = new NodeDay();
                    temp2 = headDay;
                    temp2.next = headDay.next;
                    while(temp2.next != null){
                        temp2 = temp2.next;
                    }
                    while(temp2.dayExp.next != null){
                        temp2.dayExp = temp2.dayExp.next;
                    }
                    temp2.dayExp.next = new NodeExp(exp);
                    temp2.next = new NodeDay();
                    temp2.next.dayExp = temp2.dayExp.next;
                    temp2.next.dayExp.next = temp2.dayExp.next.next;
                }
                else if(counter == exp.getDay()){//////////ayni gun en sona ekleme/////////
                    while(temp.next!=null){
                        temp = temp.next;
                    }
                    while(temp.dayExp.next != null){
                        temp.dayExp = temp.dayExp.next;
                    }
                    temp.dayExp.next = new NodeExp(exp);
                }
                else{/////////////farkli gun araya ekleme/////////////
                    if(headDay.dayExp.exp.getDay()>exp.getDay()){
                        NodeExp tempExp = new NodeExp(exp);
                        tempExp.next = headExp;
                        NodeDay tempDay = new NodeDay();
                        tempDay.next = headDay;
                        tempDay.dayExp = tempExp;
                        headExp = tempExp;
                        headExp.next = tempExp.next;
                        headDay = tempDay;
                        headDay.next = tempDay.next;
                    }
                    else if (headDay.dayExp.exp.getDay() == exp.getDay()){
                        while(temp.dayExp.next != temp.next.dayExp){
                            temp.dayExp = temp.dayExp.next;
                        }
                        temp.dayExp.next = new NodeExp(exp);
                        temp.dayExp.next.next = temp.next.dayExp;
                        temp.dayExp.next.next.next = temp.next.dayExp.next;
                    }
                    else{
                        while(temp.next.dayExp.exp.getDay()<exp.getDay()){
                            temp = temp.next;
                        }
                        if (temp.next.dayExp.exp.getDay() == exp.getDay()){
                            temp = temp.next;
                            while(temp.dayExp.next != temp.next.dayExp){
                                temp.dayExp = temp.dayExp.next;
                            }
                            temp.dayExp.next = new NodeExp(exp);
                            temp.dayExp.next.next = temp.next.dayExp;
                        }
                        else{
                            while(temp.dayExp.next != temp.next.dayExp){
                                temp.dayExp = temp.dayExp.next;
                            }
                            temp.dayExp.next = new NodeExp(exp);
                            temp.dayExp.next.next = temp.next.dayExp;
                            NodeDay newDay = new NodeDay();
                            newDay.next = temp.next;
                            newDay.dayExp = temp.dayExp.next;
                            newDay.dayExp.next = temp.dayExp.next.next;
                            temp.next = newDay;
                            temp.next.next = newDay.next;
                        }
                    }
                }
            }
        }
    }

    public MachineLearningExp getExp(int day, int index){
        try {
            if(index < 0){
                Exception e;
                throw e = new Exception();
            }
        }
        catch (Exception e){
            System.out.println("index 0 veya 0 dan buyuk olmalidir");
            System.exit(1);
        }
        NodeExp tempExp = new NodeExp(headExp.exp);
        tempExp = headExp;
        tempExp.next = headExp.next;
        int i=0;
        while(tempExp.exp.getDay() != day){
            tempExp = tempExp.next;
        }
        for (i=0;i<index;i++){
            if (tempExp.next != null){
                if (tempExp.exp.getDay() != tempExp.next.exp.getDay()){
                    i = index+1;
                }
                else
                    tempExp = tempExp.next;
            }
            else{
                return null;
            }
        }
        if(i>index){
            return null;
        }
        else return tempExp.exp;
    }

    public MachineLearningExp setExp(int day, int index, String s, Time t, boolean c, float a){
        try {
            if(index < 0){
                Exception e;
                throw e = new Exception();
            }
        }
        catch (Exception e){
            System.out.println("index 0 veya 0 dan buyuk olmalidir");
            System.exit(1);
        }
        NodeExp tempExp = new NodeExp(headExp.exp);
        tempExp = headExp;
        tempExp.next = headExp.next;
        int i=0;
        while(tempExp.exp.getDay() != day){
            tempExp = tempExp.next;
        }
        for (i=0;i<index;i++){
            if (tempExp.next != null){
                if (tempExp.exp.getDay() != tempExp.next.exp.getDay()){
                    i = index+1;
                }
                else
                    tempExp = tempExp.next;
            }
            else{
                return null;
            }
        }
        if(i>index){
            return null;
        }
        else{
            tempExp.exp.setAccuracy(a);
            tempExp.exp.setCompleted(c);
            tempExp.exp.setTime(t);
            tempExp.exp.setDay(day);
            tempExp.exp.setSetup(s);
            return tempExp.exp;
        }
    }

    public void removeExp(int day, int index){
        try {
            if(index < 0){
                Exception e;
                throw e = new Exception();
            }
        }
        catch (Exception e){
            System.out.println("index 0 veya 0 dan buyuk olmalidir");
            System.exit(1);
        }
        NodeExp tempExp = new NodeExp(headExp.exp);
        tempExp = headExp;
        tempExp.next = headExp.next;
        if (headDay.dayExp.exp.getDay() == day && index==0 && headExp.next==null){
            headDay = null;
            headExp = null;
            System.out.println("borulari dosedim abi");
        }
        else {
            int i = 0;
            while (tempExp.exp.getDay() != day) {
                tempExp = tempExp.next;
            }
            for (i = 0; i < index - 1; i++) {
                if (tempExp.next != null) {
                    if (tempExp.exp.getDay() != tempExp.next.exp.getDay()) {
                        i = index + 1;
                    } else
                        tempExp = tempExp.next;
                } else {
                    i = index + 1;
                }
            }
            if (i <= index) {
                if (tempExp.next.next == null) {
                    tempExp.next = null;
                } else {
                    tempExp.next = tempExp.next.next;
                }
            }
        }
    }

    public ExpLinkedList listExp(int day){
        NodeExp tempExp = new NodeExp(headExp.exp);
        tempExp = headExp;
        tempExp.next = headExp.next;
        int i=0;
        if(tempExp.exp.getDay() != day){
            while(tempExp.exp.getDay() != day){
                tempExp = tempExp.next;
            }
        }
        boolean control = true;
        ExpLinkedList newList = new ExpLinkedList();

        while (control==true){
            if ( tempExp.exp.getCompleted() == true && tempExp.exp.getDay() == tempExp.next.exp.getDay()){
                newList.addExp(tempExp.exp);
                tempExp = tempExp.next;
            }
            else {
                if (tempExp.next != null)
                    tempExp = tempExp.next;
                else
                    control = false;
            }
        }
        return  newList;
    }

    public void removeDay(int day){
        NodeDay tempDay = new NodeDay();
        tempDay = headDay;
        tempDay.next = headDay.next;
        boolean control = true;
        boolean control2 = true;
        if (tempDay.dayExp.exp.getDay() == day){
            tempDay = tempDay.next;
            headDay = tempDay;
            headDay.next = tempDay.next;
            headExp = tempDay.dayExp;
            headExp.next = tempDay.dayExp.next;
        }
        else {
            while(control==true){
                if(tempDay.next.dayExp.exp.getDay() == day) {
                    control = false;
                }
                else{
                    if (tempDay.next.next!=null)
                        tempDay = tempDay.next;
                    else {
                        control = false;
                        control2 = false;
                    }
                }
            }
            if (control2==true){
                if(tempDay.next.next != null){
                    NodeExp tempExp = new NodeExp(tempDay.dayExp.exp);
                    tempExp = tempDay.dayExp;
                    tempExp.next = tempDay.dayExp.next;
                    while (tempExp.next.exp.getDay() != tempDay.next.dayExp.exp.getDay()){
                        tempExp = tempExp.next;
                    }
                    tempExp.next = tempDay.next.next.dayExp;
                    tempExp.next.next = tempDay.next.next.dayExp.next;
                    tempDay.next = tempDay.next.next;
                }
                else{
                    NodeExp tempExp = new NodeExp(tempDay.dayExp.exp);
                    tempExp = tempDay.dayExp;
                    tempExp.next = tempDay.dayExp.next;
                    while (tempExp.next.exp.getDay() != tempDay.next.dayExp.exp.getDay()){
                        tempExp = tempExp.next;
                    }
                    tempExp.next = null;
                    tempDay.next = null;
                }
            }
        }

    }

    public void orderDay(int day){
        ExpLinkedList newList = new ExpLinkedList();
        NodeExp tempExp = new NodeExp();
        tempExp = headExp;
        while (tempExp.next!=null && tempExp.exp.getDay() != day){
            tempExp = tempExp.next;
        }
        NodeExp tempHead = new NodeExp();
        tempHead = tempExp;
        tempHead.next = tempExp.next;
        int i = 0;
        boolean control = true;
        while (control==true){
            if(tempExp.next != null && tempExp.exp.getDay() == day){
                i++;
                tempExp = tempExp.next;
            }
            else
                control = false;
        }
        i++;
       // tempExp = tempHead;
        tempExp = new NodeExp(tempHead.exp);
        tempExp.exp = tempHead.exp;
        tempExp.next = tempHead.next;
        MachineLearningExp [] array = new MachineLearningExp[i];
        for (int j=0;j<i;j++){
            array[j] = new MachineLearningExp(tempExp.exp);
            tempExp = tempExp.next;
        }
        //tempExp = tempHead;
        tempExp = new NodeExp(tempHead.exp);
        tempExp.exp = tempHead.exp;
        tempExp.next = tempHead.next;
        int j = 0;
        boolean control2 = true;
        control = true;
        while(control==true){
            while (control2==true){
                if (j<i-1) {
                    if (array[j].getAccuracy() > array[j + 1].getAccuracy()) {
                        MachineLearningExp tempEx = new MachineLearningExp();
                        tempEx.setData(array[j]);
                        array[j] = array[j+1];
                        array[j+1] = tempEx;
                        j=0;
                        control2=false;
                    }
                    else{
                        j++;
                    }
                }
                else {
                    control = false;
                    control2 = false;
                }
            }
            control2 = true;
        }
        tempExp = tempHead;
        removeDay(tempExp.exp.getDay());
        for(int m = 0;m<i;m++){
            this.addExp(array[m]);
        }
    }

    public ExpLinkedList orderExperiments(){
        ExpLinkedList newList = new ExpLinkedList();
        NodeExp tempExp = new NodeExp();
        tempExp = headExp;
        int i = 0;
        while (tempExp.next!=null){
            tempExp = tempExp.next;
            i++;
        }
        i++;
        tempExp = headExp;
        MachineLearningExp [] array = new MachineLearningExp[i];
        for (int j=0;j<i;j++){
            array[j] = new MachineLearningExp(tempExp.exp);
            tempExp = tempExp.next;
        }
        tempExp = headExp;
        int j = 0;
        boolean control = true;
        boolean control2 = true;
        control = true;
        while(control==true){
            while (control2==true){
                if (j<i-1) {
                    if (array[j].getAccuracy() > array[j + 1].getAccuracy()) {
                        MachineLearningExp tempEx = new MachineLearningExp();
                        tempEx.setData(array[j]);
                        array[j] = array[j+1];
                        array[j+1] = tempEx;
                        j=0;
                        control2=false;
                    }
                    else{
                        j++;
                    }
                }
                else {
                    control = false;
                    control2 = false;
                }
            }
            control2 = true;
        }
        newList.headExp = new NodeExp();
        newList.headExp.exp = array[0];
        NodeExp tempHead = new NodeExp();
        tempHead = newList.headExp;
        for (int m=0;m<i;m++){
            newList.headExp.exp = array[m];
            newList.headExp.next = new NodeExp();
            newList.headExp = newList.headExp.next;
        }
        newList.headExp = tempHead;
        return newList;
    }

    public void print()
    {
        NodeDay temp = new NodeDay(headDay);
        temp = headDay;
        temp.next = headDay.next;
        while(temp!=null){
            System.out.println(temp.dayExp.exp);
            temp=temp.next;
        }
    }

    public void deneme(){
        NodeDay temp = new NodeDay();
        temp = headDay;
        temp.next = headDay.next;
        System.out.println(temp.dayExp.exp);
        System.out.println(temp.dayExp.next.exp);
        System.out.println(temp.dayExp.next.next.exp);
    }

    public void print2()
    {
        NodeExp temp = new NodeExp(headExp.exp);
        temp = headExp;
        temp.next = headExp.next;
        while(temp!=null){
            System.out.println(temp.exp);
            temp = temp.next;
        }
    }

}
