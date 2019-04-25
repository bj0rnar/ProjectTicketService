package TicketService.Model;

import TicketService.DataAccess.IRepository;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Users.Organizer;
import TicketService.DataAccess.FakeDB;
import TicketService.Utility.InputValidator;
import TicketService.Utility.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * One eventhandler per organizer, same system as tickethandler.
 * Since events have DB interaction and local interaction, the local eventlist will be used to
 * store that particular organizers events, which will in turn make sure that organizers can't delete
 * or edit events that aren't theirs.
 */

public class EventHandler {
    //Static demo lists.
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static ObservableList<Event> eventListFX = FXCollections.observableArrayList();
    private IRepository repository;

    private Organizer organizer;

    public EventHandler(Organizer organizer){
        this.organizer = organizer;
        repository = new FakeDB();
    }

    public void createNewSeatedEvent(String name, Venue venue, LocalDate date, int ticketPrice) throws VenueHasNoSeatsException {
        if(InputValidator.validateSeatedEventInput(name, venue, date, ticketPrice)) {
            Event event = new Event(name, venue, date, ticketPrice, organizer);
            uploadEvents(event);
        }
        else{
            System.out.println("False input");
        }
    }
    public void createNewNoneSeatedEvent(String name, Venue venue, LocalDate date, int ticketPrice, int totalTickets){
        if(InputValidator.validateUnseatedEventInput(name, venue, date, ticketPrice, totalTickets)){
            Event event = new Event(name, venue, date, ticketPrice, organizer, totalTickets);
            uploadEvents(event);
        }
        else {
            System.out.println("False input");
        }
    }

    private void uploadEvents(Event event){
        repository.uploadEvent(event);
        organizer.getEvents().add(event);
    }

    public void createNewVenue(int numberOfSeats, String nameOfVenue, String venueAddress){
        Venue venue = new Venue(numberOfSeats, nameOfVenue, venueAddress);
        uploadVenues(venue);
    }

    private void uploadVenues(Venue venue) {
        organizer.getUserCreatedVenues().add(venue);
        repository.uploadVenue(venue);
    }

    public boolean deleteEvent(Event event){
        for(int x=0;x<organizer.getEvents().size();x++) {
            if(organizer.getEvents().get(x).equals(event)) {
                repository.deleteEvent(organizer.getEvents().get(x));
                organizer.getEvents().remove(organizer.getEvents().get(x));
                return true;
            }
        }
        return false;
    }

    public boolean deleteVenue(Venue venue){
        for(int x=0;x<organizer.getUserCreatedVenues().size();x++) {
            if(organizer.getUserCreatedVenues().get(x).equals(venue)) {
                repository.deleteVenue(organizer.getUserCreatedVenues().get(x));
                organizer.getUserCreatedVenues().remove(organizer.getUserCreatedVenues().get(x));
                return true;
            }
        }
        return false;
    }


    public boolean validateTicket(String s, Event e){
        return Validator.validateTicket(e, s);
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public static ArrayList<Event> getEventList() {
        return FakeDB.getEventList();
    }

}
