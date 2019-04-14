package TicketService;

import TicketService.Controller.LoginWindowController;
import TicketService.Controller.ScreenController;
import TicketService.DataAccess.DataContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    private static MainFX minApplikasjon;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {


        DataContext.CreateVenues();
        DataContext.CreateEvents();
        minApplikasjon = this;
        try{
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Ticket service login");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/RegisterWindow.fxml"));
            Parent LoginWindowOverview = fxmlLoader.load();
            Scene hovedScene = new Scene(LoginWindowOverview, 580, 400);

            ScreenController screenController = new ScreenController(hovedScene);
            screenController.addScreen("Login", FXMLLoader.load(getClass().getResource("View/RegisterWindow.fxml")));
            screenController.addScreen("Main", FXMLLoader.load(getClass().getResource("View/MainWindow.fxml")));



            LoginWindowController.screenController = screenController;
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
