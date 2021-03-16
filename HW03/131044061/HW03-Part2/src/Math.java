public class Math {

    private double number;
    public static final double PI = 3.141592653589793;

    public Math(){
        number = 0;
    }
    public Math(double n){
        setNumber(n);
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public static double sin(double degrees){
        double radians = toRadians(degrees);
        int k =0;
        double result = 0;
        boolean control = true;
        while(k<20){
            if (control) {
                result = result + (1 / fac(2*k+1)) * (pow(radians, 2*k+1));
            }
            else{
                result = result - (1 / fac(2*k+1)) * (pow(radians, 2*k+1));
            }
            k = k+1;
            control = !control;
        }
        return  result;
    }

    public static double cos(double degrees){

        double radians = toRadians(degrees);
        int n=0;
        double result = 0;
        boolean control = true;
        while(n<20){
            if (control){
                result = result + (1 / fac(2*n)) * (pow(radians, 2*n));
            }
            else{
                result = result - (1 / fac(2*n)) * (pow(radians, 2*n));
            }
            n = n + 1;
            control = !control;
        }
        return result;
    }

    public static double toRadians(double degrees){
        return (degrees * PI) / 180;
    }

    public static double abs(double i){
        if(i>=0)
            return i;
        return -1*i;
    }

    public static double fac(int n){
        double result = 1;
        for (int i=n;i>0;i--){
            result = result *i;
        }
        return result;
    }

    public static double pow(double n, int p){
        double result = 1;
        for (int i=0;i<p;i++){
            result = result * n;
        }
        return result;
    }



}
