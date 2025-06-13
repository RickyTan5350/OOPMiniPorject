import java.util.ArrayList;

public class tripOption {
    private String tripName;
    private String placeName;
    private double cost;
    private double duration;
    private ArrayList<Trip> tripList;

    // Constructor
    public tripOption(String tripName, String placeName, double cost, double duration) {
        this.tripName = tripName;
        this.cost = cost;
        this.place = placeName;
        this.duration = duration;
        this.tripList = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        tripList.add(trip);
    }

    public void deleteTrip(Trip trip) {
        tripList.remove(trip);
    }

    public String getTripName() {
        return tripName;
    }

    public double getCost() {
        return cost;
    }

    public String getPlace(){
        return placeName;
    }

    public double getDuration() {
        return duration;
    }

    // Optional: display all trips
    public void displayAllTrips() {
        System.out.println("Trips available : ");
        for (Trip t : tripList) {
            System.out.println(t);
        }
    }
}
