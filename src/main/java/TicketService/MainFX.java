package TicketService;

import TicketService.Controller.LoginWindowController;
import TicketService.Controller.MainWindowController;
import TicketService.Controller.RegisterWindowController;
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
        DataContext.CreateUsers();
        minApplikasjon = this;
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
            screenController.addScreen("Main", FXMLLoader.load(getClass().getResource("View/MainWindow.fxml")));

            LoginWindowController.screenController = screenController;
            RegisterWindowController.screenController = screenController;
            MainWindowController.screenController = screenController;
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
