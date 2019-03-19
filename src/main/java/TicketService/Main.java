package TicketService;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Venue.CreateVenues();
        EventHandler.CreateEvents();



        Event event = EventHandler.getEventList().stream()
                .filter(asd -> "TG19".equals(asd.getName()))
                .findAny()
                .orElse(null);


        System.out.println(event.getAreSeatsAvailable());
    }


    public static void StartBuyTicketProcess() {

    }
}