package Fake;


import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import TicketService.Util.DatabaseGateway;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FakeDatabaseGateway extends DatabaseGateway {
    private HashMap<String, Event> events;
    //private HashMap<String, Venue> venues;
    private ArrayList<Organizer> organizers;


    public FakeDatabaseGateway() {
        events = new HashMap<>();
        organizers = new ArrayList<>();

        Venue.CreateVenues();

        Organizer org1 = new Organizer("1", "2", "3");
        Organizer org2 = new Organizer("1", "2", "3");
        Organizer org3 = new Organizer("1", "2", "3");

        organizers.add(org1);
        organizers.add(org2);
        organizers.add(org3);

        Event event1 = new Event("Test1", Venue.venues.get(0), LocalDate.of(2019, 12, 12), 50, true, org1);
        Event event2 = new Event("Test2", Venue.venues.get(0), LocalDate.of(2019, 12, 12), 50, true, org2);
        Event event3 = new Event("Test3", Venue.venues.get(2), LocalDate.of(2019, 12, 12), 50, false, org3);

        events.put(event1.getEventId(), event1);
        events.put(event2.getEventId(), event2);
        events.put(event3.getEventId(), event3);
    }

    /*@Override
    public HashMap<String, Venue> getAllVenues() {
        return venues;
    }*/

    @Override
    public ArrayList<Venue> getAllVenues() {
        return Venue.venues;
    }

    @Override
    public Venue getVenue(String venueId) {
        if (Venue.venues != null && Venue.venues.size() > 0) {
            Venue venue;

            for (int i = 0; i < Venue.venues.size(); i++) {
                venue = Venue.venues.get(i);
                if (venue.getVenueId().equals(venueId)) {
                    return venue;
                }
            }
        }

        return null;
    }

    @Override
    public boolean addVenue(Venue venue) {
        //venues.put(venue.getVenueId(), venue);
        Venue.venues.add(venue);
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