package TicketService.Users;

import TicketService.Model.Event;
import TicketService.Model.Venue;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organizer extends User {

    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Venue> userCreatedVenues = new ArrayList<>();

    public Organizer(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
    }



    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Venue> getUserCreatedVenues() { return userCreatedVenues; }
}
