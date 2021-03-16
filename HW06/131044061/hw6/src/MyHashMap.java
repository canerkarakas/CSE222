import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyHashMap {
    public static void main(String args[]){
        Driver driver = new Driver();
        driver.driver("./src/input.txt");
    }
    public static class Driver{
        public void driver(String inputFile){
            NLP test = new NLP();
            test.readDataset("./dataset/");
            System.out.println("----WMAP ITERATOR----");
            test.printWordMap();
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("---------------------");
            File file = new File(inputFile);
            try {
                String [] lines = read(file);
                String [][] lines2D = new String[lines.length][];
                for (int i=0;i<lines.length;i++)
                    lines2D[i] = lines[i].split(" ");
                for (int i=0;i<lines2D.length; i++)
                    for (int j=0;j<lines2D[i].length;j++) {
                        if (lines2D[i][j].equals("bigram")){
                            System.out.println(test.bigrams(lines2D[i][j+1]));
                            System.out.println();
                            j=lines2D.length;
                        }
                        else{
                            System.out.println(test.tfIDF(lines2D[i][j+1], lines2D[i][j+2]));
                            System.out.println();
                            j=lines2D.length;
                        }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public String[] read(File file)throws IOException {
            String []result = new String[(((new BufferedReader(new FileReader(file))).lines()).toArray()).length];
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line = null;
                int i=0;
                while ((line = br.readLine()) != null) {
                    result[i] = line;
                    i++;
                }
            }
            return result;
        }
    }
}