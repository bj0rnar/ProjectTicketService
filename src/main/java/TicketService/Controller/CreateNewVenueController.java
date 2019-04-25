package TicketService.Controller;

import TicketService.Model.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CreateNewVenueController {
    private EventHandler eventHandler;
    @FXML
    Button createVenueButton;

    @FXML
    TextField venueNameText, venueTotalSeats, venueAddressText;


    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void createNewVenue(ActionEvent actionEvent) {
        eventHandler.createNewVenue(Integer.parseInt(venueTotalSeats.getText()), venueNameText.getText(), venueAddressText.getText());
        Stage dialogStage = (Stage)createVenueButton.getScene().getWindow();
        dialogStage.close();
    }

    public void backButton(ActionEvent actionEvent) {
        Stage dialogStage = (Stage)createVenueButton.getScene().getWindow();
        dialogStage.close();
    }
}
