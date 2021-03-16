import java.util.Iterator;
import java.util.Scanner;

public abstract class GraphADT{

    protected boolean directed;
    protected int numVertices;

    public GraphADT(int numVertices, boolean directed){
        this.directed = directed;
        this.numVertices = numVertices;
    }

    public int getNumVertices(){return numVertices;}

    public boolean isDirected() { return directed; }

    public abstract void loadEdgesFromFile(Scanner scanner);

    public abstract void insert(Edge edge);

    public abstract boolean isEdge(int from, int to);

    public abstract Edge getEdge(int from, int to);

    public abstract Iterator<Edge> edgeIterator(int from);

}
