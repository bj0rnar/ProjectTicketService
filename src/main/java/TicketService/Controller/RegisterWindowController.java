package TicketService.Controller;

import TicketService.Users.Customer;
import com.sun.jdi.InvocationException;
import javafx.fxml.FXML;

import java.lang.reflect.InvocationTargetException;

public class RegisterWindowController {

    public static ScreenController screenController;

    @FXML private javafx.scene.control.Button loginButton;
    @FXML javafx.scene.control.TextField usernameText, passwordText, emailText, firstNameText, lastNameText;


    @FXML
    private void initialize() {

    }

    public void registerUser() {
        try{
            MainWindowController.customer = new Customer(usernameText.getText(), passwordText.getText(),firstNameText.getText(), lastNameText.getText(), emailText.getText());

        } catch (NullPointerException e) {
            System.out.println(getClass() + ": A field was not filled.");
        }
        if(MainWindowController.customer != null) {
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
