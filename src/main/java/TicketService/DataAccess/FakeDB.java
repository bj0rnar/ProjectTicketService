package TicketService.DataAccess;

import TicketService.Model.Event;
import TicketService.Model.Venue;

import java.util.ArrayList;

public class FakeDB implements IRepository{

    public static final ArrayList<Event> uploadedEvents = new ArrayList<>();
    public static final ArrayList<Venue> officialVenueList = new ArrayList<>();

    @Override
    public void uploadEvent(Event e) {
        uploadedEvents.add(e);
    }

    @Override
    public void deleteEvent(Event e) {
        uploadedEvents.remove(e);
    }

    @Override
    public void uploadVenue(Venue v) {
        officialVenueList.add(v);
    }

    @Override
    public void deleteVenue(Venue v) {
        officialVenueList.remove(v);
    }
}
