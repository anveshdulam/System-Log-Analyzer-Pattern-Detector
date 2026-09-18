# Problem statement
System administrators and developers often deal with massive server logs that dump thousands of lines of text every hour. Reading these files manually in standard text editors is slow, inefficient, and can cause systems to crash due to memory overload. There is a need for a fast, memory-efficient utility to scan these files for specific errors, track specific IP addresses, and summarize frequent issues without the overhead of heavy third-party dependencies.

# Scope of the project
This project provides a lightweight, command-line Java application that streams log files line-by-line. It allows users to filter logs by severity or IP address and includes a pattern detection mode that aggregates data to display the top 5 most frequent errors and active IP addresses. The scope is limited to parsing standard bracketed log formats (`[Date] [Severity] [IP] Message`) and runs entirely in the terminal without a GUI.

# Target users
- **System Administrators** who need to quickly diagnose server issues from the command line.
- **Backend Developers** debugging application errors or tracking down faulty IP requests.
- **Security Analysts** looking for patterns in access logs, such as repeated failed authentication attempts.

# High-level features
1. **Memory-Efficient Parsing:** Uses `BufferedReader` to process multi-gigabyte files streamingly without loading the entire file into memory.
2. **Dynamic Filtering:** Supports command-line flags to filter output by `--severity` (e.g., ERROR, WARNING) or `--ip`.
3. **Pattern Detection (Summary Mode):** Analyzes the entire log file to aggregate and sort the Top 5 most active IP addresses and the Top 5 most frequent error messages.
4. **Resilient Execution:** Gracefully skips malformed or empty lines to ensure uninterrupted processing of messy log data.
5. **Zero Dependencies:** Built entirely with standard Java libraries (`java.io`, `java.util`), requiring no external build tools like Maven or Gradle.
