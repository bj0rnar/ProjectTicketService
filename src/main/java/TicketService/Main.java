package TicketService;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Venue.CreateVenues();
        EventHandler.CreateEvents();

        TicketHandler ticketHandler = new TicketHandler();

        Event asdasd = new Event("TG16", new Venue(123,"klj"), LocalDate.of(200,1,1), true);
        Event event = EventHandler.getEventList().stream()
                .filter(asd -> "TG19".equals(asd.getName()))
                .findAny()
                .orElse(null);


        System.out.println(event.getAreSeatsAvailable());
    }


    public static void StartBuyTicketProcess() {

    }
}