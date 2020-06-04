package weekone;

public class DepthFilter implements Filter {
    private double min, max;
    private String name;

    public DepthFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public DepthFilter(double min, double max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= min && qe.getDepth() <= max;
    }

    @Override
    public String getName() {
        return name;
    }
}
