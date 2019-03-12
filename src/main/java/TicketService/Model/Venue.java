package TicketService.Model;

import java.util.ArrayList;

public class Venue {
    public static ArrayList<Venue> venues = new ArrayList<>();
    private ArrayList<Seat> seats = new ArrayList<>();
    private String name;

    //Venue with seats
    public Venue(int totalSeats, String name) {
        for(int x=1; x<=totalSeats;x++) {
            seats.add(new Seat(x));
        }
        this.name = name;
    }
    //Venue without seats
    public Venue(String name) {
        this.name = name;
    }

    public static void CreateVenues() {
        if(venues.size() == 0) {
            venues.add(new Venue(100, "Hall 1"));
            venues.add(new Venue(1, "Hall 2"));
            venues.add(new Venue("Skogen"));
        }
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public String getName() {
        return name;
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
