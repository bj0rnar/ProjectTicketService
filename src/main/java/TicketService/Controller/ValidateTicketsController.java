package TicketService.Controller;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Utility.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ValidateTicketsController {

    private Event event;
    private EventHandler eventHandler;

    @FXML
    Text StatusText,dummyCodeText;

    @FXML
    Button CheckInButton, BackButton;

    @FXML
    TextField codeTextField;

    @FXML
    private void initialize() {
        StatusText.setText("");
        CheckInButton.setDisable(false);
        codeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.equals("")) {
                CheckInButton.setDisable(false);
            }
             else {
                 CheckInButton.setDisable(true);
            }
        });
    }

    public void setEvent(Event event, EventHandler eventHandler) {
        this.event = event;
        this.eventHandler = eventHandler;
        dummyCodeText.setText(event.getVerificationCodeList().size() > 0 ? event.getVerificationCodeList().get(0) : "NO TICKETS BOUGHT");
        codeTextField.setText(event.getVerificationCodeList().size() > 0 ? event.getVerificationCodeList().get(0) : "");
    }

    public void ValidateTicket(ActionEvent actionEvent) {
        String valiCode = codeTextField.getText();
        if(eventHandler.validateTicket(valiCode,event)) {
            StatusText.setText("Success!");
        } else {
            StatusText.setText("Failed...");
        }
    }

    public void CloseWindow(ActionEvent actionEvent) {
        Stage dialogStage = (Stage)BackButton.getScene().getWindow();
        dialogStage.close();
    }
}
