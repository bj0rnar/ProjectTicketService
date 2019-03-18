package TicketService.Gateway;

import TicketService.Model.Event;
import TicketService.Model.Venue;
import java.time.LocalDate;
import java.util.ArrayList;


public class EventAdministrationGateway {


    public Event createBasicEvent(String eventId, String eventTitle, String venueId, int numberOfTickets, double ticketPrice) {
        Event result = new Event(eventId);

        result.setEventTitle(eventTitle);
        result.setVenueId(venueId);
        result.setNumberOfSeats(numberOfTickets);
        result.setRemainingTickets(numberOfTickets);
        result.setTicketPrice(ticketPrice);

        return result;
    }

    public void addExtra(Event event, String description, double price) {
        event.addExtra(description, price);
    }

    /*public ArrayList<Event.Extra> getExtras() {

    }*/
}