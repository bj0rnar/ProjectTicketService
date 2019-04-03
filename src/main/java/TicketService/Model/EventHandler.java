package TicketService.Model;

import TicketService.DataAccess.DataContext;
import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventHandler {

    public static ArrayList<Event> getEventList() {
        return DataContext.getEventList();
    }

    public static ObservableList<Event> getEventListFX() {
        return DataContext.getEventListFX();
    }
}
