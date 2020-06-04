package weekone;

public class DistanceFilter implements Filter {
    private Location loc;
    private double maxDistance;
    private String name;

    public DistanceFilter(Location location, double maxDistance) {
        loc = location;
        this.maxDistance = maxDistance;
    }

    public DistanceFilter(Location location, double maxDistance, String name) {
        loc = location;
        this.maxDistance = maxDistance;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return loc.distanceTo(qe.getLocation()) < maxDistance;
    }

    @Override
    public String getName() {
        return name;
    }
}
