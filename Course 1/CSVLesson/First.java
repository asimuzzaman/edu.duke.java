
/**
 * Write a description of First here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class First {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {     
        for(CSVRecord record : parser) {
            String countryName = record.get("Country");
            if(countryName.equals(country)) {
                String output = countryName + ": "+record.get("Exports")+": "+record.get("Value (dollars)");;
                return output;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)  {
        int count = 0;
        
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if(exports.contains(exportItem))
                count++;
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            
            if(value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
}
