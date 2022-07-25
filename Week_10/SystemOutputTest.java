import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


@RunWith(Parameterized.class)

public class SystemOutputTest {
    public String fileA, fileB, actualDiff, testDiff;
    
    public SystemOutputTest(String fileA, String fileB, String actualDiff, String testDiff) {
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

    @Parameters public static Collection<Object[]> differentLength() {
        return Arrays.asList(new Object [][] {{
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestCaseDiffLengthA.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestCaseDiffLengthB.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestCaseDiffLengthActualResult.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestResult.csv"},
        {
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\Empty.csv",
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\Empty.csv",
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestCaseEmptyActualResult.csv",
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\Week_10\\TestCases\\TestResult.csv"}
        });
    }

    @Test public void CompareTesting() throws IOException {
        Compare.compare(this.fileA, this.fileB, this.testDiff);
        assertEquals(read(this.testDiff), read(this.actualDiff));
    }
}