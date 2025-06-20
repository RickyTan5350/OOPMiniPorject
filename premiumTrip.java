class premiumTrip extends Trip {
    public premiumTrip(String tripName, String location, double duration, double cost, String description) {
        super(tripName, location, duration, cost, description);
    }


    public String getTripDetails() {
        return "[Premium] " + tripName + " at " + location + ", RM" + calculateCost() + ", " + duration + " days";
    }
}