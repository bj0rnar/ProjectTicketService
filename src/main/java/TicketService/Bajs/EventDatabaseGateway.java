package TicketService.Bajs;

import TicketService.Model.Event;
import TicketService.Model.Venue;

import java.util.HashMap;

/**
 * Should probably be turned into an interface or abstract class and mocked,
 * but this will do until I get a better handle on testing methodology.
 */
public class EventDatabaseGateway {
    private static HashMap<Integer, Event> events = new HashMap<>();
    private static HashMap<String, Venue> venues = new HashMap<>();


    public static boolean addEventToDatabase(Event event) {
        /*if (event == null || events.containsKey(event.getEventId()) || event.getEventTitle() == null ||
                event.getEventStartDateAndTime() == null || event.getNumberOfTickets() == 0 ||
                event.getVenue() == null || event.getTicketPrice() == 0.0) {
            return false;
        } else {
            events.put(Integer.valueOf(event.getEventId()), event);
            return true;
        }*/

        return true;
    }

    public static boolean addVenueToDatabase(Venue venue) {
        if (venue == null || venues.containsKey(venue.getVenueId()) || venue.getVenueName() == null) {
            return false;
        } else {
            venues.put(venue.getVenueId(), venue);
            return true;
        }
    }

    public static Event getEventFromDatabase(int eventId) {
        return events.getOrDefault(eventId, null);
    }

    public static Venue getvenueFromDatabase(String venueId) {
        return venues.getOrDefault(venueId, null);
    }

    /*public static boolean cancelTicket(int eventId, int ticketId) {
        Event event = getEventFromDatabase(eventId);
    }*/
}