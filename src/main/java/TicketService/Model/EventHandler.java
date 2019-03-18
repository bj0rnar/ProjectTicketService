package TicketService.Model;

import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventHandler {
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static ObservableList<Event> eventListFX = FXCollections.observableArrayList();

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
        if(eventList.isEmpty()) {
            CreateEvents();
        }
        return eventList;
    }

    public static ObservableList<Event> getEventListFX() {
        if(eventListFX.size() != eventList.size()) {
            if(eventList.size() > 0) {
                //Clears list, prevents duplicates.
                eventListFX = FXCollections.observableArrayList();
                for(Event event : eventList) {
                    eventListFX.add(event);
                }
            }
        }
        return eventListFX;
    }
}
