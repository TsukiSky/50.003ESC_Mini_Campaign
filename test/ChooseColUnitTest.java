import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import javax.imageio.IIOException;


public class ChooseColUnitTest {
    public ChooseColUnitTest() {}

    @Test public void CompareTesting() throws IIOException {
        // Test Compare.chooseCol method
        ArrayList<String> chosenCol = new ArrayList<>();
        ArrayList<String> availableCol = new ArrayList<>();
        ArrayList<Integer> expectedResult = new ArrayList<>();

        // available Columns
        availableCol.add("Customer ID#");
        availableCol.add("Account No.");
        availableCol.add("Currency");
        availableCol.add("Type");
        availableCol.add("Balance");

        chosenCol.add("Type");
        expectedResult.add(availableCol.indexOf("Type"));
        assertEquals(expectedResult, Compare.chooseCol(chosenCol, availableCol));   // Choose the index of "Type" in availableCol

        chosenCol.add("Customer ID#");
        expectedResult.add(availableCol.indexOf("Customer ID#"));
        assertEquals(expectedResult, Compare.chooseCol(chosenCol, availableCol));   // index of "Type" and index of "Customer ID#"

        // MISUSE CASES
        Throwable e = null;
        chosenCol.add("Misuse");
        try {
            Compare.chooseCol(chosenCol, availableCol);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof IIOException);      // An IIOException is thrown
        assertEquals(e.getMessage(), "Exception: Unexpected Column Name: Misuse");     // an Exception message is sent
    }
}