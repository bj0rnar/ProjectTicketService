package TicketService.Model;

import java.util.ArrayList;

public class Venue {

    public static ArrayList<Venue> venues = new ArrayList<>();
    private ArrayList<Seat> seats;
    private String name;
    private String venueId;


    /**
     * Venue constructor
     * @param totalSeats set to 0 if venue has no seats
     * @param name name of venue. e.g Telenor Arena
     */
    public Venue(int totalSeats, String name) {
        this.venueId = venues == null ? "0" : Integer.toString(venues.size());
        this.name = name;

        if(totalSeats != 0) {
            seats = new ArrayList<>();
            addSeats(totalSeats);
        }
    }


    public static void CreateVenues() {
        if(venues.size() == 0) {
            venues.add(new Venue(100, "Hall 1"));
            venues.add(new Venue(1, "Hall 2"));
            venues.add(new Venue(0,"Skogen"));
        }
    }

    public void addSeats(int totalSeats) {
        for(int x=1; x<=totalSeats;x++) {
            seats.add(new Seat(x));
        }
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public class Seat {
        int seatNumber;
        public Seat(int seatNumber) {
            this.seatNumber = seatNumber;
        }

        public int getSeatNumber() {
            return seatNumber;
        }
    }
}
