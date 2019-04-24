package TicketService;

import TicketService.DataAccess.FakeDB;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;

public class Main {

    public static void main(String[] args){
        FakeDB.CreateVenues();
        try {
            FakeDB.CreateEvents();
        } catch (VenueHasNoSeatsException e) {
            e.printStackTrace();
        }

        Event event = EventHandler.getEventList().stream()
                .filter(asd -> "TG19".equals(asd.getName()))
                .findAny()
                .orElse(null);


        System.out.println(event.getAreSeatsAvailable());
    }


    public static void StartBuyTicketProcess() {

    }


}