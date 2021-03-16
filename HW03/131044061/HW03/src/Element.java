public class Element {
    private char value;
    private int row;
    private int column;
    private char id;

    public Element(){
        value = ' ';
        row = -1;
        column = -1;
        id = ' ';
    }

    public Element(Element other){
        setId(other.getId());
        setColumn(other.getColumn());
        setRow(other.getRow());
        setValue(other.getValue());
    }

    public Element(char v, int r, int c, char i){
        setValue(v);
        setRow(r);
        setColumn(c);
        setId(i);
    }

    public void setValue(char v){
        if (v == 48 || v == 49){ value = v; }
        else{ System.out.println("error setValue!!"); }
    }

    public char getValue() { return value; }

    public void setRow(int r){
        if (r>-1){ row = r; }
        else { System.out.println("error setRow!!"); }
    }

    public int getRow() { return row; }

    public void setColumn(int c){
        if (c>-1){ column = c; }
        else { System.out.println("error setColumn!!"); }
    }
    public int getColumn() { return column; }

    public void setId(char i) {id = i;}

    public char getId() {return id;}

    @Override
    public String toString() {
        String str = new String();
        str = str + "id= " + this.getId() + " row=" + this.getRow() + " column=" + this.getColumn();
        return str;
    }
}
