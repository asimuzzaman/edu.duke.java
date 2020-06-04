package weektwo;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "data/nov20quakedatasmall.atom";
        String source = "data/2/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
//        sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
//        sortByMagnitudeWithCheck(list);

        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	private int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxDex = from;
        for (int i=from+1; i<quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxDex).getDepth())
                maxDex = i;
        }
        return maxDex;
    }

    private void sortByLargestDepth(ArrayList<QuakeEntry> in) {
//        for (int i=0; i< in.size(); i++) {
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }

    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=1; i<quakeData.size()-numSorted; i++) {
            if (quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i-1);
                quakeData.set(i-1, quakeData.get(i));
                quakeData.set(i, temp);
            }
        }
    }

    private void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size()-1; i++) {
//            for (QuakeEntry qe : in)
//                System.out.println(qe);

            onePassBubbleSort(in, i);
//            System.out.println("After pass " + i);
        }
    }

    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=1; i<quakes.size(); i++) {
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude())
                return false;
        }
        return true;
    }

    private void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int i;
        for (i=0; i<in.size()-1; i++) {
            if (checkInSortedOrder(in))
                break;
            onePassBubbleSort(in, i);
        }
        System.out.println("Pass needed: " + i);
    }

    private void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int i;
        for (i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in))
                break;

            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        System.out.println("Pass needed: " + i);
    }
}
