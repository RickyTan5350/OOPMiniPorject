import java.util.ArrayList;

public class tripEvaluator {
    public Trip evaluate(double maxCost, double duration, String placeName, ArrayList<Trip> tripList) {
    Trip bestMatch = null;
    double smallestDifference = Double.MAX_VALUE;
    boolean placeFound = false;

    for (Trip trip : tripList) {
        boolean samePlace = trip.place.getName().equalsIgnoreCase(placeName);
        if (samePlace) {
            placeFound = true;

            double costDifference = Math.abs(trip.getTotalCost() - maxCost);
            double durationDifference = Math.abs(trip.durationDays - duration);

            double score = costDifference + durationDifference;
            if (score < smallestDifference) {
                smallestDifference = score;
                bestMatch = trip;
            }
        }
    }

    if (!placeFound) {
        System.out.println("❌ Sorry, no trips found to " + placeName + ".");
        return null;
    }

    return bestMatch;
}
}
