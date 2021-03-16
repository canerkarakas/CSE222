import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Driver{

    public static void test(){
        try (FileReader fileReader = new FileReader("src/input.txt/")) {
            Scanner scanner = new Scanner(fileReader);
            ListGraph listGraph = new ListGraph(scanner.nextInt(), true);
            listGraph.setNumOfEdges(scanner.nextInt());
            scanner.nextLine();
            listGraph.loadEdgesFromFile(scanner);
            System.out.println(listGraph);
            System.out.println(numOfPop(listGraph));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int numOfPop(ListGraph listGraph){
        Edge [] edges = new Edge[listGraph.getNumOfEdges()];
        int k=0;
        for (int i=0;i<listGraph.getNumVertices()+1;i++){
            Iterator<Edge> edgeIterator = listGraph.edgeIterator(i);
            if (edgeIterator!=null){
                for (int j=0;j<((LinkedList.LinkedListIterator)edgeIterator).size();j++){
                    edges[k] = edgeIterator.next();
                    k++;
                }
            }
        }
        LinkedList<Edge> listEdge = new LinkedList<Edge>();
        for (int i=0;i<edges.length;i++){
            listEdge.add(edges[i]);
            boolean control = true;
            int l=0;
            while (l<edges.length){
                if (edges[l].getFrom() == edges[i].getTo()){
                    Edge edge = new Edge(edges[i].getFrom(), edges[l].getTo());
                    listEdge.add(edge);
                }
                l++;
            }
        }
        int numOfPop = 0;
        for (int j=listGraph.getNumVertices();j>0;j--) {
            int counter = 0;
            Iterator<Edge> iter = listEdge.iterator();
            for (int i = 0; i < ((LinkedList.LinkedListIterator) iter).size(); i++) {
                Edge edge = iter.next();
                if (edge.getTo()==j && edge.getFrom()!=j)
                    counter++;
            }
            if (counter==listGraph.getNumVertices()-1)
                numOfPop++;
        }
        System.out.println(listEdge);
        return numOfPop;
    }
}
