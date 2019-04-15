package TicketService.DataAccess;

import TicketService.Model.Event;
import TicketService.Model.Venue;

public interface IRepository {
    void uploadEvent(Event e);
    void deleteEvent(Event e);
    void uploadVenue(Venue v);
    void deleteVenue(Venue v);
}
