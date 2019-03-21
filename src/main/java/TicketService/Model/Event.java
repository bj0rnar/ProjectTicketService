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
    private String eventId;

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

    @Override
    public String toString() {
        return name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
