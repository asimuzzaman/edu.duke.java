package weekone;

public class MagnitudeFilter implements Filter {
    private double min, max;
    private String name;

    public MagnitudeFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public MagnitudeFilter(double min, double max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= min && qe.getMagnitude() <= max;
    }

    @Override
    public String getName() {
        return name;
    }
}
