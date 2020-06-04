package weekone;

import java.util.ArrayList;

public class LargestQuakes {
    public LargestQuakes() {

    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/1/nov20quakedata.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

//        System.out.println(list.get(indexOfLargest(list)).getMagnitude());

        ArrayList<QuakeEntry> large = getLargest(list, 50);
        for (QuakeEntry qe : large) {
            System.out.println(qe);
        }
    }

    private int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int maxIndex = 0;
        double max = quakeData.get(0).getMagnitude();
        for (int i=0; i<quakeData.size(); i++) {
            if (quakeData.get(i).getMagnitude() > max) {
                max = quakeData.get(i).getMagnitude();
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        for (int i=0; i<howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }

        return ret;
    }
}
