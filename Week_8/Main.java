import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        String result;
        if (args.length == 0) {
            // run with test files
            result = Compare.compare();
        } 
        else if (args.length == 3) {
            String fileA = args[0];
            String fileB = args[1];
            String fileO = args[2];
            result = Compare.compare(fileA, fileB, fileO);
        }
        else if (args.length >= 4) {
            String fileA = args[0];
            String fileB = args[1];
            String fileO = args[2];
            String[] cols = Arrays.copyOfRange(args, 3, args.length);
            result = Compare.compare(fileA, fileB, fileO, cols);
        }
        else {
            result = "Invalid Input: The input format is not valid.";
        }
        System.out.println(result);
    }
}
