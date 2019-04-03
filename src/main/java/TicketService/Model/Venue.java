package TicketService.Model;

import TicketService.DataAccess.DataContext;

import java.util.ArrayList;

public class Venue {


    private ArrayList<Seat> seats;
    private String name;


    /**
     * Venue constructor
     * @param totalSeats set to 0 if venue has no seats
     * @param name name of venue. e.g Telenor Arena
     */
    public Venue(int totalSeats, String name) {
        this.name = name;
        if(totalSeats != 0) {
            seats = new ArrayList<>();
            addSeats(totalSeats);
        }
    }


    public static ArrayList<Venue> getVenues() {
        return DataContext.getVenues();
    }

    public void addSeats(int totalSeats) {
        for(int x=0; x<totalSeats;x++) {
            seats.add(new Seat(x));
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
