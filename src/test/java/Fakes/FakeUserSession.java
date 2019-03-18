package Fakes;

import TicketService.ExternalService.DatabaseGateway;
import TicketService.Gateway.UserEventGateway;
import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.UserSession;

import java.util.ArrayList;

public class FakeUserSession extends UserSession {
    private UserEventGateway eventGateway;
    public DatabaseGateway db;
    public FakeUserTicketGateway ticketGateway;

    public FakeUserSession() {
        db = new FakeDatabaseGateway();
    }

    @Override
    public void setSelectedEvent(String eventId) {
        eventGateway = new FakeUserEventGateway(db.getEvent(eventId));
    }

    @Override
    public Event getSelectedEvent() {
        return eventGateway.getSelectedEvent();
    }

    public ArrayList<Event.Extra> listExtras() {
        if (eventGateway != null) {
            return eventGateway.getExtras();
        } else {
            return null;
        }
    }

    public void reserveTicketForEvent() {
        if (eventGateway == null || eventGateway.getSelectedEvent() == null || !eventGateway.ticketAvailable()) {
            return;
        }

        ticketGateway = new FakeUserTicketGateway(eventGateway.getEventId(), eventGateway.getTicketNumber());
    }

    public Ticket getTicket() {
        return ticketGateway.getTicket();
    }


}