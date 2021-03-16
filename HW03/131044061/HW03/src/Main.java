import javax.lang.model.util.Elements;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File f = new File("src\\test1.txt");
        try (FileInputStream input = new FileInputStream(f)) {
            int size = (int) f.length();
            int row = 0;
            int column = 0;
            boolean control = true;
            int count;
            for (int i=0;i<size;i++){
                count = input.read();
                if ((count == 49 || count == 48) && control == true)
                    column++;
                else{
                    if ((char)count == '\n') {
                        row++;
                        control = false;
                    }
                }
            }
            input.close();
            row++;
            FileInputStream input2 = new FileInputStream(f);
            char [][] arr = new char[row][column];
            int r = 0;
            int c = 0;
            int sizeOfStack = 0;
            for (int i=0;i<size;i++){
                count = input2.read();
                if (count == 49 || count == 48){
                    arr[r][c] = (char)count;
                    if(count == 49)
                        sizeOfStack++;
                    c++;
                }
                else{
                    if ((char)count == '\n') {
                        r++;
                        c=0;
                    }
                }
            }
            input2.close();
            Element [][] elements = new Element[row][column];
            for (int i=0;i<row;i++){
                for (int j=0;j<column;j++){
                    elements[i][j] = new Element(arr[i][j],i,j,arr[i][j]);
                    elements[i][j].setId(arr[i][j]);
                    elements[i][j].setValue(arr[i][j]);
                }
            }
            StackArray testStack = new StackArray();
            System.out.println("Stack Test");
            System.out.println("----------");
            testStack.test();
            System.out.println();
            System.out.println("White Comp Test");
            System.out.println("---------------");
            testStack.test2(elements,row,column,sizeOfStack);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}