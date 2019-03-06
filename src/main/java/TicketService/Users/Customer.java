package TicketService.Users;

import TicketService.Model.Ticket;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Ticket> TicketList = new ArrayList<>();


    public ArrayList<Ticket> getTicketList() {
        return TicketList;
    }
}
