import java.util.ArrayList;
import java.util.Scanner;

public class TripOption {
    private double budget;
    private double duration;
    private String state;
    private ArrayList<Trip> tripList = new ArrayList<>();

    public void addTrip(Trip trip) {
        tripList.add(trip);
    }

    public void deleteTrip(Trip trip) {
        tripList.remove(trip);
    }

    public void setBudget() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter budget: ");
        this.budget = sc.nextDouble();
    }

    public void setState() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter state: ");
        this.state = sc.nextLine();
    }

    public void setDuration() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter duration (days): ");
        this.duration = sc.nextDouble();
    }

    public void displayAllTrips() {
        for (Trip trip : tripList) {
            System.out.println(trip.getTripDetails());
        }
    }

    public ArrayList<Trip> getTripList() {
        return tripList;
    }

    public double getBudget() {
        return budget;
    }

    public double getDuration() {
        return duration;
    }
}

