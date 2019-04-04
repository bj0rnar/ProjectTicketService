package TicketService.Model;

import TicketService.DataAccess.DataContext;
import TicketService.Users.Organizer;
import TicketService.Utility.FakeDB;
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

    private Organizer organizer;

    public EventHandler(Organizer organizer){
        this.organizer = organizer;
    }

    public void createNewEvent(String name, Venue venue, LocalDate date, int ticketPrice , Boolean areSeatsAvailable){
        Event event = new Event(name, venue, date, ticketPrice, areSeatsAvailable, organizer);
       // organizerEventList.add(event);
       // FakeDB.uploadedEvents.add(event);
        upload(event);
    }

    public void upload(Event event){
        organizer.getEvents().add(event);
        FakeDB.uploadedEvents.add(event);
    }

    public void removeArrangementFromDB(String name){
        Event event = SelectEvent(name);
        if(event != null) {
            FakeDB.uploadedEvents.remove(event);
            organizer.getEvents().remove(event);
        }
        else
            System.out.println("That's not yours to delete");
    }

    /**
     * Representation of onClick, remove once added to javaFX
     */
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
