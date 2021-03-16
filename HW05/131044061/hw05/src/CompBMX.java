import java.util.Comparator;

public class CompBMX implements Comparator<HWColor> {
    @Override
    public int compare(HWColor hwColor1, HWColor hwColor2) {
        int [] firstColorRedArr = new int[8];
        int [] firstColorGreenArr = new int[8];
        int [] firstColorBlueArr = new int[8];
        int [] secondColorRedArr = new int[8];
        int [] secondColorGreenArr = new int[8];
        int [] secondColorBlueArr = new int[8];
        firstColorRedArr = toBinaryArray(hwColor1.getRed());
        firstColorGreenArr = toBinaryArray(hwColor1.getGreen());
        firstColorBlueArr = toBinaryArray(hwColor1.getBlue());
        secondColorRedArr = toBinaryArray(hwColor2.getRed());
        secondColorGreenArr = toBinaryArray(hwColor2.getGreen());
        secondColorBlueArr = toBinaryArray(hwColor2.getBlue());
        int [] firstColor = new int[24];
        int [] secondColor = new int[24];
        firstColor = to24Bit(firstColorRedArr, firstColorGreenArr, firstColorBlueArr);
        secondColor = to24Bit(secondColorRedArr, secondColorGreenArr, secondColorBlueArr);
        int firstColorDecimal = toDecimal(firstColor);
        int secondColorDecimal = toDecimal(secondColor);
        if(firstColorDecimal>secondColorDecimal)
            return 1;
        if (secondColorDecimal>firstColorDecimal)
            return -1;
        return 0;
    }

    public int[] toBinaryArray(int number){
        int [] result = new int[8];
        int index = 0;
        while (number>0){
            result[index] = number%2;
            number = number - result[index];
            number = number/2;
            index++;
        }
        return result;
    }

    public int[] to24Bit(int redArr[], int greenArr[], int blueArr[]){
        int [] result = new int[24];
        int index = 0;
        for (int i=0;i<8;i++){
            result[index] = redArr[i];
            index++;
            result[index] =  greenArr[i];
            index++;
            result[index] = blueArr[i];
            index++;
        }
        return result;
    }
    public int toDecimal(int binaryArr[]){
        int result = 0;
        int binaryPow = 0;
        for (int i=0;i<24;i++){
            result = result + binaryArr[i] * (int)Math.pow(2.0,binaryPow);
            binaryPow++;
        }
        return result;
    }
}
