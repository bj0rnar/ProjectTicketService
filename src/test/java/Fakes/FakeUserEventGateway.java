package Fakes;

import TicketService.Gateway.UserEventGateway;
import TicketService.Model.Event;

import java.util.ArrayList;

public class FakeUserEventGateway extends UserEventGateway {
    private Event selectedEvent;


    public FakeUserEventGateway(Event event) {
        this.selectedEvent = event;
    }

    @Override
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    @Override
    public ArrayList<Event.Extra> getExtras() {
        return selectedEvent.getExtras();
    }

    @Override
    public boolean ticketAvailable() {
        if (selectedEvent.getRemainingTickets() > 0) {
            selectedEvent.setRemainingTickets(selectedEvent.getRemainingTickets() -1);
            return true;
        }

        return false;
    }

    @Override
    public int getTicketNumber() {
        return selectedEvent.getNumberOfTickets() - selectedEvent.getRemainingTickets() + 1;
    }

    @Override
    public String getEventId() {
        return selectedEvent.getEventId();
    }
}