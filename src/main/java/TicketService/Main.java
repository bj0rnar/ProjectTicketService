package TicketService;

import TicketService.DataAccess.DataContext;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        DataContext.CreateVenues();
        DataContext.CreateEvents();

        Event event = EventHandler.getEventList().stream()
                .filter(asd -> "TG19".equals(asd.getName()))
                .findAny()
                .orElse(null);


        System.out.println(event.getAreSeatsAvailable());
    }


    public static void StartBuyTicketProcess() {

    }
}