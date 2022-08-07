import random
from Fuzzer import Fuzzer


if __name__ == "__main__":
    fuzzer = Fuzzer()
    fuzz_rate = 0.1
    print("Generating fuzzing files...")
    for i in range(1, 21):
        # generating 20 fuzzing files
        num_records = random.randint(50, 60)
        fuzzer.generate_fuzz_file(str(i), num_records=num_records, fuzz_rate=fuzz_rate, generate_contrast=True)
    print("###All fuzzing flies generated###")
