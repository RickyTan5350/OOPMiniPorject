import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

class Location {
    private String place;
    private String state;

    Location(String p, String s) {
        this.place = p;
        this.state = s;
    }

    public String getPlace() {
        return place;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        return place + ", " + state;
    }
}

abstract class Trip {
    private String tripName;
    private Location location;
    private int duration;
    private double cost;
    private String description;

    public Trip(String tripName, String place, String state, int duration, double cost, String description) {
        this.tripName = tripName;
        this.location = new Location(place, state);
        this.duration = duration;
        this.cost = cost;
        this.description = description;
    }

    public abstract void getTripDetails();

    public String getTripName() {
        return tripName;
    }

    public String getLocation() {
        return location.toString();
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}

class BudgetTrip extends Trip {
    public BudgetTrip(String tripName, String place, String state, int duration, double cost, String description) {
        super(tripName, place, state, duration, cost, description);
    }

    public void getTripDetails() {
        System.out.printf("%-10s %-35s %-40s, %2d days, RM%7.2f", "[Budget]", super.getTripName(), super.getLocation(),
                super.getDuration(), super.getCost());

    }
}

class StandardTrip extends Trip {
    public StandardTrip(String tripName, String place, String state, int duration, double cost, String description) {
        super(tripName, place, state, duration, cost, description);
    }

    public void getTripDetails() {
        System.out.printf("%-10s %-35s %-40s, %2d days, RM%7.2f", "[Standard]", super.getTripName(),
                super.getLocation(),
                super.getDuration(), super.getCost());

    }
}

class PremiumTrip extends Trip {
    public PremiumTrip(String tripName, String place, String state, int duration, double cost, String description) {
        super(tripName, place, state, duration, cost, description);
    }

    public void getTripDetails() {
        System.out.printf("%-10s %-35s %-40s, %2d days, RM%7.2f", "[Premium]", super.getTripName(), super.getLocation(),
                super.getDuration(), super.getCost());

    }
}

class TripOption {
    private ArrayList<Trip> tripList = new ArrayList<>();

    public void addTrip(String fileName, String inputState, String tripType) {
        boolean found = false;
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",", 6);

                if (data.length == 6) {
                    String tripName = data[0];
                    String city = data[1];
                    String state = data[2];
                    int duration = Integer.parseInt(data[3]);
                    double cost = Double.parseDouble(data[4]);
                    String description = data[5];

                    if (state.equalsIgnoreCase(inputState)) {
                        Trip trip = null;
                        if (tripType.equals("1") && cost <= 500) {
                            trip = new BudgetTrip(tripName, city, state, duration, cost, description);
                        } else if (tripType.equals("2") && cost > 500 && cost <= 2000) {
                            trip = new StandardTrip(tripName, city, state, duration, cost, description);
                        } else if (tripType.equals("3") && cost > 2000) {
                            trip = new PremiumTrip(tripName, city, state, duration, cost, description);
                        }

                        if (trip != null) {
                            tripList.add(trip);
                            found = true;
                        }
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Unfortunately, there is no option for your selected state.");
        }
    }

    public ArrayList<Trip> getTripList() {
        return tripList;
    }

    public void displayTripsByType(String fileName, String tripType) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            System.out.println("\n=== Available " + getTripTypeName(tripType) + " Trips ===");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",", 6);

                if (data.length == 6) {
                    String tripName = data[0];
                    String city = data[1];
                    String state = data[2];
                    int duration = Integer.parseInt(data[3]);
                    double cost = Double.parseDouble(data[4]);
                    String description = data[5];

                    Trip tempTrip = null;
                    if (tripType.equals("1") && cost <= 500) {
                        tempTrip = new BudgetTrip(tripName, city, state, duration, cost, description);
                    } else if (tripType.equals("2") && cost > 500 && cost <= 2000) {
                        tempTrip = new StandardTrip(tripName, city, state, duration, cost, description);
                    } else if (tripType.equals("3") && cost > 2000) {
                        tempTrip = new PremiumTrip(tripName, city, state, duration, cost, description);
                    }

                    if (tempTrip != null) {
                        tempTrip.getTripDetails();
                        System.out.println();
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private String getTripTypeName(String tripType) {
        switch (tripType) {
            case "1":
                return "Budget";
            case "2":
                return "Standard";
            case "3":
                return "Premium";
            default:
                return "Unknown";
        }
    }
}

class TripRecommendation {
    public static void recommendAndSortByBudget(ArrayList<Trip> trips, double budget) {
        ArrayList<Trip> filtered = new ArrayList<>();
        for (Trip t : trips) {
            if (t.getCost() <= budget) {
                filtered.add(t);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No trips found within your budget.");
            return;
        }

        filtered.sort((a, b) -> Double.compare(a.getCost(), b.getCost()));
        System.out.println("\n=== Trips Within Budget (Lowest to Highest) ===");
        for (Trip t : filtered) {
            t.getTripDetails();
            System.out.println();
        }
    }

    public static void recommendAndSortByDuration(ArrayList<Trip> trips, int duration) {
        ArrayList<Trip> filtered = new ArrayList<>();
        for (Trip t : trips) {
            if (t.getDuration() <= duration) {
                filtered.add(t);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No trips found within your desired duration.");
            return;
        }

        filtered.sort((a, b) -> Integer.compare(a.getDuration(), b.getDuration()));
        System.out.println("\n=== Trips Within Duration (Shortest to Longest) ===");
        for (Trip t : filtered) {
            t.getTripDetails();
            System.out.println();
        }
    }
}

class Wishlist {
    private ArrayList<Trip> wishList = new ArrayList<>();

    public void addToWishlist(Trip t) {
        if (!wishList.contains(t)) {
            wishList.add(t);
            System.out.println("Trip added to wishlist: ");
            t.getTripDetails();
            System.out.println();
        } else {
            System.out.println("Trip already in wishlist.");
        }
    }

    public void deleteWishlist(int index) {
        if (index >= 0 && index < wishList.size()) {
            Trip removed = wishList.remove(index);
            System.out.println("Removed: ");
            removed.getTripDetails();
            System.out.println();
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void displayWishlist() {
        if (wishList.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            System.out.println("\n=== Your Wishlist ===");
            for (int i = 0; i < wishList.size(); i++) {
                System.out.printf("%2d .", (i + 1));
                wishList.get(i).getTripDetails();
                System.out.println();

            }
        }
    }

    public boolean isEmpty() {
        return wishList.isEmpty();
    }

    public int size() {
        return wishList.size();
    }
}

// ... (previous Trip, BudgetTrip, StandardTrip, PremiumTrip, TripOption,
// TripRecommendation, Wishlist classes stay the same)
public class TripPlanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TripOption tripOption = new TripOption();
        Wishlist wishlist = new Wishlist();
        boolean running = true;

        System.out.println("=== Welcome to Budget Trip Planner ===");

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Wishlist");
            System.out.println("2. Select Trip");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String mainChoice = sc.nextLine();

            switch (mainChoice) {
                case "1":
                    boolean wishlistMenu = true;
                    while (wishlistMenu) {
                        System.out.println("\nWishlist Menu:");
                        System.out.println("1. View Wishlist");
                        System.out.println("2. Remove Trip from Wishlist");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Choose option: ");
                        String wChoice = sc.nextLine();

                        switch (wChoice) {
                            case "1":
                                wishlist.displayWishlist();
                                break;
                            case "2":
                                wishlist.displayWishlist();
                                System.out.println();
                                if (!wishlist.isEmpty()) {
                                    System.out.print("Enter trip number to remove: ");
                                    int removeIndex = sc.nextInt();
                                    sc.nextLine();
                                    wishlist.deleteWishlist(removeIndex - 1);
                                }
                                break;
                            case "3":
                                wishlistMenu = false;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;

                case "2":
                    System.out.println("Please choose your trip type:");
                    System.out.println("1. Budget Trip  [ less than RM 500 ]");
                    System.out.println("2. Standard Trip  [ RM 500 - RM 1999 ]");
                    System.out.println("3. Premium Trip  [ RM 2000 above ]");
                    System.out.print("Enter your choice: ");
                    String typeChoice = sc.nextLine();

                    tripOption.displayTripsByType("malaysia_trip_data.txt", typeChoice);

                    System.out.println("\nWhat do you want to do next?");
                    System.out.println("1. Filter by state");
                    System.out.println("2. Add a trip to wishlist");
                    System.out.print("Enter your choice: ");
                    String afterDisplayChoice = sc.nextLine();

                    if (afterDisplayChoice.equals("1")) {
                        System.out.print("Enter state: ");
                        String state = sc.nextLine();
                        tripOption.getTripList().clear();
                        tripOption.addTrip("malaysia_trip_data.txt", state, typeChoice);

                        if (!tripOption.getTripList().isEmpty()) {
                            System.out.println("\n=== Trips in " + state + " ===");
                            for (Trip t : tripOption.getTripList()) {
                                t.getTripDetails();
                                System.out.println();
                            }

                            boolean filterMenu = true;
                            while (filterMenu) {
                                System.out.println("\nWhat would you like to do?");
                                System.out.println("1. Filter and sort by budget");
                                System.out.println("2. Filter and sort by duration");
                                System.out.println("3. Add trip to wishlist");
                                System.out.println("4. Exit to main menu");
                                System.out.print("Enter your choice: ");
                                String filterChoice = sc.nextLine();

                                switch (filterChoice) {
                                    case "1":
                                        System.out.print("Enter max budget: ");
                                        double budget = sc.nextDouble();
                                        sc.nextLine();
                                        TripRecommendation.recommendAndSortByBudget(tripOption.getTripList(), budget);
                                        break;
                                    case "2":
                                        System.out.print("Enter max duration (in days): ");
                                        int duration = sc.nextInt();
                                        sc.nextLine();
                                        TripRecommendation.recommendAndSortByDuration(tripOption.getTripList(),
                                                duration);
                                        break;
                                    case "3":
                                        ArrayList<Trip> filteredTrips = tripOption.getTripList();
                                        if (!filteredTrips.isEmpty()) {
                                            System.out.println("\nChoose a trip to add (enter number):");
                                            for (int i = 0; i < filteredTrips.size(); i++) {
                                                System.out.printf("%2d .", (i + 1));
                                                filteredTrips.get(i).getTripDetails();
                                                System.out.println();
                                            }
                                            System.out.print("Enter trip number: ");
                                            int index = sc.nextInt();
                                            sc.nextLine();
                                            if (index >= 1 && index <= filteredTrips.size()) {
                                                wishlist.addToWishlist(filteredTrips.get(index - 1));
                                            } else {
                                                System.out.println("Invalid selection.");
                                            }
                                        }
                                        filterMenu = false; // Exit to main menu
                                        break;
                                    case "4":
                                        filterMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                        } else {
                            System.out.println("No trips found in that state.");
                        }
                    } else if (afterDisplayChoice.equals("2")) {
                        ArrayList<Trip> currentTrips = new ArrayList<>();
                        try (Scanner reader = new Scanner(new File("malaysia_trip_data.txt"))) {
                            while (reader.hasNextLine()) {
                                String[] parts = reader.nextLine().split(",", 6);
                                if (parts.length == 6) {
                                    String tripName = parts[0];
                                    String city = parts[1];
                                    String state = parts[2];
                                    int duration = Integer.parseInt(parts[3]);
                                    double cost = Double.parseDouble(parts[4]);
                                    String desc = parts[5];

                                    Trip tempTrip = null;
                                    if (typeChoice.equals("1") && cost <= 500) {
                                        tempTrip = new BudgetTrip(tripName, city, state, duration, cost, desc);
                                    } else if (typeChoice.equals("2") && cost > 500 && cost <= 2000) {
                                        tempTrip = new StandardTrip(tripName, city, state, duration, cost, desc);
                                    } else if (typeChoice.equals("3") && cost > 2000) {
                                        tempTrip = new PremiumTrip(tripName, city, state, duration, cost, desc);
                                    }
                                    if (tempTrip != null)
                                        currentTrips.add(tempTrip);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                        if (!currentTrips.isEmpty()) {
                            System.out.println("\nChoose a trip to add (enter number):");
                            for (int i = 0; i < currentTrips.size(); i++) {
                                System.out.printf("%2d .", (i + 1));
                                currentTrips.get(i).getTripDetails();
                                System.out.println();
                            }
                            System.out.print("Enter trip number: ");
                            int pick = sc.nextInt();
                            sc.nextLine();
                            if (pick >= 1 && pick <= currentTrips.size()) {
                                wishlist.addToWishlist(currentTrips.get(pick - 1));
                            } else {
                                System.out.println("Invalid selection.");
                            }
                        } else {
                            System.out.println("No trips available to add.");
                        }
                        break; // exit to main menu after adding
                    }

                    else {
                        System.out.println("Invalid choice!!!Returning to main menu");
                        break;
                    }
                    break;

                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
        sc.close();
    }
}


