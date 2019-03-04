package TicketService.Model;

import java.util.Arrays;

public class Venue {
    private String venueId;
    private String venueName;

    //Venue handles seating option
    private Seat[] seats;

    //New stuff
    public Venue(String venueId, String venueName, int capacity) {
        this.venueId = venueId;
        this.venueName = venueName;

        this.seats = new Seat[capacity];
        for(int i= 0; i < seats.length; i++){
            seats[i] = new Seat(i);
        }

    }

    public Venue(String venueId, String venueName) {
        this.venueId = venueId;
        this.venueName = venueName;

    }

    //Reservation
    public boolean seatReservation(int seatNumber, Ticket ticket){
        if(!seats[seatNumber].isTaken()){
            seats[seatNumber].setTicket(ticket);
            ticket.setSeatNumber(seatNumber);
            return true;
        }
        else {
            return false;
        }
    }

    public void addMoreSeats(int addedSeats){
        this.seats = Arrays.copyOf(seats, seats.length + addedSeats);
    }

    public void removeSeats(int removedSeats){
        this.seats = Arrays.copyOf(seats, seats.length - removedSeats);
    }

    public Seat[] getSeats() {
        return seats;
    }

    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }
}
