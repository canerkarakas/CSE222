import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NLP
{
    public Word_Map wmap;
    public File[] files;
    public String[][] words;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir)
    {
        File folder = new File(dir);
        files = new File[(int) folder.length()];
        files = folder.listFiles();
        String []filesContent = new String[files.length];
        words = new String[files.length][];
        try {
            for (int i=0;i<filesContent.length;i++)
                filesContent[i] = readContent(files[i]);
            for (int i=0;i<filesContent.length;i++)
                words[i] = filesContent[i].split(" ");
            filesContent = null;
            wmap = new Word_Map();
            for (int i=0;i<words.length;i++){
                for(int j=0;j<words[i].length;j++){
                    if (!wmap.containsKey(words[i][j])) {
                        File_Map tempMap = new File_Map();
                        ArrayList<Integer> occur = new ArrayList<>();
                        occur.add(j);
                        tempMap.put(files[i].getName(), occur);
                        wmap.put(words[i][j], tempMap);
                    }
                    else{
                        File_Map temp = (File_Map) wmap.get(words[i][j]);
                        if (!temp.containsKey(files[i].getName())){
                            ArrayList<Integer> occur = new ArrayList<>();
                            occur.add(j);
                            temp.put(files[i].getName(), occur);
                        }
                        else{
                            ArrayList<Integer> occur = new ArrayList<>();
                            occur = (ArrayList<Integer>) ((File_Map)wmap.get(words[i][j])).get(files[i].getName());
                            occur.add(j);
                            temp.put(files[i].getName(), occur);
                        }
                        wmap.put(words[i][j], temp);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readContent(File file) throws IOException
    {
        String result = new String();
        try(BufferedReader br  = new BufferedReader(new FileReader(file))){
            String strLine;
            while((strLine = br.readLine()) != null)
                result = result + strLine + "\n" + " ";
        }
        result = result.trim().replaceAll("\\p{Punct}", "");
        result = result.trim().replaceAll("\\n","");
        result = result.trim().replaceAll("            ", " ");
        result = result.trim().replaceAll("           ", " ");
        result = result.trim().replaceAll("          ", " ");
        result = result.trim().replaceAll("         ", " ");
        result = result.trim().replaceAll("        ", " ");
        result = result.trim().replaceAll("       ", " ");
        result = result.trim().replaceAll("      ", " ");
        result = result.trim().replaceAll("     ", " ");
        result = result.trim().replaceAll("    ", " ");
        result = result.trim().replaceAll("   ", " ");
        result = result.trim().replaceAll("  ", " ");
        return result;
    }

    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word)
    {
        if (wmap.containsKey(word)) {
            Collection<String> wordValues = new ArrayList<String>();
            wordValues = (ArrayList) ((File_Map) wmap.get(word)).values();
            Object[] wordValuesArr = wordValues.toArray();
            TreeSet<String> wordKeys = new TreeSet<>();
            wordKeys = (TreeSet<String>) ((File_Map) wmap.get(word)).keySet();
            Object[] wordKeysArr = wordKeys.toArray();
            int k = 0;
            int l = 0;
            List<String> result = new LinkedList<String>();
            for (int i = 0; i < words.length; i++) {
                if (wordKeysArr[k].equals(files[i].getName())) {
                    for (int j = 0; j < words[i].length; j++) {
                        if (words[i][j].equals(word))
                            result.add(word + " " + words[i][j+1]);
                    }
                    k++;
                    if (k == wordKeysArr.length)
                        i = words.length;
                }
            }
            return result;
        }
        return null;
    }

    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName)
    {
        if (wmap.containsKey(word)) {
            if (((File_Map)wmap.get(word)).containsKey(fileName)) {
                float totalNumOfDoc = files.length;
                float numOfDocWithTermWord = ((File_Map) wmap.get(word)).keySet().size();
                float IDF = (float) Math.log(totalNumOfDoc / numOfDocWithTermWord);
                float totalNumOfTermsDoc = 0;
                float numOfTimesTermWordAppDoc = ((ArrayList) (((File_Map) wmap.get(word)).get(fileName))).size();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().equals(fileName)) {
                        totalNumOfTermsDoc = words[i].length;
                        i = files.length;
                    }
                }
                float TF = numOfTimesTermWordAppDoc / totalNumOfTermsDoc;
                return (TF * IDF);
            }
        }
        return  -1f;
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        Iterator iter = wmap.iterator();
        while(iter.hasNext())
            System.out.println((iter.next()));
    }
}