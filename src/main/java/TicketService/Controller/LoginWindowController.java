package TicketService.Controller;

import TicketService.DataAccess.DataContext;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginWindowController {

    public static ScreenController screenController;

    @FXML private Button loginButton;
    @FXML
    TextField usernameText, passwordText;


    @FXML
    private void initialize() {

    }

    public void logInUser() {
        User user = DataContext.authUserLogin(usernameText.getText(), passwordText.getText());
        if( user != null) {
            if(user instanceof Customer) {
                ShopWindowController.customer = (Customer)user;
                screenController.active("Main");
            }
            if(user instanceof Organizer) {

            }
        }
    }
}
