package weektwo;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        if (!q1.getInfo().equals(q2.getInfo()))
            return q1.getInfo().compareTo(q2.getInfo());
        if (q1.getDepth() < q2.getDepth())
            return -1;
        if (q1.getDepth() > q2.getDepth())
            return 1;

        return 0;
    }
}
