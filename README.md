# Project Title: System Log Analyzer & Pattern Detector

## Overview of the project
Server logs can get massive and impossible to read manually. I built this tool to automate the process of finding errors and tracking down suspicious IPs. It reads standard log files line-by-line (to save memory) and lets you filter the data on the fly or generate a quick summary report of the most frequent system issues.

## Features
- **Zero Dependencies:** Written purely in standard Java. No external libraries required.
- **Memory Efficient:** Uses `BufferedReader` to process logs streamingly—it won't crash on multi-gigabyte log files.
- **Pattern Detection:** Aggregates data to automatically find the Top 5 most frequent errors and active IP addresses.
- **Resilient Parsing:** Gracefully skips empty or malformed lines without crashing.
- **Dynamic Filtering:** Filter logs by severity (e.g., ERROR, WARNING) or specific IP addresses directly from the command line.

## Technologies/tools used
- **Language:** Java (JDK 8+)
- **Core Libraries:** `java.io` (File I/O, BufferedReader), `java.util` (Collections, HashMap, Comparator)
- **Environment:** Windows Command Prompt / PowerShell / Linux Terminal

## Steps to install & run the project

### Prerequisites
- Java Development Kit (JDK) installed on your machine.
- A terminal or command prompt.

### Compilation
1. Clone the repository or download the source files.
2. Open your terminal and navigate to the project directory.
3. Compile the Java file by running:
   ```bash
   javac Main.java
   ```

### Running the Project
Once compiled, run the tool using `java Main` followed by the path to your log file and any optional flags.

```bash
# Basic parsing
java Main server.log

# Filter by severity
java Main server.log --severity ERROR

# Filter by IP address
java Main server.log --ip 192.168.1.50

# Run summary mode for pattern detection
java Main server.log --summary
```

## Instructions for testing
1. A sample `server.log` file is included in the repository for testing purposes. It contains over 150 lines of varied log data, including intentional malformed lines.
2. Compile the project as shown above.
3. Test the filtering by running `java Main server.log --severity ERROR`. Verify that only lines containing `[ERROR]` are printed to the console.
4. Test the pattern detection by running `java Main server.log --summary`. Verify that a formatted report is printed showing the "Top 5 IP Addresses" and "Top 5 Error Messages" with accurate occurrence counts.
5. Notice that the malformed lines in `server.log` do not cause the program to crash.

## Screenshots
*(Add your terminal screenshots here showing the output of the filter and summary commands)*
