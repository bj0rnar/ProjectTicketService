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

    @FXML


    public void registerUser() {
        try{
            ShopWindowController.customer = new Customer(usernameText.getText(), passwordText.getText(),firstNameText.getText(), lastNameText.getText(), emailText.getText());

        } catch (NullPointerException e) {
            System.out.println(getClass() + ": A field was not filled.");
        }
        if(ShopWindowController.customer != null) {
            try {
                screenController.active("Main");
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println(getClass() + ": A field was not filled.");
        }
    }
}
