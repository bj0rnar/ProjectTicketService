package TicketService.Model;

import java.util.ArrayList;

public class EventHandler {
    public static ArrayList<Event> eventList = new ArrayList<>();

    public static void CreateEvents() {
        if(eventList.size() == 0) {
            eventList.add(new Event("TG19", Venue.venues.get(0),true));
            eventList.add(new Event("EnSetersEvent", Venue.venues.get(1),true));
            eventList.add(new Event("Sopptur", Venue.venues.get(2),true));
        }
    }
}
