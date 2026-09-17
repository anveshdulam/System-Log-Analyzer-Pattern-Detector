import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <logfile> [--severity <level>] [--ip <ip>] [--summary]");
            return;
        }

        String filePath = args[0];
        String filterSeverity = null;
        String filterIp = null;
        boolean showSummary = false;

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--severity") && i + 1 < args.length) {
                filterSeverity = args[i+1];
                i++;
            } else if (args[i].equals("--ip") && i + 1 < args.length) {
                filterIp = args[i+1];
                i++;
            } else if (args[i].equals("--summary")) {
                showSummary = true;
            }
        }

        Map<String, Integer> ipCounts = new HashMap<>();
        Map<String, Integer> errorCounts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
    // read file and parse lines
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; 
                
                int dateEnd = line.indexOf("] [");
                int severityEnd = line.indexOf("] [", dateEnd + 1);
                int ipEnd = line.indexOf("] ", severityEnd + 1);

                if (dateEnd != -1 && severityEnd != -1 && ipEnd != -1) {
                    String date = line.substring(1, dateEnd);
                    String severity = line.substring(dateEnd + 3, severityEnd);
                    String ip = line.substring(severityEnd + 3, ipEnd);
                    String message = line.substring(ipEnd + 2);
                    
                    boolean match = true;
                    
                        if (filterSeverity != null && !severity.equals(filterSeverity)) {
                            match = false;
                        }
                    if (filterIp != null && !ip.equals(filterIp)) {
                        match = false;
                    }

                    if (match && !showSummary) {
                        System.out.println(line);
                    }

                    if (showSummary) {
                        ipCounts.put(ip, ipCounts.getOrDefault(ip, 0) + 1);
                        if (severity.equals("ERROR")) {
                            errorCounts.put(message, errorCounts.getOrDefault(message, 0) + 1);
                        }
                    }
                } else {
                    if (filterSeverity == null && filterIp == null && !showSummary) {
                        System.out.println("Malformed line skipped: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (showSummary) {
            System.out.println("\nLog Summary Report:");
            System.out.println("-------------------");
            
            System.out.println("\nTop 5 IP Addresses:");
            printTop5(ipCounts);
            
            System.out.println("\nTop 5 Error Messages:");
            printTop5(errorCounts);
            System.out.println();
        }
    }


// print top 5 counts
    private static void printTop5(Map<String, Integer> counts) {
        if (counts.isEmpty()) {
            System.out.println("  None found.");
            return;
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        
        list.sort((a, b) -> b.getValue().compareTo(a.getValue())); 

        int limit = Math.min(5, list.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
