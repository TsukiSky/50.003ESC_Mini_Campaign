# Week 13 Group Members Testing Report

## Requirements

You will test the implementation of your project group members (at least three different implementations excluding yours). A group member X will share with another group member only the code implementing the problem statement and no test code written by X would be shared. Your objective is to use any testing techniques (Blackbox, Whitebox, fault-based, fuzzing) to discover bugs in your group members’ implementations.

## Implementations

### Test 1. Yanbao

The repo can be found [here](https://github.com/wangyanbao666/ESC-software-testing-campaign).

The test code can be found [here](https://github.com/TsukiSky/50.003ESC_Mini_Campaign/tree/main/group_test/yanbao).

Whitebox and fuzzing testing are carried out in testing this implementation. One bug related to invalid input detection is found:

**bug1:** The `CSVReader.java` does not recognize column names without quote signs, causing an error when judging whether there is a column named `"\"Balance\""` in the csv file. For example, if the balance column in the csv file is structured as `"Balance"` instead of `"\"Balance\""`, the CSVreader will throw an error `"Error: there is no column 'Balance' in the files, can not compare"`. This bug was found by fuzz testing.

**bug1.5**: The functionality of the `CSVReader` is bounded by Balance, which means it can only compare csv files with `"\"Balance\""` column and cannot be popularized to all kinds of data files. This is not a "bug" but more like a problem on the universality of functionalities.

### Test 2. Divy

The repo can be found [here](https://github.com/Divy1211/software-testing-mini-camp).

The test code can be found [here](https://github.com/TsukiSky/50.003ESC_Mini_Campaign/tree/main/group_test/divy/test).

Fuzzing testing, unit testing, and system testing are carried out in testing this implementation. One bug is found by the system test on `Compare.java`:

**bug1:** There is no mechanism handling null value in input csv files in `Comapare.java`. For example, if one record in the csv file is "null, BOS1, null, null, 300", it will neither be compared nor raise an error message.

The fuzz testing shows "not pass" on this implementation, but it passes all fuzzing test cases in fact. Showing "not pass" is because the output sequence of this implementation is different from the sequence of the standard fuzz test result. (standard: "112233", this implementation: "123123")

### Test 3. Ishan

The repo can be found [here](https://github.com/imk8/50.003-Software-Testing-Mini-Campaign).

The test code can be found [here](https://github.com/TsukiSky/50.003ESC_Mini_Campaign/tree/main/group_test/ishan/test).

Fuzzing testing and system testing are carried out in testing this implementation. Two bugs are found in `Comapre.java`:

**bug1:** The implementation does not handle unmatched column names and will eventually result in a problematic result in cases containing unmatched column names. For example when comparing csv file A with columns `"column1", "column2", "column3"` and csv file B with columns `"column1, column2, column4"`, the program will comparing `column3` of A against `column4` of B, which is not valid.

**bug2:** The implementation does not handle different column numbers. For example when comparing csv file A with columns `"column1", "column2", "column3"` and csv file B with columns `"column1", "column2"`, the program will compare `"column3"` of A against null instead of throwing an error.

The fuzz testing shows "not pass" on this implementation, but it passes all fuzzing test cases in fact. Showing "not pass" is because the output format of this implementation is different from the standard fuzz test result. (standard: "112233", this implementation: "fileA123fileB123")