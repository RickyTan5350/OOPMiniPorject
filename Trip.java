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

    
}
