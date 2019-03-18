package TicketService.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


/*
    TODO: Cancel reserved ticket, cancel/refund bought ticket
 */
public class Event {
    private int[] seats;

    private String eventId, venueId;
    private String eventTitle;
    private LocalDate eventStartDateAndTime;

    private int numberOfTickets = 0;
    private int remainingTickets;
    private double ticketPrice = 0.0;

    private ArrayList<Extra> extras;


    public Event(String eventId) {
        this.eventId = eventId;
    }

    //construct without ticket numbers.
   /* public Event(int eventId, String eventTitle, LocalDate eventStartDateAndTime, Venue venue, double ticketPrice) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventStartDateAndTime = eventStartDateAndTime;
        this.venue = venue;
        this.ticketPrice = ticketPrice;

        this.extras = new ArrayList<>();
    }*/

    /*public Event(String eventTitle, LocalDate eventStartDateAndTime, Venue venue, int numberOfTickets, double ticketPrice) {
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

        this.extras = new ArrayList<>();
    }*/


    //  TODO: Overload with method for events with seating/seat numbers
    /*public Ticket reserveTicketForEvent() {
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
    }*/

    //Just a lazy method for testing
    /*public Ticket reservePlz(){
        Ticket ticket;
        ticket = new Ticket(20, this);
        return ticket;
    }*/

    public void addExtra(String description, double price) {
        if (extras == null) {
            extras = new ArrayList<>();
        }

        extras.add(new Extra(price, description));
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setRemainingTickets(int remainingTickets) {
        this.remainingTickets = remainingTickets;
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

    /*public void registerSoldTicket(Ticket ticket) {
        if (ticket.isPaidFor()) {
            soldTickets[ticket.getTicketNumber()] = ticket;
        }
    }

    public void cancelTicket(Ticket ticket) {
        reservedTickets[ticket.getTicketNumber()] = null;
        remainingTickets++;
    }*/

    /*public void addExtra(double price, String name) {
        extras.add(new Extra(price, name));
    }*/

//region SETTERS
    public void setNumberOfSeats(int numberOfSeats) {
        this.seats = new int[numberOfSeats];
        Arrays.fill(seats, 0);
    }
//endregion

//region GETTERS
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getEventId() {
        return eventId;
    }

    /*public Venue getVenue() {
        return venue;
    }*/

    public String getEventTitle() {
        return eventTitle;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    /*public Ticket[] getReservedTickets() {
        return reservedTickets;
    }*/

    public LocalDate getEventStartDateAndTime() {
        return eventStartDateAndTime;
    }

    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public int[] getSeats() {
        return seats.clone();
    }
//endregion

    public class Extra {
        double price;
        String name;

        public Extra(double price, String name) {
            this.price = price;
            this.name = name;
        }
    }
}