package TicketService.Utility;

import TicketService.Model.Venue;

import java.time.LocalDate;

public class InputValidator {
    public static boolean validateSeatedEventInput(String name, Venue venue, LocalDate date, int ticketPrice){
        if(name.equals("")){
            return false;
        }
        if(venue == null){
            return false;
        }
        if(date.isBefore(LocalDate.now())){
            return false;
        }
        if(ticketPrice == 0){
            return false;
        }
        return true;
    }

    public static boolean validateUnseatedEventInput(String name, Venue venue, LocalDate date, int ticketPrice, int totalPrice){
        if(name.equals("")){
            return false;
        }
        if(venue == null){
            return false;
        }
        if(date.isBefore(LocalDate.now())){
            return false;
        }
        if(ticketPrice == 0){
            return false;
        }
        if(totalPrice == 0){
            return false;
        }
        return true;
    }
}
