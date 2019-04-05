package TicketService.Controller;

import TicketService.Model.TicketHandler;

public class BuyTicketController {
    private TicketHandler ticketHandler;

    public BuyTicketController(TicketHandler ticketHandler) {

    }

    public void setTicketHandler(TicketHandler ticketHandler) {
        this.ticketHandler = ticketHandler;
    }
}
