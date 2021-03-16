import java.util.Comparator;

public class CompEUC implements Comparator<HWColor> {

    @Override
    public int compare(HWColor hwColor1, HWColor hwColor2) {
        double vectorColor1 = Math.sqrt(Math.pow(hwColor1.getRed(),2) + Math.pow(hwColor1.getGreen(),2) + Math.pow(hwColor1.getBlue(),2));
        double vectorColor2 = Math.sqrt(Math.pow(hwColor2.getRed(),2) + Math.pow(hwColor2.getGreen(),2) + Math.pow(hwColor2.getBlue(),2));
        if (vectorColor1>vectorColor2)
            return 1;
        if (vectorColor2>vectorColor1)
            return -1;
        return 0;
    }
}