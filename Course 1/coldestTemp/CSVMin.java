
/**
 * Write a description of CSVMin here.
 * 
 * @author Md. Asimuzzaman 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If largestSoFar is nothing
			if (smallestSoFar == null) {
				smallestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp != -9999.0 && currentTemp < smallestTemp) {
					//If so update largestSoFar to currentRow
					smallestSoFar = currentRow;
				}
			}
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	
    public void testColdestHourInFile() {
    	//FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
    	FileResource fr = new FileResource();
    	CSVRecord largest = coldestHourInFile(fr.getCSVParser());
    	System.out.println("coldest temperature was " + largest.get("TemperatureF") +
    			   " at " + largest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String smallestFile = "";
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			if (smallestSoFar == null) {
				smallestSoFar = currentRow;
				smallestFile = f.getName();
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp < smallestTemp) {
					//If so update largestSoFar to currentRow
					smallestSoFar = currentRow;
					smallestFile = f.getName();
				}
			}
		}
		//The largestSoFar is the answer
		return smallestFile;
	}
	
    public void testFileWithColdestTemperature() {
    	String fileName = fileWithColdestTemperature();
    	System.out.println("Coldest day was in file " + fileName);
    	
    	FileResource fr = new FileResource("data/2013/"+fileName);
    	CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
    	System.out.println("Coldest temperature on that day was "+coldest.get("TemperatureF"));
    	
    	CSVParser parser = fr.getCSVParser();
    	for(CSVRecord record : parser) {
    	    System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));
    	}
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
        	if (smallestSoFar == null) {
        		smallestSoFar = currentRow;
        	}
        	//Otherwise
        	else {
        	    String current = currentRow.get("Humidity");
        	    
        	    if(current.equals("N/A"))
        	       continue;
        	       
                    double currentHum = Double.parseDouble(current);
                    double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
                    //Check if currentRow’s temperature > largestSoFar’s
                    if (currentHum < smallestHum) {
                    	//If so update largestSoFar to currentRow
                    	smallestSoFar = currentRow;
                    }
        	}
        }
        
        return smallestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
        	FileResource fr = new FileResource(f);
        	
        	CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
        	
        	if (smallestSoFar == null) {
        		smallestSoFar = currentRow;
        	}
        	
        	else {
        		double currentHum = Double.parseDouble(currentRow.get("Humidity"));
        		double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
        		
        		if (currentHum < smallestHum) {
        			smallestSoFar = currentRow;
        		}
        	}
        }
        return smallestSoFar;        
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+record.get("Humidity")+" at "+record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        int count = 0;
        
        for(CSVRecord rec:parser) {
            String current = rec.get("TemperatureF");
            if(current.equals("-9999"))
                continue;
            
            double currTemp = Double.parseDouble(current);
            
            sum += currTemp;
            count++;
        }
        
        return sum/count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " +averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0.0;
        int count = 0;
        
        for(CSVRecord rec:parser) {
            String current = rec.get("TemperatureF");
            String currHum = rec.get("Humidity");
            if(currHum.equals("N/A"))
                continue;
            
            double currentHum = Double.parseDouble(currHum);
            
            if(current.equals("-9999") || currentHum < value)
                continue;
            
            double currTemp = Double.parseDouble(current);
            
            sum += currTemp;
            count++;
        }
        
        return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        
        double avg = averageTemperatureWithHighHumidityInFile(parser,value);
        
        if(Double.isNaN(avg))
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is " + avg);
    }
}
