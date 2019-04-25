package TicketService;

import TicketService.Controller.*;
import TicketService.DataAccess.FakeDB;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Users.Organizer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {


    /*
     *   1. Download JavaFX :    https://gluonhq.com/products/javafx/      ... Choose 'JavaFX Windows SDK'
     *   2. Place where-ever, but a common location would be C:/Program files/java/javafx
     *   3. Go to File -> Settings -> Appearance & Behavior -> Path Variables. Add new path named: PATH_TO_FX and set location to C:/Program files/java/javafx/lib
     *   4. If MainFX is already in Configurations, proceed to step 6.
     *   5. Go to Edit Configuration... and press the + sign top left. Name: MainFX, Main class: TicketService.MainFX.
     *   4. Add : --module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml    to "Edit Configurations... -> VM Options."
     *   5. Make sure javafx/lib is in the project libraries. Project Structur -> Libraries
     *
     *   Yes, we could do it trough pom, but I was too lazy to do it that way.
     */

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        FakeDB.CreateVenues();
        try {
            FakeDB.CreateEvents();
        } catch (VenueHasNoSeatsException e) {
            e.printStackTrace();
        }
        FakeDB.CreateUsers();
        try{
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Ticket service login");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/LoginWindow.fxml"));
            Parent LoginWindowOverview = fxmlLoader.load();
            Scene hovedScene = new Scene(LoginWindowOverview, 580, 400);

            ScreenController screenController = new ScreenController(hovedScene);
            screenController.addScreen("Register", FXMLLoader.load(getClass().getResource("View/RegisterWindow.fxml")));
            screenController.addScreen("Login", FXMLLoader.load(getClass().getResource("View/LoginWindow.fxml")));
            screenController.addScreen("Main", FXMLLoader.load(getClass().getResource("View/ShopWindow.fxml")));
            screenController.addScreen("OrganizerWindow", FXMLLoader.load(getClass().getResource("View/OrganizerMainWindow.fxml")));

            OrganizerMainWindowController.screenController = screenController;
            LoginWindowController.screenController = screenController;
            RegisterWindowController.screenController = screenController;
            ShopWindowController.screenController = screenController;
            primaryStage.setScene(hovedScene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CreateDummyData();
        launch(args);
    }

    private static void CreateDummyData() {
        FakeDB.CreateVenues();
        try {
            FakeDB.CreateEvents();
        } catch (VenueHasNoSeatsException e) {
            e.printStackTrace();
        }
        FakeDB.CreateUsers();
    }
}
