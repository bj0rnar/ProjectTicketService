package TicketService.Controller;

import TicketService.DataAccess.DataContext;
import TicketService.MainFX;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;


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
                OrganizerMainWindowController.organizer = (Organizer)user;
                screenController.active("OrganizerWindow");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    URL path = getClass().getResource("../View/OrganizerMainWindow.fxml");
                    Pane p = fxmlLoader.load(getClass().getResource("../View/OrganizerMainWindow.fxml").openStream());
                    OrganizerMainWindowController controller = (OrganizerMainWindowController)fxmlLoader.getController();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
