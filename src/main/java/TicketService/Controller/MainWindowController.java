package TicketService.Controller;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;

public class MainWindowController {

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private Text eventNameText, VenueNameText, EventDateText, SeatsLeftEventText, seatsLeftStaticText;

    @FXML
    private void initialize() {
        eventListView.setItems(EventHandler.getEventListFX());
        updateEventDetails(eventListView.getItems().get(0));

        eventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldEvent, Event newEvent) {
                updateEventDetails(newEvent);
            }
        });
    }

    private void updateEventDetails(Event event) {
        eventNameText.setText(event.getName());
        VenueNameText.setText(event.getVenue().getName());
        EventDateText.setText(event.getDate().toString());
        if(event.getAreSeatsAvailable()) {
            seatsLeftStaticText.setText("Seats left: ");
            SeatsLeftEventText.setText(event.getVenue().getSeats().size() + ".");
        } else {
            SeatsLeftEventText.setText("");
            seatsLeftStaticText.setText("");
        }
    }
}
