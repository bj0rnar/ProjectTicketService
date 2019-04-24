package TicketService.Controller;

import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OrganizerMainWindowController {

    public static ScreenController screenController;

    @FXML
    private ListView<Event> eventListView;

    private ObservableList<Event> eventListFX = FXCollections.observableArrayList();
    private Organizer organizer;
    private EventHandler eventHandler;

    @FXML
    private Text eventNameText, VenueNameText, EventDateText, seatsLeftStaticText, TicketPriceText, EventTicketsLeftText;

    @FXML
    private Button a;

    @FXML
    private void initialize() {
        eventListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldEvent, newEvent) -> {
            if (newEvent != null)
                updateEventDetails(newEvent);
        });

    }

    public void setupController(Organizer organizer) {
        this.organizer = organizer;
        this.eventHandler = new EventHandler(organizer);

        /*DUMMY DATA*/
        eventHandler.createNewVenue(100, "My custom venue");
        try {
            eventHandler.createNewSeatedEvent("My custom event", organizer.getUserCreatedVenues().get(0), LocalDate.now(), 150);
        } catch (VenueHasNoSeatsException e) {
            e.printStackTrace();
        }
        /*DUMMY DATA*/

        for(Event event : organizer.getEvents()) {
            addEventToFxList(event);
        }
        eventListView.setItems(eventListFX);
    }

    private void updateEventDetails(Event event) {
        if(event != null) {
            eventNameText.setText(event.getName());
            VenueNameText.setText(event.getVenue().getName());
            EventDateText.setText(event.getDate().toString());
            TicketPriceText.setText(event.getTicketPrice() + ",- ");
            seatsLeftStaticText.setText("Tickets left: ");
            EventTicketsLeftText.setText((String.valueOf(event.getAvailableTickets())));
        } else {
            eventNameText.setText("");
            VenueNameText.setText("");
            EventDateText.setText("");
            TicketPriceText.setText("");
            seatsLeftStaticText.setText("");
            EventTicketsLeftText.setText("");
        }

    }

    public void addEventToFxList(Event event) {
        eventListFX.add(event);
    }

    public void CheckInnCustomers(MouseEvent mouseEvent) {

    }

    public void DeleteEvent(MouseEvent mouseEvent) {
        Event event = eventListView.getSelectionModel().getSelectedItem();
        if(event != null)
            eventHandler.deleteEventFromDB(event);
        eventListFX.remove(event);
        updateEventDetails(null);
    }

    public void CreateEvent(MouseEvent mouseEvent) {
    }

    public void LogOut(MouseEvent mouseEvent) {
    }
}
