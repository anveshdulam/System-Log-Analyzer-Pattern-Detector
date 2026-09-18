# Project Report: System Log Analyzer & Pattern Detector

**Course:** Programming in Java 
**Student:** DULAM ANVESH GOUD
**Registration Number:** 25BAI10595

---

## 2. Introduction
Server logs track everything that happens on a system. It gets hard to find errors when files get too big. i built a simple java tool to read these files from the command line. it filters out the noise and summarizes the most frequent issues. it saves a lot of time.

## 3. Problem Statement & Objectives
Servers dump out thousands of lines of text every hour. reading them manually in notepad is slow and sometimes crashes the computer. there needs to be a fast way to scan these files for specific errors or track a specific ip address. 
**objectives:** the main goal is to build a fast, memory-safe tool that parses logs without crashing, filters data based on user input, and automatically detects patterns like the most frequent errors.

## 4. Functional Requirements
my project has three main modules:
1. **File Parsing:** reads a standard log file line by line. extracts the date, severity, ip, and the message.
2. **Filtering:** takes command line arguments to only show certain things. like --severity ERROR or --ip 192.168.1.50.
3. **Pattern Detection:** a summary mode that counts everything up. it finds the top 5 errors and top 5 ip addresses.

## 5. Non-functional Requirements
1. **Memory Efficiency:** it doesn't load the whole file into memory at once.
2. **Speed:** uses simple string splitting instead of heavy regex processing.
3. **Usability:** runs directly from the terminal with easy flags.
4. **Reliability:** skips blank or broken lines instead of crashing the whole program.

## 6. System Architecture
it is a simple procedural cli tool. it takes input from the terminal, streams the file from the disk, processes the text in a loop, and prints the results back to the screen.

```text
 [Terminal Input] ---> [Main Argument Parser]
                              |
                              V
 [server.log File] --> [File BufferedReader]
                              |
                              V
                       [Filter / Aggregate]
                        /               \
                       /                 \
            [Print Matches]        [Generate Summary Map]
```

## 7. Design Diagrams

### Use Case Diagram
```text
[User] ---> (Run Basic Log Parse)
[User] ---> (Filter Logs by Severity)
[User] ---> (Filter Logs by IP Address)
[User] ---> (Generate Summary Report)
```

### Workflow Diagram
```text
[Start Program]
      |
[Parse CLI Arguments]
      |
[Summary Mode Active?]
   /              \
 YES               NO
 /                  \
[Read Line]       [Read Line]
 |                  |
[Aggregate Data]  [Matches Filters?]
 |                  |
[Print Top 5]     [Print Line]
 \                  /
  \                /
   [End Program]
```

### Class/Component Diagram
```text
+-----------------------------------+
| Main                              |
+-----------------------------------+
| + main(String[] args): void       |
| - printTop5(Map counts): void     |
+-----------------------------------+
        |                  |
     uses                uses
        V                  V
 [BufferedReader]      [HashMap]
```

### Sequence Diagram
```text
[User]         [Main.java]         [server.log]
  |                 |                   |
  |--Run Command--->|                   |
  |                 |----Open File----->|
  |                 |<---Read Line------|
  |                 |                   |
  |<--Print Output--|                   |
  |                 |                   |
```

## 8. Design Decisions & Rationale
- **BufferedReader instead of Files.readAllLines():** i wanted to make sure it doesn't run out of memory. reading line by line fixes that.
- **String Splitting instead of Regex:** regular expressions are hard to read and overkill for this. i just used indexOf() to find the brackets.
- **HashMap:** i used hash maps to count the ips and errors because they are fast for looking things up and updating counts.

## 9. Implementation Details
i wrote the whole thing in Main.java. the program checks the args array to see what the user wants. it uses a try-with-resources block to open the file safely. inside the loop, it cuts the string up. if the user wants a summary, it adds the data to the maps. otherwise, it just prints the line if it matches the filters.

## 10. Screenshots / Results
*(Note: take a screenshot of your terminal running the code and paste it here)*
- filtering works perfectly. running java Main server.log --severity ERROR only shows errors.
- summary mode sorts the maps and prints a simple list of the top 5 items.

## 11. Testing Approach
i made a fake server.log file to test it. i put in some normal lines, some errors, and some completely broken lines without brackets. the program skipped the broken ones exactly like it was supposed to.

## 12. Challenges Faced
extracting the text was tricky at first. i kept getting StringIndexOutOfBoundsException when trying to cut the strings. i had to carefully check the math for the string boundaries to get it right.

## 13. Learnings & Key Takeaways
i got a lot of practice with java file streams and maps. i also learned how to use a custom comparator to sort a list of map entries by their values.

## 14. Future Enhancements
if i kept working on it, i would:
1. add a feature to save the summary to a file instead of just printing it.
2. make a separate class for the parser to clean up the main method.

## 15. References
- java docs for bufferedreader and hashmap.
- class slides on file i/o.
