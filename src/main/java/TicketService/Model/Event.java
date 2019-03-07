package TicketService.Model;

import TicketService.ExternalService.EventDatabaseGateway;

import java.time.LocalDate;
import java.util.Arrays;


/*
    TODO: Cancel reserved ticket, cancel/refund bought ticket
 */
public class Event {
    private static int eventIdCounter = 0;

    //private HashMap<String, Ticket> soldTickets;    // <ticketNumber, Ticket>
    private Ticket[] soldTickets;
    private Ticket[] reservedTickets;
    private int[] seats;

    private int eventId;
    private String eventTitle;
    private LocalDate eventStartDateAndTime;
    private Venue venue;

    private String OptionAvalible;

    private int numberOfTickets = 0;
    private int remainingTickets;
    private double ticketPrice = 0.0;

    //construct without ticket numbers.
    public Event(int eventId, String eventTitle, LocalDate eventStartDateAndTime, Venue venue, double ticketPrice) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventStartDateAndTime = eventStartDateAndTime;
        this.venue = venue;
        this.ticketPrice = ticketPrice;

        this.numberOfTickets = venue.getSeats().length;
        this.remainingTickets = venue.getSeats().length;

        this.soldTickets = new Ticket[numberOfTickets];
        this.reservedTickets = new Ticket[numberOfTickets];
    }

    public Event(String eventTitle, LocalDate eventStartDateAndTime, Venue venue, int numberOfTickets, double ticketPrice) {
        eventIdCounter++;

        this.eventId = eventIdCounter;
        this.eventTitle = eventTitle;
        this.eventStartDateAndTime = eventStartDateAndTime;
        this.numberOfTickets = numberOfTickets;
        this.remainingTickets = numberOfTickets;
        this.venue = venue;
        this.ticketPrice = ticketPrice;

        this.soldTickets = new Ticket[numberOfTickets];
        this.reservedTickets = new Ticket[numberOfTickets];
    }


    //  TODO: Overload with method for events with seating/seat numbers
    public Ticket reserveTicketForEvent() {
        Ticket ticket;

        for (int i = 0; i < soldTickets.length; i++) {
            if (soldTickets[i] == null && reservedTickets[i] == null) {
                ticket = new Ticket(i, this);
                reservedTickets[i] = ticket;
                remainingTickets--;
                return ticket;
            }
        }

        return null;
    }

    //With seatnumbers.
    public Ticket reserveTicketForEventWithSeating(int seatNumber){
        Ticket ticket;

        for (int i = 0; i < soldTickets.length; i++) {
            if (soldTickets[i] == null && reservedTickets[i] == null) {
                ticket = new Ticket(i, this);
                reservedTickets[i] = ticket;
                remainingTickets--;
                this.venue.seatReservation(seatNumber, ticket);

                return ticket;
            }
        }
        return null;
    }


    public boolean reserveSeat(int seatNumber, Ticket ticket) {
        if (seats != null && seats[seatNumber] == 0) {
            seats[seatNumber] = 1;      // Need better system for reserving seats
            ticket.setSeatNumber(seatNumber);
            return true;
        } else {
            return false;
        }
    }

    public void registerSoldTicket(Ticket ticket) {
        if (ticket.isPaidFor()) {
            soldTickets[ticket.getTicketNumber()] = ticket;
        }
    }

    public void cancelTicket(Ticket ticket) {
        reservedTickets[ticket.getTicketNumber()] = null;
        remainingTickets++;
    }

//region SETTERS
    public void setNumberOfSeats(int numberOfSeats) {
        this.seats = new int[numberOfSeats];
        Arrays.fill(seats, 0);
    }

    public void setOptionAvalible(String option){
        this.OptionAvalible = option;
    }
//endregion

//region GETTERS
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getEventId() {
        return eventId;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Ticket[] getReservedTickets() {
        return reservedTickets;
    }

    public LocalDate getEventStartDateAndTime() {
        return eventStartDateAndTime;
    }

    public int[] getSeats() {
        return seats.clone();
    }

    public String getOptionAvalible() {
        return OptionAvalible;
    }

    //endregion
}