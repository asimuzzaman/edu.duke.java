
/**
 * Write a description of Project here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Project {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalGirls = 0, totalBoys = 0, totalGirlName = 0, totalBoyName = 0;
        
        for(CSVRecord item : fr.getCSVParser(false)) {
            int count = Integer.parseInt(item.get(2));
            
            if(item.get(1).equals("M")) {
                totalBoys += count;
                totalBoyName++;
            } else {
                totalGirls += count;
                totalGirlName++;
            }
        }
        
        System.out.println("Total: " + (totalGirls+totalBoys));
        System.out.println("Total Girls: " + totalGirls);
        System.out.println("Total Boys: " + totalBoys);
        System.out.println("Total Girl names: " + totalGirlName);
        System.out.println("Total Boy names: " + totalBoyName);
        System.out.println("Total Names: " + (totalGirlName+totalBoyName));
    }
    
    public int getRank(int year, String name, String gender) {
        String file = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(file);
        int rank = 0;
        
        for(CSVRecord item : fr.getCSVParser(false)) {
            if(item.get(1).equals(gender))
                rank++;
            
            if(item.get(0).equals(name) && item.get(1).equals(gender))
                return rank;
        }
        
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        String file = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(file);
        int currRank = 0;
        
        for(CSVRecord item : fr.getCSVParser(false)) {
            if(item.get(1).equals(gender))
                currRank++;
            
            if(currRank == rank && item.get(1).equals(gender))
                return item.get(0);
        }

        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int ownRank = getRank(year,name,gender);
        String newName = getName(newYear,ownRank,gender);
        
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear+".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = Integer.MAX_VALUE;
        int result = -1;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            String yearS = f.getName().substring(3,7);
            int year = Integer.parseInt(yearS);
            int rank = getRank(year,name,gender);
            
            if(rank != -1 && rank < highestRank) {
                highestRank = rank;
                result = year;
            }
        }
        
        return result;
    }
    
    public double getAverageRank(String name, String gender) {
        int totalRank = 0;
        int count = 0;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            String yearS = f.getName().substring(3,7);
            int year = Integer.parseInt(yearS);
            int rank = getRank(year,name,gender);
            
            if(rank != -1) {
                totalRank += rank;
                count++;
            }
        }
        
        if(count == 0)
            return -1.00;
        return (double)totalRank/count;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String file = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(file);
        int total = 0;
        int rank = getRank(year,name,gender);
        int currRank = 1;
        
        for(CSVRecord item : fr.getCSVParser(false)) {
            if(rank <= currRank)
                break;
                
            if(item.get(1).equals(gender)) {
                currRank++;
                total += Integer.parseInt(item.get(2));
            }
        }
        
        return total;
    }
    
    public void test() {
        //totalBirths();
        
        //System.out.println(getRank(1971,"Frank", "M")); //check file name before calling it
        //System.out.println(getName(1982, 450, "M")); //check file name before calling it
        //whatIsNameInYear("Owen",1974,2014,"M");
        //System.out.println(yearOfHighestRank("Mich", "M"));
        //System.out.println(getAverageRank("Robert", "M"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M")); //check file name before calling it
    }
}
