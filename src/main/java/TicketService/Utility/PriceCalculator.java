package TicketService.Utility;

import TicketService.Model.Ticket;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.T;


public class PriceCalculator {

    public static int summarizePrice(ArrayList<Ticket> ticketList){

        int result = 0;

        for(Ticket t : ticketList){
             result += t.getPrice();
        }

        return result;
    }
}
