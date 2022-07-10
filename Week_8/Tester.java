import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class Tester{

    public static void test(){
        test("sample_file_1.csv", "sample_file_3.csv", "test_file_comparing_1_and_3.csv");
    }

    public static void test(String fileA, String fileB, String fileO){
        try{
            BufferedReader readerA = new BufferedReader(new FileReader(fileA));
            BufferedReader readerB = new BufferedReader(new FileReader(fileB));
            File fileOut = new File(fileO);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));

            String lineA = null;
            String lineB = null;
            lineA = readerA.readLine();
            lineB = readerB.readLine();
            
            while ((lineA = readerA.readLine()) != null && (lineB = readerB.readLine()) != null){
                String itemA[] = lineA.split(",");
                String itemB[] = lineB.split(",");
                boolean same = true;

                if (itemA.length != itemB.length){
                    writer.write(lineB);
                    writer.newLine();
                    writer.write(lineA);
                    writer.newLine();
                    continue;
                }
                else {
                    for (int i=0; i<itemA.length; i++){
                        if (!itemA[i].equals(itemB[i])){
                            same = false;
                            break;
                        }
                    }
                }

                if (!same){
                    writer.write(lineB);
                    writer.newLine();
                    writer.write(lineA);
                    writer.newLine();
                }
            }

            writer.close();
            readerA.close();
            readerB.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
