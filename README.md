# 📊 System Log Analyzer & Pattern Detector

> A lightweight, zero-dependency Java command-line utility for parsing, filtering, and summarizing server log files. Built for the flipped course evaluation in Programming in Java.

---

## 📝 Overview

Server logs can get massive and impossible to read manually. I built this tool to automate the process of finding errors and tracking down suspicious IPs. It reads standard log files line-by-line (to save memory) and lets you filter the data on the fly or generate a quick summary report of the most frequent system issues.

### 🌟 Key Features
- **Zero Dependencies:** Written purely in standard Java (`java.io`, `java.util`). No Maven, Gradle, or external libraries required.
- **Memory Efficient:** Uses `BufferedReader` to process logs streamingly—it won't crash on multi-gigabyte log files.
- **Pattern Detection:** Aggregates data to automatically find the Top 5 most frequent errors and active IP addresses.
- **Resilient Parsing:** Gracefully skips empty or malformed lines without throwing a stack trace.

---

## 🚀 Getting Started

### Prerequisites
- Any standard **Java Development Kit (JDK 8 or higher)** installed on your machine.
- A terminal or command prompt.

### Compilation
Clone the repository or download the files, navigate to the folder in your terminal, and run:

```bash
javac Main.java
```

---

## 💻 Usage Guide

Once compiled, run the tool using `java Main` followed by the path to your log file and any optional flags.

### Command Syntax
```bash
java Main <path_to_log_file> [flags...]
```

### Examples

**1. Basic Parsing (No filters)**  
Simply print out all valid lines from the log.
```bash
java Main server.log
```

**2. Filter by Severity**  
Only show logs that match a specific severity (e.g., ERROR, INFO, WARNING).
```bash
java Main server.log --severity ERROR
```

**3. Filter by IP Address**  
Track the activity of a single user or server.
```bash
java Main server.log --ip 192.168.1.50
```

**4. 📊 Summary Mode (Pattern Detection)**  
Instead of printing raw logs, scan the whole file and print a summary report of the top 5 IPs and top 5 error messages.
```bash
java Main server.log --summary
```

**5. Combining Filters**  
You can combine flags! For example, find all errors from a specific IP:
```bash
java Main server.log --severity ERROR --ip 192.168.1.50
```

---

## ⚙️ Log Format Requirements

This tool is designed to parse standard bracketed log formats. The string parser relies on the `] [` separators to cleanly extract the data.

**Expected Format:**
`[Date] [Severity] [IP] Message`

**Example Line:**
`[2026-09-17 10:00:01] [INFO] [192.168.1.10] Server started successfully`

---

## 🔌 Integration into Other Projects

If you want to use this logic inside a larger Java application (like a Spring Boot web app or a GUI dashboard), you can easily adapt the code:

1. **Remove the CLI wrapper:** Take the `while ((line = br.readLine()) != null)` loop out of `public static void main` and put it in a helper method like `public void analyze(File logFile)`.
2. **Use Objects:** Instead of printing to `System.out`, map the parsed strings to a custom `LogEntry` class and store them in an `ArrayList`.
3. **Return the Maps:** The `--summary` logic uses `HashMap<String, Integer>`. You can simply return these maps to your backend to generate charts or graphs on a UI.

---
*Developed for a course project. Pure Java, no external libraries.*
