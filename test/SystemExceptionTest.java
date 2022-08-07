import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;


@RunWith(Parameterized.class)

public class SystemExceptionTest {
    public String fileA, fileB, fileO;
    private String[] chosenCol;
    
    public SystemExceptionTest(String fileA, String fileB, String fileO, String chosenCol) {
        this.fileA = fileA;
        this.fileB = fileB;
        this.fileO = fileO;
        this.chosenCol = new String[]{chosenCol};
    }

    @Parameters public static Collection<Object[]> columnException() {
        // String chosenCol = "\"Balance\"";
        return Arrays.asList(new Object [][] {{
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\test\\TestCases\\TestCaseExceptionA.csv", 
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\test\\TestCases\\TestCaseExceptionB.csv",
            "D:\\Term 5\\50.003 Elements of Software Construction\\TestingMiniCamp\\50.003ESC_Mini_Campaign\\test\\TestCases\\TestResult.csv",
            "\"Balance\""}
        });
    }

    @Test public void ExceptionTesting() {
        String result = Compare.compare(this.fileA, this.fileB, this.fileO, this.chosenCol);
        assertTrue(result.equals("Exception: Unexpected Column Name: \"Balance\""));
    }
}