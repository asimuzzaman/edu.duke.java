package weektwo;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
//        String last1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(' ')).toLowerCase();
//        String last2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(' ')).toLowerCase();
        String last1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(' '));
        String last2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(' '));

        if (!last1.equals(last2))
            return last1.compareTo(last2);
        if (q1.getMagnitude() < q2.getMagnitude())
            return -1;
        if (q1.getMagnitude() > q2.getMagnitude())
            return 1;

        return 0;
    }
}
