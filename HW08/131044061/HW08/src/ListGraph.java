import java.util.Iterator;
import java.util.Scanner;

public class ListGraph extends GraphADT{

    private LinkedList [] addj;
    private int numOfEdges;
    private int calcNumOfEdges;

    public ListGraph(int numVertices, boolean directed) {
        super(numVertices, directed);
        addj = new LinkedList[this.numVertices+1];
        for (int i=0;i<this.numVertices+1;i++)
            addj[i] = new LinkedList();
    }

    @Override
    public void loadEdgesFromFile(Scanner scanner) {
        while (scanner.hasNext()) {
            Edge edge = new Edge(scanner.nextInt(), scanner.nextInt());
            calcNumOfEdges++;
            this.insert(edge);
        }
    }


    @Override
    public void insert(Edge edge) {
        addj[(int) edge.getFrom()].add(edge);
    }

    @Override
    public boolean isEdge(int from, int to) {
        return (addj[from].contains(new Edge(from, to)));
    }

    @Override
    public Edge getEdge(int from, int to) {
        Edge target = new Edge(from, to);
        for (Object edge : addj[from]){
            if (edge.equals(target))
                return (Edge) edge;
        }
        return target;
    }

    public int getNumOfEdges() { return numOfEdges; }

    public int getCalcNumOfEdges() { return calcNumOfEdges; }

    public void setNumOfEdges(int numOfEdges) { this.numOfEdges = numOfEdges; }

    @Override
    public Iterator<Edge> edgeIterator(int current) {
        if (addj[current].size()!=0)
            return addj[current].iterator();
        return null;
    }

    @Override
    public String toString() {
        String string = new String();
        int control = 0;
        while (control<numVertices+1){
            if (addj[control].size()!=0)
                string += addj[control].toString();
            control++;
        }
        return string;
    }
}
