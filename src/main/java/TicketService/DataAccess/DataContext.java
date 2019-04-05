package TicketService.DataAccess;

import TicketService.Model.Event;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataContext {

    private static ArrayList<Event> eventList = new ArrayList<>();
    private static ArrayList<Venue> venues = new ArrayList<>();
    private static ObservableList<Event> eventListFX = FXCollections.observableArrayList();

    public static void CreateEvents() {

        if(eventList.size() == 0) {
            eventList.add(new Event("TG19", Venue.getVenues().get(0), LocalDate.of(2019,3,21), 100, true, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
            eventList.add(new Event("EnSetersEvent", Venue.getVenues().get(1), LocalDate.of(2019,3,21), 100,true, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
            eventList.add(new Event("Sopptur", Venue.getVenues().get(2), LocalDate.of(2019,3,21), 100,false, new Organizer("Eddy", "Normann","eventMaker@mail.com")));
        }
    }

    public static void CreateVenues() {
        venues.add(new Venue(100, "Hall 1"));
        venues.add(new Venue(1, "Hall 2"));
        venues.add(new Venue(0,"Skogen"));

    }

    public static ArrayList<Event> getEventList() {
        if(eventList.isEmpty()) {
            CreateEvents();
        }
        return eventList;
    }

    public static ObservableList<Event> getEventListFX() {
        eventListFX.clear();
        for(Event event : getEventList()) {
            eventListFX.add(event);
        }
        return eventListFX;
    }

    public static ArrayList<Venue> getVenues() {
        if(venues.size() == 0) {
            CreateVenues();
        }
        return venues;
    }
}
