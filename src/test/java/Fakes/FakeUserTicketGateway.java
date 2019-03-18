package Fakes;

import TicketService.Model.Ticket;

public class FakeUserTicketGateway {
    private Ticket ticket;


    public FakeUserTicketGateway(String eventId, int ticketNumber) {
        this.ticket = new Ticket(eventId, ticketNumber);
    }

    public Ticket getTicket() {
        return ticket;
    }
}