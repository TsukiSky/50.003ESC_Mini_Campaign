package group_test.divy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Compare {

    public static ArrayList<ArrayList<String>> csv1, csv2;
    public static final ArrayList<Integer> uniqueIndices1 = new ArrayList<>(), uniqueIndices2 = new ArrayList<>();
    public static final ArrayList<Integer> compIndices1 = new ArrayList<>(), compIndices2 = new ArrayList<>();
    public static final ArrayList<ArrayList<String>> mismatches = new ArrayList<>();

    private static boolean isMismatch(ArrayList<String> rec1, ArrayList<String> rec2) {
        for (int i = 0; i < compIndices1.size(); ++i) {
            if (!rec1.get(compIndices1.get(i)).equals(rec2.get(compIndices2.get(i))))
                return true;
        }
        return false;
    }

    private static boolean areCorresponding(ArrayList<String> rec1, ArrayList<String> rec2) {
        for (int i = 0; i < uniqueIndices1.size(); ++i) {
            if (!rec1.get(uniqueIndices1.get(i)).equals(rec2.get(uniqueIndices2.get(i))))
                return false;
        }
        return true;
    }

    private static void addTo(ArrayList<Integer> list1, ArrayList<Integer> list2, int i, String col) throws IllegalArgumentException {
        list1.add(i);
        int index = csv2.get(0).indexOf(col);
        if (index == -1)
            throw new IllegalArgumentException("Column '" + col + "' was not found in csv2");
        list2.add(index);
    }

    public static void findMismatches(ArrayList<String> colNames) throws IllegalArgumentException {
        mismatches.clear();
        uniqueIndices1.clear();
        uniqueIndices2.clear();
        compIndices1.clear();
        compIndices2.clear();

        if (csv1.size() == 0) {
            if (csv2.size() == 0)
                return;
            throw new IllegalArgumentException("There are an unequal number of columns in csv1 and csv2");
        } else if (csv2.size() == 0)
            throw new IllegalArgumentException("There are an unequal number of columns in csv1 and csv2");

        if (csv1.get(0).size() != csv2.get(0).size())
            throw new IllegalArgumentException("There are an unequal number of columns in csv1 and csv2");

        for (int i = 0; i < csv1.get(0).size(); ++i) {
            String col = csv1.get(0).get(i);
            if (colNames.contains(col)) {
                colNames.remove(col);
                addTo(uniqueIndices1, uniqueIndices2, i, col);
            } else
                addTo(compIndices1, compIndices2, i, col);
        }

        if (colNames.size() > 0) {
            throw new IllegalArgumentException("The following columns were not found in csv1: " + colNames.toString().replaceAll("(^\\[|]$)", ""));
        }

        for (ArrayList<String> record1 : csv1) {
            int matches = 0;
            for (ArrayList<String> record2 : csv2) {
                if (!areCorresponding(record1, record2))
                    continue;
                ++matches;

                if (isMismatch(record1, record2))
                    mismatches.add(record1);
            }
            if (matches == 0)
                mismatches.add(record1);
        }

        for (ArrayList<String> record2 : csv2) {
            int matches = 0;
            for (ArrayList<String> record1 : csv1) {
                if (!areCorresponding(record1, record2))
                    continue;
                ++matches;

                if (isMismatch(record1, record2))
                    mismatches.add(record2);
            }
            if (matches == 0)
                mismatches.add(record2);
        }
    }

    /**
     * @param args path1 path2 out_name
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.printf("Expected 3 arguments, %d provided\n", args.length);
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the column names that you wish to use as a unique combination separated by commas (eg: A,B,C)");
        ArrayList<String> columnNames = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));

        try {
            csv1 = ReadCSV.parse(args[0]);
            csv2 = ReadCSV.parse(args[1]);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            findMismatches(columnNames);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            ReadCSV.save(mismatches, args[2]);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void run(String[] args) {
        main(args);
    }
}