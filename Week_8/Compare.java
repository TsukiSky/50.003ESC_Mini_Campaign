import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.IIOException;
import java.io.File;


public class Compare{
    public Compare(){};

    public static ArrayList<Integer> chooseCol(ArrayList<String> chosenCol, ArrayList<String> availCol) throws IIOException{
        // return the index of chosen columns
        String chosen;
        ArrayList<Integer> colIndex = new ArrayList<>();
        for (int i=0; i<chosenCol.size(); i++) {
            chosen = chosenCol.get(i);
            boolean exist = false;
            for (int j=0; j<availCol.size(); j++){
                if (availCol.get(j).equals(chosen)){
                    colIndex.add(availCol.indexOf(chosen));
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                throw new IIOException("Unexpected Column Name: " + chosen);
            }
        }
        return colIndex;
    }

    public static String compare(){
        return compare("sample_file_1.csv", "sample_file_3.csv", "test_file_comparing_1_and_3.csv");
    }

    public static String compare(String fileA, String fileB, String fileO){
        String[] comparingCol = {"\"Customer ID#\"", "\"Account No.\"", "\"Currency\"", "\"Type\"", "\"Balance\""}; // choose comparing columns
        return compare(fileA, fileB, fileO, comparingCol);
    }

    public static String compare(String fileA, String fileB, String fileO, String[] comparingCol){
        try{
            BufferedReader readerA = new BufferedReader(new FileReader(fileA));
            BufferedReader readerB = new BufferedReader(new FileReader(fileB));
            File fileOut = new File(fileO);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
            String lineA = null;
            String lineB = null;
            lineA = readerA.readLine();
            lineB = readerB.readLine();
            ArrayList<String> targetCol = new ArrayList<String>(Arrays.asList(comparingCol));
            ArrayList<Integer> chosenColA = chooseCol(targetCol, new ArrayList<String>(Arrays.asList(lineA.split(","))));
            ArrayList<Integer> chosenColB = chooseCol(targetCol, new ArrayList<String>(Arrays.asList(lineB.split(","))));

            while ((lineA = readerA.readLine()) != null & (lineB = readerB.readLine()) != null){
                String itemA[] = lineA.split(",");
                String itemB[] = lineB.split(",");
                // initialize comparing columns
                ArrayList<String> itemAComparing = new ArrayList<>();
                ArrayList<String> itemBComparing = new ArrayList<>();
                for (int i=0; i<chosenColA.size(); i++) {
                    itemAComparing.add(itemA[chosenColA.get(i)]);
                    itemBComparing.add(itemB[chosenColB.get(i)]);
                }
                boolean same = true;
                for (int i=0; i<itemAComparing.size(); i++){
                    if (!itemAComparing.get(i).equals(itemBComparing.get(i))){
                        same = false;
                        break;
                    }
                }
                if (!same){
                    writer.write(lineB);
                    writer.newLine();
                    writer.write(lineA);
                    writer.newLine();
                }
            }
            // if there are rows left in either fileA or fileB: add them into the mismatch
            if (lineA!=null) {
                writer.write(lineA);
                while ((lineA = readerA.readLine()) != null) {
                    writer.write(lineA);
                }
            }
            else if (lineB!=null) {
                writer.write(lineB);
                while ((lineB = readerB.readLine()) != null) {
                    writer.write(lineB);
                }
            }
            writer.close();
            readerA.close();
            readerB.close();
            return "Complete: mismatched information has been written to " + fileO;
        } catch (IIOException e){
            return e.getMessage();
        } catch (IOException e) {
            return "Invalid File";
        }
    }
}