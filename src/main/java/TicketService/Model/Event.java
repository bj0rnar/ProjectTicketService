package TicketService.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String name;
    private Venue venue;
    private LocalDate date;
    private Boolean areSeatsAvailable;
    private ArrayList<Venue.Seat> eventSeats;

    public Event(String name, Venue venue, LocalDate date, Boolean areSeatsAvailable) {
        this.name = name;
        this.venue = venue;
        this.date = date;
        this.areSeatsAvailable = areSeatsAvailable;
        if(areSeatsAvailable) {
            eventSeats = venue.getSeats();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
