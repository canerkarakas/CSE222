public class Edge{
    private int from;
    private int to;
    public Edge(){
        from = 0;
        to = 0;
    }
    public Edge(int from, int to){
        this.from = from;
        this.to = to;
    }
    public void setFrom(int from) { this.from = from; }
    public void setTo(int to) { this.to = to; }
    public int getFrom() { return from; }
    public int getTo() { return to; }

    @Override
    public boolean equals(Object obj) {
        return (((Edge)obj).getFrom() == this.getFrom() && ((Edge)obj).getTo() == this.getTo());
    }

    @Override
    public String toString() {
        String string = new String();
        string += from + " " + to;
        return string;
    }
}
