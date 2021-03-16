import java.sql.Time;
import java.util.Iterator;

public class Driver {

    public void test(){
        MachineLearningExp x11 = (new MachineLearningExp("Exp1",1,new Time(1,2,3),false,1));
        MachineLearningExp x22 = (new MachineLearningExp("Exp1",1,new Time(1,2,3),false,2));
        MachineLearningExp x33 = (new MachineLearningExp("Exp1",1,new Time(1,2,3),false,3));
        MachineLearningExp x = (new MachineLearningExp("Exp1",1,new Time(1,2,3),true,4));
        MachineLearningExp x2 = (new MachineLearningExp("Exp1",2,new Time(1,40,3),false,0));
        MachineLearningExp x3 = (new MachineLearningExp("Exp1",4,new Time(1,50,3),false,3));
        MachineLearningExp x4 = (new MachineLearningExp("Exp1",4,new Time(1,12,3),false,2));
        MachineLearningExp x5 = (new MachineLearningExp("Exp1",4,new Time(1,31,3),false,1));

        ExpLinkedList list = new ExpLinkedList();
        list.addExp(x11);
        list.addExp(x);
        list.addExp(x33);
        list.addExp(x3);
        list.addExp(x4);
        list.addExp(x2);
        list.addExp(x22);
        list.addExp(x5);

        System.out.println("Exps");
        list.print2();
        System.out.println("\n");
        System.out.println("Days");
        list.print();

        list.removeExp(4,2);
        System.out.println("\n");
        System.out.println("removeExp(4,2)");
        list.print2();

        list.removeDay(4);
        System.out.println("\n");
        System.out.println("removeDay(4)");
        list.print2();

        list.removeDay(1);
        System.out.println("\n");
        System.out.println("removeDay(1)");
        list.print2();

        list.addExp(x11);
        list.addExp(x);
        list.addExp(x33);
        list.addExp(x3);
        list.addExp(x4);
        list.addExp(x22);
        list.addExp(x5);
        System.out.println("\n");
        System.out.println("flashback");
        list.print2();

        System.out.println("\n");
        System.out.println("listExp(1)");
        ExpLinkedList tempList = new ExpLinkedList(list.listExp(1));
        tempList.print2();

        System.out.println("\n");
        System.out.println("orderDay(4)");
        list.orderDay(4);
        list.print2();

        System.out.println("\n");
        System.out.println("orderExperiments()");
        ExpLinkedList tempList2 = new ExpLinkedList();
        tempList2 = list.orderExperiments();
        tempList2.print2();

        System.out.println("\n");
        System.out.println("getExp(1,0)");
        System.out.println(list.getExp(1,0));

        System.out.println("\n");
        System.out.println("getExp(4,1)");
        System.out.println(list.getExp(4,1));

        System.out.println("\n");
        System.out.println("getExp(2,0)");
        System.out.println(list.getExp(2,0));

        System.out.println("\n");
        System.out.println("setExp(1,0)");
        System.out.println(list.setExp(1,0,"new",new Time(4,22,33),true,10));


        System.out.println("\n");
        System.out.println("setExp(2,0)");
        System.out.println(list.setExp(2,0,"new",new Time(5,12,2),true,1));

        System.out.println("\n");
        System.out.println("setExp(4,0)");
        System.out.println(list.setExp(4,0,"new",new Time(3,42,55),true,23));

        System.out.println("\n");
        System.out.println("setExp(1,2)");
        System.out.println(list.setExp(1,0,"new",new Time(7,55,3633),true,425));
        System.out.println("\n");

        System.out.println("The end");
        list.print2();

        System.out.println("\n\n");
        System.out.println("test iterator");
        Iterator<ExpLinkedList> iter = list.iterator();
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.next().getIter());
        System.out.println(iter.hasNext());
        // System.out.println(iter.next().getIter());
    }

}
