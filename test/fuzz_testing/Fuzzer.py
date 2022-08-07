import random
import pandas as pd


class Fuzzer:
    def __init__(self):
        self._available_columns = ["Customer ID#", "Account No.", "Currency", "Type", "Balance"]
        self._currencies = ["CAD", "USD", "CHF", "EUR", "KYD", "GBP", "JOD", "OMR", "BHD", "KWD", "SGD"]
        self._record_types = ["SAVINGS", "CURRENT"]

    # def _choose_fuzz_cols(self):
    #     """
    #     Fuzz available columns in the data file
    #     :return: fuzzing columns
    #     """
    #     num_columns = len(self._available_columns)
    #     fuzz_columns = random.sample(self._available_columns, random.randint(0, num_columns))
    #     return fuzz_columns

    def _create_fuzz_data(self, num_records, fuzz_rate, columns):
        """
        Fuzz & Generate values for the data file
        :param num_records: number of rows in the file
        :param fuzz_rate: the probability of generating fuzzing data
        :param columns: provided columns
        :return: a dataframe containing fuzzed data
        """
        fuzz_data = pd.DataFrame(columns=columns)
        for record in range(1, num_records+1):
            current_record = {}
            for col in columns:
                if col == "Customer ID#":
                    # fuzz ID
                    fuzz = True if random.random() < fuzz_rate else False
                    if fuzz:
                        record_id = "ID" + str(random.randint(1, num_records))
                    else:
                        record_id = "ID" + str(record)
                    current_record[col] = record_id

                elif col == "Account No.":
                    # fuzz Account Number
                    fuzz = True if random.random() < fuzz_rate else False
                    if fuzz:
                        record_account = "BOS" + str(random.randint(10000, 999999))
                    else:
                        record_account = "BOS" + str(record)
                    current_record[col] = record_account

                elif col == "Currency":
                    # fuzz currency
                    fuzz = True if random.random() < fuzz_rate else False
                    if fuzz:
                        currency = random.choice(self._currencies)
                    else:
                        currency = self._currencies[record % len(self._currencies)]
                    current_record[col] = currency

                elif col == "Type":
                    # fuzz type
                    fuzz = True if random.random() < fuzz_rate else False
                    if fuzz:
                        record_type = self._record_types[(record + 1) % 2]
                    else:
                        record_type = self._record_types[record % 2]
                    current_record[col] = record_type

                elif col == "Balance":
                    # fuzz balance
                    fuzz = True if random.random() < fuzz_rate else False
                    if fuzz:
                        balance = random.randint(0, 10000)
                    else:
                        balance = record * 100
                    current_record[col] = balance
            fuzz_data = fuzz_data.append(current_record, ignore_index=True)
        return fuzz_data

    def _create_contrast_data(self, num_records, columns):
        """
        Create the file without fuzzing to be compared with the fuzz file
        :param num_records: number of rows in the file
        :param columns: provided columns
        :return: a dataframe containing data without fuzzing
        """
        contrast_data = pd.DataFrame(columns=columns)
        for record in range(1, num_records+1):
            current_record = {}
            for col in columns:
                if col == "Customer ID#":
                    current_record[col] = "ID" + str(record)
                elif col == "Account No.":
                    current_record[col] = "BOS" + str(record)
                elif col == "Currency":
                    current_record[col] = self._currencies[record % len(self._currencies)]
                elif col == "Type":
                    current_record[col] = self._record_types[record % 2]
                elif col == "Balance":
                    current_record[col] = record * 100
            contrast_data = contrast_data.append(current_record, ignore_index=True)
        return contrast_data

    def generate_fuzz_file(self, file_name, num_records, fuzz_rate, generate_contrast=False):
        """
        Generate a fuzzing test file
        :param file_name: fuzzing csv file name
        :param num_records: number of records in the file
        :param fuzz_rate: fuzzing rate
        :param generate_contrast: Generate a contrast file comparing to the fuzz file if set to True
        :return: null
        """
        # columns = self._choose_fuzz_cols()
        columns = self._available_columns

        file = self._create_fuzz_data(num_records, fuzz_rate, columns)
        file.to_csv("fuzzing_{}.csv".format(file_name), index=False)
        print("Fuzz testing file \"fuzzing_{}\" generated".format(file_name))

        if generate_contrast:
            contrast_file = self._create_contrast_data(num_records, columns)
            contrast_file.to_csv("contrast_{}.csv".format(file_name), index=False)
            print("Contrast file \"contrast_{}\" generated".format(file_name))
