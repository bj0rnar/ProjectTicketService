package TicketService.Controller;

import TicketService.Users.Customer;
import javafx.fxml.FXML;

public class RegisterWindowController {

    public static ScreenController screenController;

    @FXML private javafx.scene.control.Button loginButton;
    @FXML javafx.scene.control.TextField usernameText, passwordText, emailText, firstNameText, lastNameText;


    @FXML
    private void initialize() {

    }

    public void logInUser() {
        try{
            MainWindowController.customer = new Customer(usernameText.getText(), passwordText.getText(),firstNameText.getText(), lastNameText.getText(), emailText.getText());
            screenController.active("Main");
        } catch (NullPointerException e) {
            System.out.println("A field was not filled.");
        }
    }
}
