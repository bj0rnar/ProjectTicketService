package TicketService.Utility;

import TicketService.Model.Event;
import TicketService.Model.Ticket;

public class Validator {
    public static boolean validateTicket(Event e, String t){
        for(String x : e.getVerificationCodeList()){
            if(x.equals(t)){
                return true;
            }
        }
        return false;
    }
}
