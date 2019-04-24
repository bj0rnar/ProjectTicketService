package TicketService.DataAccess;

import TicketService.Model.Event;
import TicketService.Model.Venue;

import java.util.ArrayList;

public interface IRepository {
    void uploadEvent(Event e);
    void deleteEvent(Event e);
    ArrayList<Event> getEvents();
    void uploadVenue(Venue v);
    void deleteVenue(Venue v);
    ArrayList<Venue> getVenues();
}
