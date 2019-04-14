package TicketService.Controller;

import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Users.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    public static ScreenController screenController;
    public static Customer customer;
    public TicketHandler ticketHandler;

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private Text eventNameText, VenueNameText, EventDateText, SeatsLeftEventText, seatsLeftStaticText, totalAmountOfItems;

    @FXML
    private Button buyTicketsButton, addToCartButton;

    @FXML
    private void initialize() {
        eventListView.setItems(EventHandler.getEventListFX());
        updateEventDetails(eventListView.getItems().get(0));
        eventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldEvent, Event newEvent) {
                if(newEvent != null)
                    updateEventDetails(newEvent);
            }
        });
    }

    private void updateEventDetails(Event event) {
        eventNameText.setText(event.getName());
        VenueNameText.setText(event.getVenue().getName());
        EventDateText.setText(event.getDate().toString());
        if ((event.getEventSeats().size() != 0)) {
            addToCartButton.setDisable(false);
        } else {
            addToCartButton.setDisable(true);
        }
        if (event.getAreSeatsAvailable()) {
            seatsLeftStaticText.setText("Seats left: ");
            SeatsLeftEventText.setText(event.getVenue().getSeats().size() + ".");
        } else {
            SeatsLeftEventText.setText("");
            seatsLeftStaticText.setText("");
        }
    }

    public void AddEventToCart() {
        if (ticketHandler == null) {
            ticketHandler = new TicketHandler(customer);
        }
        Event event = eventListView.getSelectionModel().getSelectedItem();
        if (event != null) {
            ticketHandler.createTicket(event, 0);
            totalAmountOfItems.setText("Shopping cart items: " + ticketHandler.getTickets().size());
        }
        updateEventDetails(event);

    }

    public void buyTickets() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../View/BuyTicketWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Rediger film");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(buyTicketsButton.getScene().getWindow());

            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);

            BuyTicketController secondController = fxmlLoader.getController();
            //secondController.setEventToEdit(event);

            dialogStage.showAndWait();

        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public void testNewStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../View/SecondWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Rediger film");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primatyStage);

            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);

            BuyTicketController secondController = fxmlLoader.getController();
            //secondController.setEventToEdit(event);

            dialogStage.showAndWait();

        } catch (IOException | IllegalStateException exception) {
            System.out.println(exception.toString());
        }
    }
}
