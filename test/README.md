## Equivalence Testing:

### 1. Check if  two files have the same length

Test that two files have exactly the same length (i.e. the same number of records). If not, the program has errors.

**boundary value:**

One file has the same number of records as another one.

Both files have 0 records.

**middle value:**

One file has more/fewer records than another one.

### 2. Check if both files have no null values

Check that there are no null values in files. If null values are in the file, it means there are some errors in the code.

**boundary value:**

Both files have no null value.

One file has one null value.

**middle value:**

Both files have many null values.

### 3. Check if the `Customer ID` of the files is of the same range

Check that in both files, the `Customer ID` range the same value from `ID1` to `IDn`.

**boundary value:**

Files' `Customer ID` are ranging from the same range.

One file has one larger `Customer ID` than another.

**middle value:**

One file has a different range of `Customer ID` compared to another.

### 4. Check if data in files are of the same type

Check that in both files, the type of `Account No.`, `Currency`, `Type` is String and `Balance` is Integer.

**boundary value:**

Data in both files are of the correct type.

**middle value:**

One file has different types of variables.
Both files have different types of variables.