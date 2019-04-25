package TicketService.DataAccess;

import TicketService.Model.Event;
import TicketService.Model.Venue;
import TicketService.Users.User;

import java.util.ArrayList;

public interface IRepository {
    boolean uploadUser(User e);
    ArrayList<User> getUsers();
    void uploadEvent(Event e);
    void deleteEvent(Event e);
    ArrayList<Event> getEvents();
    void uploadVenue(Venue v);
    void deleteVenue(Venue v);
    ArrayList<Venue> getVenues();
}
