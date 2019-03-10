package TicketService.Model;

import java.util.ArrayList;

public class EventHandler {
    public static ArrayList<Event> eventList = new ArrayList<>();

    public static void CreateEvents() {
        eventList.add(new Event("TG19"));
        eventList.add(new Event("Qlimax"));
        eventList.add(new Event("Sopptur"));
    }
}
