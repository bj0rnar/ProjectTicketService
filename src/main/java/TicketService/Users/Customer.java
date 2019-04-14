package TicketService.Users;

import TicketService.Model.Ticket;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Ticket> TicketList = new ArrayList<>();
    private ArrayList<String> receiptList = new ArrayList<>();

    public Customer(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
    }

    public ArrayList<Ticket> getTicketList() {
        return TicketList;
    }

    public ArrayList<String> getReceiptList() { return receiptList; }
}
