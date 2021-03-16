import java.util.Comparator;

public class CompLEX implements Comparator<HWColor> {
    @Override
    public int compare(HWColor hwColor1, HWColor hwColor2) {
        if(hwColor1.getRed()==hwColor2.getRed()){
            if (hwColor1.getGreen()==hwColor2.getGreen()){
                if (hwColor1.getBlue()==hwColor2.getBlue())
                    return 0;
                else if (hwColor1.getBlue()>hwColor2.getBlue())
                    return 1;
                else
                    return -1;
            }
            else if (hwColor1.getGreen()>hwColor2.getGreen())
                return 1;
            else
                return -1;
        }
        else if (hwColor1.getRed()>hwColor2.getRed())
            return 1;
        else
            return -1;
    }
}
