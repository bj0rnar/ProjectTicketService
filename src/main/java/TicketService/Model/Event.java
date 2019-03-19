package TicketService.Model;

import TicketService.Users.Organizer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String name;
    private Venue venue;
    private int ticketPrice;
    private Organizer organizer;
    private LocalDate date;
    private Boolean areSeatsAvailable;
    private ArrayList<Venue.Seat> eventSeats;

    public Event(String name, Venue venue, LocalDate date, int ticketPrice , Boolean areSeatsAvailable, Organizer organizer) {
        this.name = name;
        this.venue = venue;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.organizer = organizer;
        this.areSeatsAvailable = areSeatsAvailable;
        if(areSeatsAvailable) {
            if(venue.getSeats().size() != 0) {
                eventSeats = venue.getSeats();
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public Venue getVenue() {
        return venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getAreSeatsAvailable() {
        return areSeatsAvailable;
    }

    public ArrayList<Venue.Seat> getEventSeats() {
        return eventSeats;
    }

    //Use this method if you need to reserve a spot. Example: Spesific date tickets.
    public void removeSeatingFrom(int SeatingNumberWhereAllSeatsAfterThisWillBeRemoved) {
        if(SeatingNumberWhereAllSeatsAfterThisWillBeRemoved > eventSeats.size()) {
            System.out.println("Too high seating number. Try a smaller number.");
        } else if(SeatingNumberWhereAllSeatsAfterThisWillBeRemoved < 0) {
            System.out.println("Do not use negative numbers...");
        }
        for(int x = SeatingNumberWhereAllSeatsAfterThisWillBeRemoved; x<eventSeats.size();x++) {

            eventSeats.remove(x);
        }

    }

    @Override
    public String toString() {
        return name;
    }
}
