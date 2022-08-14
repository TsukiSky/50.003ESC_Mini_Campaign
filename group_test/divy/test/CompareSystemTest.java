package group_test.divy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import group_test.divy.Compare;


public class CompareSystemTest {
    @Test public void invalidCol () {
        ByteArrayInputStream in = new ByteArrayInputStream("1,2,3".getBytes());
        System.setIn(in);
        Compare.run(new String[]{"D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv", 
        "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv", 
        "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\result.csv"});
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("", outContent.toString());
    }

    @Test public void invalidFormat () {
        Throwable e = null;
        // invalid format
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("column1,column2,column3".getBytes());
            System.setIn(in);
            Compare.run(new String[]{"D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\invalidFormat.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\divy\\data\\result.csv"});
        } catch (Throwable ex) {
            e = ex;
        }
        // some mechanism is handling the invalid format exception
        assertTrue(e!=null);
    }
}