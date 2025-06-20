abstract class Trip {
    protected String tripName;
    protected String location;
    protected double duration;
    protected double cost;
    protected String description;

    public Trip(String tripName, String location, double duration, double cost, String description) {
        this.tripName = tripName;
        this.location = location;
        this.duration = duration;
        this.cost = cost;
        this.description = description;
    }


    public abstract String getTripDetails();

    public String getLocation() {
        return location;
    }

    public double getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getTripName() {
        return tripName;
    }
}