package TicketService.Controller;

import TicketService.DataAccess.FakeDB;
import TicketService.DataAccess.IRepository;
import TicketService.Exception.IllegalTicketCreationException;
import TicketService.MainFX;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Users.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;

public class ShopWindowController {

    public static ScreenController screenController;
    private Customer customer;
    public TicketHandler ticketHandler;
    private ObservableList<Event> eventListFX = FXCollections.observableArrayList();

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private Text eventNameText, VenueNameText, EventDateText, seatsLeftStaticText, totalAmountOfItems, TicketPriceText, totalPriceText, EventTicketsLeftText;

    @FXML
    private Button buyTicketsButton, addToCartButton;

    @FXML
    private void initialize() {
        if(eventListFX.isEmpty()) {
            IRepository repo = new FakeDB();
            eventListFX.addAll(repo.getEvents());
            eventListView.setItems(eventListFX);
        }
        if(eventListView.getItems().get(0) != null)
            updateEventDetails(eventListView.getItems().get(0));

        buyTicketsButton.setDisable(true);
        addToCartButton.setDisable(true);
        eventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldEvent, Event newEvent) {
                if(newEvent != null) {
                    updateEventDetails(newEvent);
                    addToCartButton.setDisable(false);
                } else {
                    addToCartButton.setDisable(true);
                }
            }
        });
    }

    private void updateEventDetails(Event event) {
        eventNameText.setText(event.getName());
        VenueNameText.setText(event.getVenue().getName());
        EventDateText.setText(event.getDate().toString());
        TicketPriceText.setText(event.getTicketPrice() + ",-");

        if ((event.getAvailableTickets() > 0)) {
            addToCartButton.setDisable(false);
        } else {
            addToCartButton.setDisable(true);
        }
        seatsLeftStaticText.setText("Tickets left: ");
        EventTicketsLeftText.setText((String.valueOf(event.getAvailableTickets())));

        if(ticketHandler != null) {
            if (ticketHandler.getTickets().size() > 0)
                buyTicketsButton.setDisable(false);
            totalPriceText.setText(ticketHandler.calculatedTotalPrice() + ",-");
        }
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void AddEventTicketToCart() {
        if (ticketHandler == null) {
            ticketHandler = new TicketHandler(customer);
        }
        Event event = eventListView.getSelectionModel().getSelectedItem();
        if (event != null) {
            try {
                if(event.getVenue().getSeats().size() != 0) {
                    ticketHandler.createTicket(event, -1);
                }
                else {
                    ticketHandler.createTicket(event);
                }
            } catch (IllegalTicketCreationException e) {
                e.printStackTrace();
            }
            totalAmountOfItems.setText("Shopping cart items: " + ticketHandler.getTickets().size());
            updateEventDetails(event);
        }
    }

    public void buyTickets() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/BuyTicketWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Payment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(buyTicketsButton.getScene().getWindow());
            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);
            BuyTicketController buyTicketController = fxmlLoader.getController();
            buyTicketController.AddTicketsToCartList(ticketHandler);
            dialogStage.showAndWait();
            initialize();
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public void goToMyTickets(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/MyTicketsWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("My tickets");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(buyTicketsButton.getScene().getWindow());
            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);
            MyTicketsController myTicketsController = fxmlLoader.getController();
            myTicketsController.setCustomer(customer);
            dialogStage.showAndWait();
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) {
        if(ticketHandler != null)
            ticketHandler.cancelBuyTicketProcess();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            MainFX.primaryStage.setTitle("ProjectTicketService Login");
            fxmlLoader.setLocation(getClass().getResource("../View/LoginWindow.fxml"));
            Parent OrganizerMainWindow = fxmlLoader.load();
            Scene hovedScene = new Scene(OrganizerMainWindow, 580, 400);
            MainFX.primaryStage.setScene(hovedScene);
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }
}
