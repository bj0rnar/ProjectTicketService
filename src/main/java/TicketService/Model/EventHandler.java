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

    //Organizer private list
    private ArrayList<Event> organizerEventList = new ArrayList<>();

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
        organizerEventList.add(event);
        FakeDB.uploadedEvents.add(event);
    }

    public void removeArrangementFromDB(String name){
        Event event = SelectEvent(name);
        if(event != null) {
            FakeDB.uploadedEvents.remove(event);
            organizerEventList.remove(event);
        }
        else
            System.out.println("That's not yours to delete");
    }

    /**
     * Representation of onClick, remove once added to javaFX
     */
    private Event SelectEvent(String name) {
        for(Event e : organizerEventList){
            System.out.println(e.getName() + " " +  e.getDate() + " " + e.getVenue().getName());
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public ArrayList<Event> getOrganizerEventList() {
        return organizerEventList;
    }

    //Just moved this further down.
    public static void CreateEvents() {
        if(Venue.venues.size() == 0) {
            Venue.CreateVenues();
        }
        if(eventList.size() == 0) {
            eventList.add(new Event("TG19", Venue.venues.get(0), LocalDate.of(2019,3,21), 100, true, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
            eventList.add(new Event("EnSetersEvent", Venue.venues.get(1), LocalDate.of(2019,3,21), 100,true, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
            eventList.add(new Event("Sopptur", Venue.venues.get(2), LocalDate.of(2019,3,21), 100,false, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
        }
    }


    public static ArrayList<Event> getEventList() {
        return DataContext.getEventList();
    }

    public static ObservableList<Event> getEventListFX() {
        return DataContext.getEventListFX();
    }
}
