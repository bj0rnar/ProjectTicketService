package TicketService.Util;

import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class DatabaseGateway {
    /*
        Replace with generics?
     */

    public abstract ArrayList<Venue> getAllVenues();
    public abstract Venue getVenue(String venueId);
    public abstract boolean addVenue(Venue venue);

    public abstract HashMap<String, Event> getAllEvents();
    public abstract Event getEvent(String eventId);
    public abstract boolean addEvent(Event event);

    public abstract HashMap<String, Ticket> getTicketsForEvent(String eventId);
    public abstract Ticket addTicket(Ticket ticket);
    public abstract Ticket updateTicket(Ticket ticket);
    public abstract Ticket getTicket(String eventId, int ticketNumber);
    public abstract Ticket removeTicket(String eventId, int ticketNumber);
}