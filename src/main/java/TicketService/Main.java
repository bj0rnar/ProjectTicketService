package TicketService;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.Venue;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Venue.CreateVenues();
        EventHandler.CreateEvents();


        ChooseOptions();
    }

    public static void ChooseOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an option.");
        System.out.print("ID: ");
        try {
            switch(scanner.nextInt()) {
                case 0:
                    StartBuyTicketProcess();
                    break;
                case 1:
                    // code block
                    break;
                default:
                    // code block
            }
        } catch (InputMismatchException e) {
            ChooseOptions();
        }
    }

    public static void StartBuyTicketProcess() {
        /*
            1. Select Event
            2. Buy ticket
        */
    }
}