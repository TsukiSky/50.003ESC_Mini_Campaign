package group_test.divy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
    private static ArrayList<String> splitOutsideCommas(String str, boolean continuation) {
        ArrayList<String> split = new ArrayList<>();
        StringBuilder entry = new StringBuilder();
        long quoteCount = 0;

        if (continuation)
            ++quoteCount;

        for (char c : str.toCharArray()) {
            if (c == '"')
                ++quoteCount;
            if (c == ',') {
                if (quoteCount % 2 == 0) {
                    split.add(entry.toString());
                    entry.setLength(0);
                } else
                    entry.append(c);
            } else
                entry.append(c);
        }
        if (entry.length() != 0)
            split.add(entry.toString());
        return split;
    }

    private static boolean isContinuation(ArrayList<String> rec, boolean cont) {
        int len = rec.size()-1;
        String last = rec.get(len);
        last = last.replaceAll("\"\"", "");
        if ((last.startsWith("\"") || (cont && len == 0)) && (last.length() == 1 || !last.endsWith("\"")))
            return true;
        else
            return false;
    }

    public static ArrayList<ArrayList<String>> parse(String filepath) throws FileNotFoundException, IllegalArgumentException {
        Scanner sc = new Scanner(new File(filepath));
        sc.useDelimiter("(?=\r\n|\n|\r)");
        ArrayList<ArrayList<String>> csv = new ArrayList<>();
        int recordLen = -1;

        ArrayList<String> record = new ArrayList<>();
        boolean continuation = false;
        while (sc.hasNext()) {
            ArrayList<String> newRecords = splitOutsideCommas(sc.next(), record.size() != 0);
            boolean pContinuation = continuation;
            continuation = isContinuation(newRecords, continuation);

            for (int i = 0; i < newRecords.size(); ++i) {
                if(!pContinuation || i != 0) {
                    newRecords.set(i, newRecords.get(i).replaceAll("(^\")", ""));
                }
                if(!continuation || newRecords.size()-1 != i) {
                    newRecords.set(i, newRecords.get(i).replaceAll("(\"$)", ""));
                }

                if (newRecords.get(i).replaceAll("\"\"", "").contains("\"")) {
                    throw new IllegalArgumentException("The format of the CSV file '" + filepath + "' is invalid");
                }

                newRecords.set(i, newRecords.get(i).replaceAll("\"\"", "\""));
            }
            if (record.size() != 0) {
                if (newRecords.size() == 0)
                    throw new IllegalArgumentException("The format of the CSV file '" + filepath + "' is invalid");

                int i = record.size() - 1;
                record.set(i, record.get(i) + newRecords.remove(0));
            }

            record.addAll(newRecords);

            if (continuation)
                continue;

            if (sc.hasNext())
                sc.nextLine();

            if (recordLen == -1)
                recordLen = record.size();
            else if (recordLen != record.size())
                throw new IllegalArgumentException("The format of the CSV file '" + filepath + "' is invalid");

            csv.add(record);
            record = new ArrayList<>();
        }
        if (record.size() != 0) {
            if (record.size() != recordLen)
                throw new IllegalArgumentException("The format of the CSV file '" + filepath + "' is invalid");
            csv.add(record);
        }

        sc.close();
        return csv;
    }

    public static String save(ArrayList<ArrayList<String>> csv, String filename) throws IOException, IllegalArgumentException {
        try (FileWriter writer = new FileWriter(filename)) {
            long recordLen = -1;
            StringBuilder file = new StringBuilder();
            for (ArrayList<String> record : csv) {
                if (recordLen == -1)
                    recordLen = record.size();
                else if (recordLen != record.size())
                    throw new IllegalArgumentException("The format of the provided CSV is invalid");

                for (int i = 0; i < record.size(); ++i) {
                    record.set(i, record.get(i).replaceAll("\"", "\"\""));
                    record.set(i, "\"" + record.get(i) + "\"");
                }
                file.append(String.join(",", record));
                file.append("\n");
            }
            writer.append(file);
            return file.toString();
        } catch (IOException e) {
            throw new IOException("Cannot write to file: " + filename);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        parse("src\\test\\resources\\FuzzingTest\\valid0.csv");
    }
}