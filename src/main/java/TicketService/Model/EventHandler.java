package TicketService.Model;

import TicketService.DataAccess.DataContext;
import TicketService.DataAccess.IRepository;
import TicketService.Users.Organizer;
import TicketService.DataAccess.FakeDB;
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

    public void createNewEvent(String name, Venue venue, LocalDate date, int ticketPrice , Boolean areSeatsAvailable){
        Event event = new Event(name, venue, date, ticketPrice, areSeatsAvailable, organizer);
        uploadEvents(event);
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

    public void deleteEventFromDB(String name){
        Event event = SelectEvent(name);
        if(event != null) {
            repository.deleteEvent(event);
            organizer.getEvents().remove(event);
        }
        else
            System.out.println("That's not yours to delete");
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

    public boolean validateTicket(Ticket t, Event e){
        return Validator.validateTicket(e, t);
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


    private Event SelectEvent(String name) {
        for(Event e : organizer.getEvents()){
            System.out.println(e.getName() + " " +  e.getDate() + " " + e.getVenue().getName());
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public static ArrayList<Event> getEventList() {
        return DataContext.getEventList();
    }

    public static ObservableList<Event> getEventListFX() {
        return DataContext.getEventListFX();
    }
}
