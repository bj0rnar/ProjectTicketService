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
        //FakeDB.uploadedEvents.add(event);
    }

    public void createNewVenue(int numberOfSeats, String nameOfVenue){
        Venue venue = new Venue(numberOfSeats, nameOfVenue);
        uploadVenues(venue);
    }

    private void uploadVenues(Venue venue) {
        organizer.getUserCreatedVenues().add(venue);
        repository.uploadVenue(venue);
    }

    public void deleteEventFromDB(Event event){
        for(int x=0;x<organizer.getEvents().size();x++) {
            if(organizer.getEvents().get(x).equals(event)) {
                repository.deleteEvent(organizer.getEvents().get(x));
                organizer.getEvents().remove(organizer.getEvents().get(x));
            }
        }
    }

    public void deleteVenueFromDB(String name){
        Venue venue = selectVenue(name);
        if(venue != null){
                repository.deleteVenue(venue);
                organizer.getUserCreatedVenues().remove(venue);
            }
        else
            System.out.println("Found no venue in list");
    }

    public boolean validateTicket(String s, Event e){
        return Validator.validateTicket(e, s);
    }

    /**
     * Representation of onClick, remove once added to javaFX
     */
    private Venue selectVenue(String name){
        for(Venue v : organizer.getUserCreatedVenues()){
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public static ArrayList<Event> getEventList() {
        return FakeDB.getEventList();
    }

    public static ObservableList<Event> getEventListFX() {
        return FakeDB.getEventListFX();
    }
}
