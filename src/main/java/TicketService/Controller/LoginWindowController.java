package TicketService.Controller;

import TicketService.DataAccess.FakeDB;
import TicketService.MainFX;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        User user = FakeDB.authUserLogin(usernameText.getText(), passwordText.getText());
        if( user != null) {
            if(user instanceof Customer) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    MainFX.primaryStage.setTitle("ProjectTicketService Login");
                    fxmlLoader.setLocation(getClass().getResource("../View/ShopWindow.fxml"));
                    Parent OrganizerMainWindow = fxmlLoader.load();
                    Scene hovedScene = new Scene(OrganizerMainWindow, 580, 400);
                    ShopWindowController shopWindowController = fxmlLoader.getController();
                    shopWindowController.setCustomer((Customer)user);
                    MainFX.primaryStage.setScene(hovedScene);
                } catch (IOException | IllegalStateException exception) {
                    exception.printStackTrace();
                }

            }
            if(user instanceof Organizer) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    MainFX.primaryStage.setTitle("Organizer view");
                    fxmlLoader.setLocation(getClass().getResource("../View/OrganizerMainWindow.fxml"));
                    Parent OrganizerMainWindow = fxmlLoader.load();
                    Scene hovedScene = new Scene(OrganizerMainWindow, 580, 400);
                    OrganizerMainWindowController controller = fxmlLoader.getController();
                    controller.setupController((Organizer)user);
                    MainFX.primaryStage.setScene(hovedScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void goToRegistration(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            MainFX.primaryStage.setTitle("ProjectTicketService Register");
            fxmlLoader.setLocation(getClass().getResource("../View/RegisterWindow.fxml"));
            Parent OrganizerMainWindow = fxmlLoader.load();
            Scene hovedScene = new Scene(OrganizerMainWindow, 580, 400);
            MainFX.primaryStage.setScene(hovedScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
