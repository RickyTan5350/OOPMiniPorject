import java.util.ArrayList;

public class TripRecommendation {
    private TripOption a = new TripOption();

    public Trip filter(double budget, String state, ArrayList<Trip> tripList) {
        Trip bestMatch = null;
        double minDifference = Double.MAX_VALUE;

        for (Trip trip : tripList) {
            if (trip.location.equalsIgnoreCase(state) && trip.calculateCost() <= budget) {
                double diff = Math.abs(trip.calculateCost() - budget);
                if (diff < minDifference) {
                    minDifference = diff;
                    bestMatch = trip;
                }
            }
        }

        if (bestMatch == null) {
            System.out.println("No trip found matching state \"" + state + "\" within budget $" + budget);
        }

        return bestMatch;
    }

    public void sortByBudget(double maxBudget) {
    ArrayList<Trip> trips = a.getTripList();

    ArrayList<Trip> filtered = new ArrayList<>();
    for (Trip t : trips) {
        if (t.calculateCost() <= maxBudget) {
            filtered.add(t);
        }
    }

    for (int i = 0; i < filtered.size() - 1; i++) {
        for (int j = i + 1; j < filtered.size(); j++) {
            if (filtered.get(i).calculateCost() > filtered.get(j).calculateCost()) {
                Trip temp = filtered.get(i);
                filtered.set(i, filtered.get(j));
                filtered.set(j, temp);
            }
        }
    }

    for (Trip t : filtered) {
        System.out.println(t.getTripDetails());
    }
}
    public void sortByDuration(double maxDuration) {
    ArrayList<Trip> trips = a.getTripList();

    ArrayList<Trip> filtered = new ArrayList<>();
    for (Trip t : trips) {
        if (t.duration <= maxDuration) {
            filtered.add(t);
        }
    }

    for (int i = 0; i < filtered.size() - 1; i++) {
        for (int j = i + 1; j < filtered.size(); j++) {
            if (filtered.get(i).duration > filtered.get(j).duration) {
                Trip temp = filtered.get(i);
                filtered.set(i, filtered.get(j));
                filtered.set(j, temp);
            }
        }
    }

    for (Trip t : filtered) {
        System.out.println(t.getTripDetails());
    }
}
}
