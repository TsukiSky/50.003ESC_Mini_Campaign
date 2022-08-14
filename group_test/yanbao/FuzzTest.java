package group_test.yanbao;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)


public class FuzzTest {
    public String fileA, fileB, actualDiff, testDiff;

    public FuzzTest(String fileA, String fileB, String actualDiff, String testDiff) {
        this.fileA = fileA;
        this.fileB = fileB;
        this.actualDiff = actualDiff;
        this.testDiff = testDiff;
    }

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

    @Parameters public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object [][] {
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_1.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_1.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_1.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_2.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_2.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_2.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_3.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_3.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_3.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_4.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_4.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_4.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_5.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_5.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_5.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_6.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_6.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_6.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"},
            {
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\contrast_7.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\fuzzing_7.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\output_7.csv", 
                "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\group_test\\fuzz_files\\result.csv"}
        });
    }
    
    @Test public void Testing() throws IOException {
        CSVReader reader = new CSVReader(this.fileA, this.fileB, this.testDiff);
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3,4".getBytes());
        System.setIn(in);
        reader.compare();
        assertEquals(read(this.testDiff), read(this.actualDiff));
    }
}