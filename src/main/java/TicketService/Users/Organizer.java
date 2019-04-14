package TicketService.Users;

import TicketService.Model.Event;
import TicketService.Model.Venue;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organizer extends User {

    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Venue> userCreatedVenues = new ArrayList<>();

    public Organizer(String firstname, String lastname, String email) {
        super(firstname, lastname, email);
    }

    //This should be the only "legal" way to create an event. To force the event to be create by an organizer. (Wouldnt make sense otherwise?)
    public void createEvent(String name, Venue venue, LocalDate date, int ticketPrice , Boolean areSeatsAvailable) {
        events.add(new Event(name, venue, date, ticketPrice, areSeatsAvailable, this));
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Venue> getUserCreatedVenues() { return userCreatedVenues; }
}
