package TicketService;

import TicketService.Controller.MainWindowController;
import TicketService.Controller.SecondWindowController;
import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    private static MainFX minApplikasjon;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
        minApplikasjon = this;
        try{
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Ticket service");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/MainWindow.fxml"));
            Parent MainOverviewLayout = fxmlLoader.load();
            MainWindowController.primatyStage = primaryStage;
            Scene hovedScene = new Scene(MainOverviewLayout, 700, 500);
            primaryStage.setScene(hovedScene);


            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
