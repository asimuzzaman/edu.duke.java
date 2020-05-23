package weekthree;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> unique = new ArrayList<>();

        for(LogEntry entry : records) {
            String ip = entry.getIpAddress();
            if(!unique.contains(ip))
                unique.add(ip);
        }
        return unique.size();
    }

    public void printAllHigherThanNum(int num) {
        for(LogEntry entry : records) {
            int status = entry.getStatusCode();
            if(status > num)
                System.out.println(entry);
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> list = new ArrayList();
        for(LogEntry entry : records) {
            String item = entry.toString();
            if(item.contains(someday) && !list.contains(entry.getIpAddress()))
                list.add(entry.getIpAddress());
        }
        return list;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> unique = new ArrayList<>();

        for(LogEntry entry : records) {
            int status = entry.getStatusCode();

            if((status >= low && status <= high) && !unique.contains(entry.getIpAddress()))
                unique.add(entry.getIpAddress());
        }
        return unique.size();
    }

    public HashMap countVisitsPerIP() {
        HashMap<String, Integer> count = new HashMap();

        for(LogEntry entry : records) {
            String ip = entry.getIpAddress();
            if(count.containsKey(ip)) {
                count.put(ip,count.get(ip)+1);
            } else {
                count.put(ip,1);
            }
        }
        return count;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> count) {
        int max = -1;
        for(int visit : count.values()) {
            if (visit > max)
                max = visit;
        }
        return max;
    }

    public ArrayList iPsMostVisits(HashMap<String, Integer> count) {
        int max = mostNumberVisitsByIP(count);
        ArrayList<String> maxIPs = new ArrayList();
        for(String ip : count.keySet()) {
            if(count.get(ip) == max)
                maxIPs.add(ip);
        }
        return maxIPs;
    }

    public HashMap iPsForDays() {
        HashMap<String, ArrayList<String>> daysIP = new HashMap();
        for(LogEntry entry : records) {
            String date = entry.getAccessTime().toString();
            date = date.substring(4,10);
            if (daysIP.containsKey(date)) {
                daysIP.get(date).add(entry.getIpAddress());
            } else {
                ArrayList<String> temp = new ArrayList();
                temp.add(entry.getIpAddress());
                daysIP.put(date,temp);
            }
        }
        return daysIP;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int max = -1;
        String maxDate = "";
        for(String date : map.keySet()) {
            int temp = map.get(date).size();
            if(temp > max) {
                max = temp;
                maxDate = date;
            }
        }
        return maxDate;
    }

    public ArrayList iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        ArrayList<String> iPsMostVisited = new ArrayList();
        HashMap<String,Integer> counterIP = new HashMap();
        for(String ip : map.get(day)) {
            if (counterIP.containsKey(ip)) {
                counterIP.put(ip,counterIP.get(ip)+1);
            } else {
                counterIP.put(ip,1);
            }
        }
        iPsMostVisited = iPsMostVisits(counterIP);
        return iPsMostVisited;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}