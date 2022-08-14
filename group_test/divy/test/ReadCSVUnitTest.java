package group_test.divy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import org.junit.Test;
import group_test.divy.ReadCSV;


public class ReadCSVUnitTest {
    @Test public void nonexistingFile () {
        Throwable e = null;
        try {
            ReadCSV.parse("nonexisting.csv");
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof FileNotFoundException);      // A FileNotFoundException is thrown
    }

    @Test public void invalidPara() {
        Throwable e = null;
        try {
            ReadCSV.parse("D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv");
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);      // An IllegalArgumentException is thrown
        assertEquals(e.getMessage(), "The format of the CSV file 'D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv' is invalid");     // an Exception message is sent   
    }
}
