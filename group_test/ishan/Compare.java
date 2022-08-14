package group_test.ishan;
//Blank Entries and IDs

import java.io. * ;
import java.util. *;

public class Compare {
    public static void main(String[] args) throws IOException {
        
        // Initial Checks on file input
        boolean prelim_check = true;
        prelim_check = checker(args);
        if (!prelim_check){
            System.out.println("Invalid inputs, please type 2 csv filenames seperated by a space ");
            throw new IllegalArgumentException();
        }

        //Creating respective variables
        String fileName= args[0]; //"sample_file_1.csv"; //Example FILE 1
        String fileName2= args[1]; //"sample_file_2.csv"; //Example FILE 2

        File file1= new File(fileName);
        File file2= new File(fileName2);

        List<String[]> file1_Array=new ArrayList<>();
        List<String[]> file2_Array=new ArrayList<>();
        List<String[]> file1a_Array=new ArrayList<>(); //Copy of Array 1
        List<String[]> file2a_Array=new ArrayList<>(); //Copy of Array 2

        //Converting into array list
        Scanner inputStream = file_convertor(file1);
        while(inputStream.hasNext()){
            String[] row = inputStream.nextLine().split(",");
            file1_Array.add(row);
            file1a_Array.add(row);
        }

        Scanner inputStream2 = file_convertor(file2);
        while(inputStream2.hasNext()){
            String[] row = inputStream2.nextLine().split(",");
            file2_Array.add(row);
            file2a_Array.add(row);
        }

        // Comparing 2 files to get similarities and removing them from copied array leaving behind exceptions
        for (int i = 0; i<file1_Array.size(); i++) {
            for (int i2 = 0; i2<file2_Array.size(); i2++) {
                if (Arrays.toString(file1_Array.get(i)).equals(Arrays.toString(file2_Array.get(i2)))){
                    file1a_Array.remove(file1_Array.get(i));
                    file2a_Array.remove(file2_Array.get(i2));
                }
            }
        }

        // Converting the copied arrays that contain the leftover rows to a csv file
        BufferedWriter br = new BufferedWriter(new FileWriter("ans_file.csv"));

        br.write(("First File Exceptions: \n"));
        for (int k = 0; k < file1a_Array.size(); k++){
            String str = String.join(",", file1a_Array.get(k));
            br.write((str));
            br.write("\n");
        }
        br.write(("Second File Exceptions: \n"));
        for (int k2 = 0; k2 < file2a_Array.size(); k2++){
            String str = String.join(",", file2a_Array.get(k2));
            br.write((str));
            br.write("\n");
        }
        br.close();
    }
    public static Boolean checker(String[] args){
        Boolean csv_check = true;
        for (String s: args){
            if(!s.endsWith(".csv")){
                System.out.println("Invalid file types");
                return false;
            }
        }

        Boolean length_check = false;
        if (args.length == 2){
            length_check = true;
        }
        else if (args.length == 0){
            System.out.println("No files input");
        }
        else{
            System.out.println("Invalid no. of files ");
        }

        if (length_check && csv_check){
            return true;
        }
        return false;
    }
    
    public static Scanner file_convertor(File file_input) throws FileNotFoundException {
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(file_input);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return inputStream;
    }
}