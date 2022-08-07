# 50.003 - ESC Mini Campaign
Student Name: Xiang Siqi

Student ID: 1004875

### Problem Description

***

Consider a CSV file that stores a list of records (e.g., records of bank accounts). You are required to write a software program that reads two such CSV files, compares records stored in these CSV files row by row against a unique combination and records all mismatches as exceptions. Finally, the software program generates another csv file listing the exceptions.

### User Case Diagram

***

![use_case_diagram](use_case_diagram.png)

### How to Run the Program

***

##### compile Main.java script

`javac Main.java`

##### run with the sample files

`java Main`

You can also **define your own input files** by running `java fileA fileB fileO`. For example, running `java FileA.csv FileB.csv FileO.csv` will compare the difference between FileA.csv and FileB.csv and output the different records in FileO.csv.

* `fileA`: the file name of a csv file to compare.
* `fileB`: the file name of a csv file to compare.
* `fileO`: the file name of the output csv file.

If you want to **specify columns to be compared**, run `java fileA fileB fileO [cols]`. For example, `java fileA.csv fileB.csv flieO.csv "\"#ID\"" "\"Account No.\"" ` will only compare the difference of fileA.csv and fileB.csv in column \"#ID\" and \"Account No.\" and output the result in fileO.csv.

* `[cols]`: names of columns to compare.

### Testing Doc (week 9)

* Refer to [testing]()

  
