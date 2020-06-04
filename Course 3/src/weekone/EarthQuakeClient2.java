package weekone;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

//        Filter f = new MinMagFilter(4.0);

        ArrayList<QuakeEntry> m7  = filter(list, new MagnitudeFilter(3.5, 4.5));
        ArrayList<QuakeEntry> quakes  = filter(m7, new DepthFilter(-55000.0, -20000.0));

//        Location location = new Location(39.7392, -104.9903);
//        ArrayList<QuakeEntry> m7  = filter(list, new DistanceFilter(location,1000000));
//        ArrayList<QuakeEntry> quakes  = filter(m7, new PhraseFilter("end", "a"));

        for (QuakeEntry qe: quakes) {
            System.out.println(qe);
        }
        System.out.println("Found # "+quakes.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/1/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/1/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
//        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));

        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe : quakes)
            System.out.println(qe);

        System.out.println("Found # " + quakes.size());
        System.out.println("Filters used are: "+maf.getName());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/1/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
//        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        Location location = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(location,3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));

        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe : quakes)
            System.out.println(qe);

        System.out.println("Found # " + quakes.size());
        System.out.println("Filters used are: "+maf.getName());
    }
}
