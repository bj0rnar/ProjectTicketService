package TicketService.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventHandler {
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static ObservableList<Event> eventListFX = FXCollections.observableArrayList();

    public static void CreateEvents() {
        if(eventList.size() == 0) {
            eventList.add(new Event("TG19", Venue.venues.get(0), LocalDate.of(2019,3,21), true));
            eventList.add(new Event("EnSetersEvent", Venue.venues.get(1), LocalDate.of(2019,3,21),true));
            eventList.add(new Event("Sopptur", Venue.venues.get(2), LocalDate.of(2019,3,21),false));
        }
    }


    public static ArrayList<Event> getEventList() {
        if(eventList.isEmpty()) {
            CreateEvents();
        }
        return eventList;
    }
    public static ObservableList<Event> getEventListFX() {
        if(eventListFX.isEmpty()) {
            for(Event event : eventList) {
                eventListFX.add(event);
            }
        }
        return eventListFX;
    }
}
