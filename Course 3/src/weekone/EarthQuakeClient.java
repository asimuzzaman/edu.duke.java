package weekone;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin)
                answer.add(qe);
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData) {
            double dist = qe.getLocation().distanceTo(from);
            if(dist < distMax)
                answer.add(qe);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> bigOnes = filterByMagnitude(list,5.0);
        for (QuakeEntry qe : bigOnes)
            System.out.println(qe);
        System.out.println("Found " +bigOnes.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> quakes = filterByDistanceFrom(list,1000000,city);
        for (QuakeEntry qe : quakes) {
//            System.out.printf("%10f %s\n",qe.getLocation().distanceTo(city)/1000,qe.getInfo());
            System.out.println(qe.getLocation().distanceTo(city)/1000+" "+qe.getInfo());
        }
        System.out.println("Found "+quakes.size()+" quakes that match.");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/1/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    private ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList();

        for(QuakeEntry qe : quakeData) {
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
                answer.add(qe);
        }
        return answer;
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/1/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        ArrayList<QuakeEntry> quakes = filterByDepth(list,-4000,-2000);

        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
        System.out.println("Found quakes # " + quakes.size());
    }

    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList();

        for (QuakeEntry qe : quakeData) {
            if (where.equals("start")) {
                if (qe.getInfo().startsWith(phrase))
                    answer.add(qe);
            } else if (where.equals("end")) {
                if (qe.getInfo().endsWith(phrase))
                    answer.add(qe);
            } else if (where.equals("any")) {
                if (qe.getInfo().contains(phrase))
                    answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/1/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        ArrayList<QuakeEntry> quakes = filterByPhrase(list,"any","Can");

        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
        System.out.println("Found quakes # " + quakes.size());
    }
}
