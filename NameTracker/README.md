# Baby Name Tracker Project

## Project Overview
This project is a Java application that processes CSV files containing baby name data and generates statistics for specific names based on their occurrence over a range of years. The application supports tracking both male and female baby names from multiple CSV files and allows querying the rank and frequency of specific names across the years.

## Author
Çağan Durgun

## Compilation and Execution

### Compilation
To compile the project, use the following command:
```bash
javac Main.java
```

### Execution
To run the compiled program, you can use the following command:
```bash
java Main <arguments>
```
### Arguments:
- `-f <name>`: Add a female name to track.
- `-m <name>`: Add a male name to track.
- `<file>`: Provide a CSV file for processing. The CSV files must be named in the format `namesYYYY.csv`, where `YYYY` is the year (e.g., `names2020.csv`).

### Example:
```bash
java Main -f Alice -m Bob names2020.csv names2021.csv
```

## Input File Format
The input CSV files should follow this structure:

| Rank | Male Name | Male Count | Female Name | Female Count |
|------|-----------|------------|-------------|--------------|
| 1    | John      | 1200       | Mary        | 1300         |
| 2    | William   | 1150       | Patricia    | 1250         |

- **File Name Format**: The file name should be in the format `namesYYYY.csv`, where `YYYY` represents the year.
- **File Content**: Each row should contain the rank of the name, the male name, the male count, the female name, and the female count for that year.

## Features
- Track male and female baby names.
- Process multiple CSV files corresponding to different years.
- Generate statistics on rank, count, and frequency of names.
- Total frequency and rank calculations across all years.

## No Known Bugs or Limitations
The project currently has no known bugs or limitations.

---

Enjoy using the Baby Name Tracker project!
