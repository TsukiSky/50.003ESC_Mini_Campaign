package group_test.ishan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import group_test.ishan.Compare;


public class CompareSystemTest {
    private ArrayList<String> read(String fileIn) throws IOException {
        // Read a csv file to an ArrayList
        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        String line = null;
        ArrayList<String> result = new ArrayList<>();
        while ((line = reader.readLine()) != null){
            result.add(line);
        }
        reader.close();
        return result;
    }
    
    @Test public void invalidCol () throws IOException {
        Throwable e = null;
        try {
            Compare.main(new String[]{"D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\invalidColA.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\invalidColB.csv"});
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue("some mechanism is handling the invalid column names", e!=null);
    }

    @Test public void nonexistingFile () {
        Throwable e = null;
        try {
            Compare.main(new String[]{"nonexisting.csv", "nonexisting.csv"});
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue("A FileNotFoundException is thrown", e instanceof FileNotFoundException);      // A FileNotFoundException is thrown
    }

    @Test public void invalidFormat() throws IOException{
        Compare.main(new String[]{"D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\invalidFormat.csv", 
        "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\validFormat.csv"});
        assertEquals(read("ans_file.csv"), read("D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\validInvalidOutput.csv"));
    }

    @Test public void invalidLength() throws IOException{
        Throwable e = null;
        try {
            Compare.main(new String[]{"D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\longFile.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\ishan\\data\\shortFile.csv"});
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue("some mechanism is handling the invalid column names", e!=null);
    }
}