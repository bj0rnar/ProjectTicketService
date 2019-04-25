package TicketService.Controller;

import TicketService.DataAccess.FakeDB;
import TicketService.DataAccess.IRepository;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewEventController {

    @FXML
    TextField eventNameText, eventTotalTickets, eventTicketPrice;

    ObservableList<Venue> venueListFX = FXCollections.observableArrayList();
    EventHandler eventHandler;

    @FXML
    Text totalTicketText;

    @FXML
    ChoiceBox<Venue> venueChoice;

    @FXML
    DatePicker eventStartDate;

    @FXML
    Button createEventButton, createNewVenueButton;

    @FXML
    public void initialize() {
        venueChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Venue>() {
            @Override
            public void changed(ObservableValue<? extends Venue> observableValue, Venue venue, Venue t1) {
                if(t1 != null) {
                    if (t1.getSeats().size() > 0) {
                        totalTicketText.setVisible(false);
                        eventTotalTickets.setVisible(false);
                        eventTotalTickets.setText("");
                    } else {
                        totalTicketText.setVisible(true);
                        eventTotalTickets.setVisible(true);
                    }
                }
            }
        });

    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        updateVenueChoices();
    }

    public void createNewVenue(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/CreateNewVenueWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create new Venue");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(createEventButton.getScene().getWindow());
            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);
            CreateNewVenueController createNewVenueController = fxmlLoader.getController();
            createNewVenueController.setEventHandler(eventHandler);
            dialogStage.showAndWait();
            updateVenueChoices();
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    private void updateVenueChoices() {
        IRepository repo = new FakeDB();
        venueListFX.clear();
        venueListFX.addAll(repo.getVenues());
        venueChoice.setItems(venueListFX);
    }

    public void createNewEvent(ActionEvent actionEvent) {
        if(eventTotalTickets.isVisible()) {
            eventHandler.createNewNoneSeatedEvent(
                    eventNameText.getText(),
                    venueChoice.getSelectionModel().getSelectedItem(),
                    eventStartDate.getValue(),
                    Integer.parseInt(eventTicketPrice.getText()),
                    Integer.parseInt(eventTotalTickets.getText()));
        } else {
            try {
                eventHandler.createNewSeatedEvent(
                        eventNameText.getText(),
                        venueChoice.getSelectionModel().getSelectedItem(),
                        eventStartDate.getValue(),
                        Integer.parseInt(eventTicketPrice.getText()));
            } catch (VenueHasNoSeatsException e) {
                e.printStackTrace();
            }
        }
        Stage dialogStage = (Stage)createEventButton.getScene().getWindow();
        dialogStage.close();
    }

    public void backButton(ActionEvent actionEvent) {
        Stage dialogStage = (Stage)createEventButton.getScene().getWindow();
        dialogStage.close();
    }
}
