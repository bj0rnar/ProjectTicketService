package TicketService.Utility;

import TicketService.Model.Event;
import TicketService.Model.Ticket;

public class Validator {
    public static boolean validateTicket(Event e, Ticket t){
        for(String x : e.getVerificationCodeList()){
            if(x.equals(t.getVerificationCode())){
                return true;
            }
        }
        return false;
    }
}
