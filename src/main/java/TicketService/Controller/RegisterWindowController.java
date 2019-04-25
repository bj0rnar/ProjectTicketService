package TicketService.Controller;

import TicketService.DataAccess.FakeDB;
import TicketService.DataAccess.IRepository;
import TicketService.MainFX;
import TicketService.Users.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterWindowController {

    public static ScreenController screenController;

    @FXML private javafx.scene.control.Button loginButton;
    @FXML javafx.scene.control.TextField usernameText, passwordText, emailText, firstNameText, lastNameText;


    @FXML
    private void initialize() {

    }

    public void registerUser() {
        try{
            Customer customer = new Customer(usernameText.getText(), passwordText.getText(),firstNameText.getText(), lastNameText.getText(), emailText.getText());
            IRepository repo = new FakeDB();
            if(repo.uploadUser(customer)){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    MainFX.primaryStage.setTitle("ProjectTicketService Show");
                    fxmlLoader.setLocation(getClass().getResource("../View/ShopWindow.fxml"));
                    Parent OrganizerMainWindow = fxmlLoader.load();
                    Scene hovedScene = new Scene(OrganizerMainWindow, 580, 400);
                    ShopWindowController shopWindowController = fxmlLoader.getController();
                    shopWindowController.setCustomer(customer);
                    MainFX.primaryStage.setScene(hovedScene);
                } catch (IOException | IllegalStateException exception) {
                    exception.printStackTrace();
                }
            } else {
                System.out.println("User already registered");
            }
        } catch (NullPointerException e) {
            System.out.println(getClass() + ": A field was not filled.");
        }
    }
}
