package TicketService.Utility;

import TicketService.Model.Ticket;
import TicketService.Users.Customer;

import java.util.ArrayList;

public class ReceiptMaker {
    public static String addToReceipt(Ticket t, Customer c){
        StringBuilder s = new StringBuilder();
        s.append("Customer: " + c.getFirstname() + " " + c.getLastname() + "\n");
        s.append(t.getEvent().getName() + " " + t.getEvent().getDate() + "\n");
        if(t.getEvent().getVenue() != null) {
            s.append(t.getEvent().getVenue().getName() + "\n");
        } else {
            s.append(t.getEvent().getVenue().getName() + " Seat: " + t.getSeat().getSeatNumber() + "\n");
        }
        s.append("Price: " + t.getPrice());

        return s.toString();
    }

    //Printing to console as temp solution
    public static void printAllTickets(ArrayList<String> receipts, int cost) {
        for(String t : receipts){
            System.out.println(t + "\n");
        }

        System.out.println("Total Cost: " + cost);
    }
}
