public class HWColor {
    private int red;
    private int blue;
    private int green;
    public HWColor(){
        red=0;blue=0;green=0;
    }
    public HWColor(int red, int green, int blue){
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public void setRed(int red) { this.red = red; }

    public void setBlue(int blue) { this.blue = blue; }

    public void setGreen(int green) { this.green = green; }

    public int getBlue() { return blue; }

    public int getGreen() { return green; }

    public int getRed() { return red; }

    @Override
    public String toString() {
        String temp = new String();
        temp = temp + "[" + red + ", " + green + ", " + blue + "]";
        return temp;
    }
}