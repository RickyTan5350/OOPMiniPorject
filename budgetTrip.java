class budgetTrip extends Trip {
    public budgetTrip(String tripName, String location, double duration, double cost, String description) {
        super(tripName, location, duration, cost, description);
    }



    public String getTripDetails() {
        return "[Budget] " + tripName + " at " + location + ", RM" + calculateCost() + ", " + duration + " days";
    }
}
