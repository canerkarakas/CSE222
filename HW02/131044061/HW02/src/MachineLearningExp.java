import javax.crypto.Mac;
import java.sql.Time;

public class MachineLearningExp {
    private String setup;
    private int day;
    private Time time;
    private  boolean completed;
    private float accuracy;

    public MachineLearningExp (){
        setup = "";
        day = 0;
        time = null;
        completed = false;
        accuracy = 0;
    }

    public MachineLearningExp (MachineLearningExp mach){
        setSetup(mach.getSetup());
        setDay(mach.getDay());
        setTime(mach.getTime());
        setCompleted(mach.getCompleted());
        setAccuracy(mach.getAccuracy());
    }

    public MachineLearningExp (String s, int d, Time t, boolean c, float a){
        setSetup(s);
        setDay(d);
        setTime(t);
        setCompleted(c);
        setAccuracy(a);
    }

    public String getSetup(){return setup;}

    public void setSetup(String s){
        setup = s;
    }

    public int getDay(){return day;}

    public void setDay(int d){
        day = d;
    }

    public Time getTime(){return time;}

    public void setTime(Time t){
        time = t;
    }

    public boolean getCompleted(){return completed;}

    public void setCompleted(boolean c){
        completed = c;
    }

    public float getAccuracy(){return accuracy;}

    public void setAccuracy(float a){
        accuracy = a;
    }

    public void setData(MachineLearningExp other){
        this.setup = other.getSetup();
        this.accuracy = other.getAccuracy();
        this.day = other.getDay();
        this.time = other.getTime();
        this.completed = other.getCompleted();
    }

    @Override
    public String toString() {
        return "setup : "+setup+" "+"day : "+day+" "+"time : "+time+
                " "+"completed : "+completed+" "+"accuracy : "+accuracy+"\n";
    }
}
