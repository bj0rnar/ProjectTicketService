package TicketService.Controller;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrganizerMainWindowController {

    public static ScreenController screenController;

    @FXML
    private ListView<Event> eventListView;

    private ObservableList<Event> eventListFX = FXCollections.observableArrayList();
    public static Organizer organizer;

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

    public void setupEvents() {
        for(Event event : organizer.getEvents()) {
            addEventToFxList(event);
        }
        eventListView.setItems(eventListFX);
    }

    private void updateEventDetails(Event event) {
        eventNameText.setText(event.getName());
        VenueNameText.setText(event.getVenue().getName());
        EventDateText.setText(event.getDate().toString());
        TicketPriceText.setText(event.getTicketPrice() + ",-");
        seatsLeftStaticText.setText("Tickets left: ");
        EventTicketsLeftText.setText((String.valueOf(event.getAvailableTickets())));

    }

    public void addEventToFxList(Event event) {
        eventListFX.add(event);
    }

    public void CheckInnCustomers(MouseEvent mouseEvent) {
    }

    public void DeleteEvent(MouseEvent mouseEvent) {
    }

    public void CreateEvent(MouseEvent mouseEvent) {
    }

    public void LogOut(MouseEvent mouseEvent) {
    }
}
