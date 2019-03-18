package TicketService;

import TicketService.ExternalService.DatabaseGateway;
import TicketService.Gateway.TicketGateway;
import TicketService.Gateway.UserEventGateway;
import TicketService.Gateway.UserTicketGateway;
import TicketService.Model.Event;
import TicketService.Model.Ticket;

public class UserSession {
    private UserEventGateway eventGateway;
    private DatabaseGateway db;



    private Event selectedEvent;
    private Ticket reservedTicket;
    private TicketGateway ticketGateway = new UserTicketGateway();

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    /*public void setSelectedEvent(Event selectedEvent) {
        if (selectedEvent != null) {
            this.selectedEvent = selectedEvent;
            reserveTicketWhenEventIsSelected();
        } else {
            //throw new NullPointerException();
        }
    }*/

    public void setSelectedEvent(String eventId) {

    }

    public void reserveTicketWhenEventIsSelected() {

    }
}