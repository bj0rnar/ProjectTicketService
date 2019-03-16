package TicketService;

import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    * */


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
            System.out.println(getClass().getResource(""));
            fxmlLoader.setLocation(getClass().getResource("View/MainWindow.fxml"));

            Parent MainOverviewLayout = fxmlLoader.load();

            Scene hovedScene = new Scene(MainOverviewLayout, 700, 500);

            primaryStage.setScene(hovedScene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void visAlert(String melding) {
        Alert newAlert = new Alert(Alert.AlertType.ERROR);
        newAlert.setTitle("Feil");
        newAlert.setHeaderText(null);
        newAlert.setContentText("Noe gikk feil! " + melding);

        newAlert.showAndWait();
    }

    public static MainFX getInstance() {
        return minApplikasjon;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
