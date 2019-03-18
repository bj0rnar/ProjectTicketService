package Fakes;

import TicketService.ExternalService.DatabaseGateway;
import TicketService.Gateway.AdminEventGateway;
import TicketService.Gateway.VenueAdministrationGateway;
import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;

import java.util.HashMap;

public class FakeDatabaseGateway extends DatabaseGateway {
    private HashMap<String, Event> events;
    private HashMap<String, Venue> venues;


    public FakeDatabaseGateway() {
        events = new HashMap<>();
        venues = new HashMap<>();

        VenueAdministrationGateway vag = new VenueAdministrationGateway();
        AdminEventGateway aeg = new AdminEventGateway();

        addVenue(vag.createUnseatedVenue("1", "Venue 1"));
        addVenue(vag.createUnseatedVenue("2", "Venue 2"));

        addEvent(aeg.createBasicEvent("1", "Test1", "1", 50, 100.0));
        addEvent(aeg.createBasicEvent("2", "Test2", "1", 20, 50.0));
        addEvent(aeg.createBasicEvent("3", "Test3", "2", 60, 300.0));

        Event event = aeg.createBasicEvent("4", "Test4", "2", 50, 100.0);

        aeg.addExtra(event, "Stuff", 9001.00);
        aeg.addExtra(event, "Other stuff", 2.00);

        addEvent(event);
    }

    @Override
    public HashMap<String, Venue> getAllVenues() {
        return venues;
    }

    @Override
    public Venue getVenue(String venueId) {
        return venues.get(venueId);
    }

    @Override
    public boolean addVenue(Venue venue) {
        venues.put(venue.getVenueId(), venue);
        return true;
    }

    @Override
    public HashMap<String, Event> getAllEvents() {
        return events;
    }

    @Override
    public Event getEvent(String eventId) {
        return events.get(eventId);
    }

    @Override
    public boolean addEvent(Event event) {
        events.put(event.getEventId(), event);
        return true;
    }

    @Override
    public HashMap<String, Ticket> getTicketsForEvent(String eventId) {
        return null;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket getTicket(String eventId, int ticketNumber) {
        return null;
    }

    @Override
    public Ticket removeTicket(String eventId, int ticketNumber) {
        return null;
    }
}
