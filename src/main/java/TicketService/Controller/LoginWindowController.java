package TicketService.Controller;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Users.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginWindowController {

    public static ScreenController screenController;

    @FXML private Button loginButton;
    @FXML TextField usernameText, passwordText,firstNameText,lastNameText,emailText;


    @FXML
    private void initialize() {

    }




    public void logInUser() {
        try{
            MainWindowController.customer = new Customer(usernameText.getText(),passwordText.getText(),firstNameText.getText(), lastNameText.getText(), emailText.getText());
            screenController.active("Main");
        } catch (NullPointerException e) {
            System.out.println("A field was not filled.");
        }
    }
}
