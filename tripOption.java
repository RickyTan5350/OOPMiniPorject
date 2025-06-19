import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class TripOption {
    private double budget;
    private double duration;
    private String state;
    private ArrayList<Trip> tripList = new ArrayList<>();

    public void addTrip(String fileName) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter desired state to filter (e.g., Johor): ");
    String inputState = sc.nextLine().trim();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 6); 

            if (parts.length == 6) {
                String tripName = parts[0].trim();
                String destination = parts[1].trim();
                String state = parts[2].trim();
                double duration = Double.parseDouble(parts[3].trim());
                double cost = Double.parseDouble(parts[4].trim());
                String description = parts[5].trim();

                if (state.equalsIgnoreCase(inputState)) {
                    Trip trip;

                    if (cost <= 500) {
                        trip = new BudgetTrip(tripName, destination, duration, description);
                    } else if (cost <= 2000) {
                        trip = new StandardTrip(tripName, destination, duration, description);
                    } else {
                        trip = new PremiumTrip(tripName, destination, duration, description);
                    }

                    tripList.add(trip);
                    found = true;
                    System.out.println("✅ Added: " + trip.getTripDetails());
                }
            }
        }

        if (!found) {
            System.out.println("❌ No trips found in state: " + inputState);
        }

    } catch (IOException e) {
        System.out.println("⚠️ Error reading file: " + e.getMessage());
    }
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

