class Wishlist {
    private List<Trip> wishList = new ArrayList<>();



    public void deleteWishlist(Trip t) {
        wishList.remove(t);
    }

    public void displayWishlist() {
        for (Trip t : wishList) {
            int index = wishList.indexOf(t); 
            System.out.println((index + 1) + ". " + t.getTripDetails());
        }
    }
}
