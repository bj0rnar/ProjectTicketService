package TicketService.Gateway;

import TicketService.Model.Event;

import java.util.ArrayList;

public class UserEventGateway extends EventGateway {
    private Event selectedEvent;


    public UserEventGateway() {
    }

    public UserEventGateway(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }


    public ArrayList<Event.Extra> getExtras() {
        return selectedEvent.getExtras();
    }

    public boolean ticketAvailable() {
        return false;
    }

    public int getTicketNumber() {
        return 0;
    }

    public String getEventId() {
        return selectedEvent.getEventId();
    }
}