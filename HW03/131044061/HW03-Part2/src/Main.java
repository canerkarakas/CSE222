import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Calculator Test");
        System.out.println("------------");
        test();
    }

    public static void test(){
        File f = new File("src\\test2.txt");
        try(FileInputStream input = new FileInputStream(f)) {
            int size = (int) f.length();
            int sizeOfStacksArr = 0;
            int count;
            boolean control2 = true;
            for (int i=0;i<size;i++){
                count = input.read();
                if (count=='\n' && control2 == true)
                    sizeOfStacksArr++;
            }
            input.close();
            Stack<Character> temp = new Stack<Character>();
            Stack<Character> [] stacks = new Stack[sizeOfStacksArr];
            int i = 0;
            int j = 0;
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String str = new String();
            boolean control = true;
            boolean control3 = true;
            Stack<Character> tempStack = new Stack<Character>();
            while (control == true){
                str = reader.readLine();
                if (str==null) {
                    control = false;
                }
                else{
                    if (!str.isEmpty()){
                        j=0;
                        stacks[i] = new Stack<Character>();
                        tempStack = new Stack<Character>();
                        control3 = true;
                        while (control3==true){
                            if (str.length() != j){
                                if (str.charAt(j) != ' ') {
                                    tempStack.push((Character) str.charAt(j));
                                }
                            }
                            else
                                control3 = false;
                            j++;
                        }
                        while (tempStack.getElementNumber()>1){
                            stacks[i].push(tempStack.pop());
                        }
                        stacks[i].push(tempStack.pop());
                        i++;
                    }
                }
            }
            i=0;
            while (i<sizeOfStacksArr){
                System.out.println(stacks[i]);
                i++;
            }
            System.out.println();
            Calculator calc = new Calculator();
            calc.calculator(stacks,sizeOfStacksArr);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
