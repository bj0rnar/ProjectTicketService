package TicketService.Model;

import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Users.Organizer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String name;
    private Venue venue;
    private int ticketPrice;
    private int totalTickets;
    private int availableTickets;
    private Organizer organizer;
    private LocalDate date;
    private Boolean areSeatsAvailable;
    private ArrayList<Venue.Seat> eventSeats;
    private ArrayList<String> verificationCodeList = new ArrayList<>();

    /**
     * Constructor for none-seated event
     * @param name Name of event
     * @param venue location of event
     * @param date start date of event
     * @param ticketPrice price for each ticket
     * @param organizer main responsible for the event
     * @param totalTickets Total amount of tickets available for this event
     */
    public Event(String name, Venue venue, LocalDate date, int ticketPrice, Organizer organizer, int totalTickets) {
        this.name = name;
        this.venue = venue;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.organizer = organizer;
        this.areSeatsAvailable = false;
        this.totalTickets = totalTickets;
        availableTickets = totalTickets;
    }

    /**
     * Constructor for seated event
     * @param name Name of event
     * @param venue location of event
     * @param date start date of event
     * @param ticketPrice price for each ticket
     * @param organizer main responsible for the event
     */
    public Event(String name, Venue venue, LocalDate date, int ticketPrice , Organizer organizer) throws VenueHasNoSeatsException {
        if(venue.getSeats().size() != 0) {
            this.name = name;
            this.venue = venue;
            this.date = date;
            this.ticketPrice = ticketPrice;
            this.organizer = organizer;
            this.areSeatsAvailable = true;
            this.totalTickets = venue.getSeats().size();
            availableTickets = totalTickets;
            eventSeats = venue.getSeats();
        }
        else
            throw new VenueHasNoSeatsException("Seated Event instantiated without venue with seats.");


    }

    public ArrayList<String> getVerificationCodeList() { return verificationCodeList; }

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

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public boolean isSeatAvailable(int seatNumber){
        for(Venue.Seat seat : eventSeats){
            if(seat.getSeatNumber() == seatNumber){
                return true;
            }
        }
        if(seatNumber == -1 && eventSeats.size() > 0) {
            return true;
        }
        return false;
    }

    public Venue.Seat popSeatFromEventSeatList(int seatNumber){
        for(Venue.Seat seat : eventSeats){
            if(seat.getSeatNumber() == seatNumber) {
                eventSeats.remove(seat);
                return seat;
            }
        }
        return null;
    }

    //Remove all seats after
    public void removeSeatingFrom(int SeatingNumberWhereAllSeatsAfterThisWillBeRemoved) {
        if(SeatingNumberWhereAllSeatsAfterThisWillBeRemoved > eventSeats.size()) {
            System.out.println("Too high seating number. Try a smaller number.");
        } else if(SeatingNumberWhereAllSeatsAfterThisWillBeRemoved < 0) {
            System.out.println("Do not use negative numbers...");
        }
        for(int x = SeatingNumberWhereAllSeatsAfterThisWillBeRemoved; x<eventSeats.size();x++) {

            for(Venue.Seat seat : eventSeats) {
                if(seat.getSeatNumber() == x) {
                    eventSeats.remove(seat);
                }
            }
        }

    }

    @Override
    public String toString() {
        return name;
    }

}
