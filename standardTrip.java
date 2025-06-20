class standardTrip extends Trip {
    public standardTrip(String tripName, String location, double duration, double cost, String description) {
        super(tripName, location, duration, cost, description);
    }



    public String getTripDetails() {
        return "[Standard] " + tripName + " at " + location + ", RM" + calculateCost() + ", " + duration + " days";
    }
}