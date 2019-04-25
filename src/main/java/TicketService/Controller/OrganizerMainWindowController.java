package TicketService.Controller;

import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.time.LocalDate;

public class OrganizerMainWindowController {

    public static ScreenController screenController;

    @FXML
    private ListView<Event> eventListView;

    private ObservableList<Event> eventListFX = FXCollections.observableArrayList();
    private EventHandler eventHandler;

    @FXML
    private Text eventNameText, VenueNameText, EventDateText, ticketsLeftStaticText, TicketPriceText, EventTicketsLeftText;

    @FXML
    private Button DeleteEventButton, CheckInnCustomerButton;

    @FXML
    private void initialize() {
        if(eventHandler == null) {
            updateEventDetails(null);

        DeleteEventButton.setDisable(true);
        CheckInnCustomerButton.setDisable(true);
        }
        eventListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldEvent, newEvent) -> {
                updateEventDetails(newEvent);
                DeleteEventButton.setDisable(newEvent == null);
                CheckInnCustomerButton.setDisable(newEvent == null);
        });

    }


    public void setupController(Organizer organizer) {
        this.eventHandler = new EventHandler(organizer);
        createDummyData(organizer);
        updateEventList();
    }

    private void createDummyData(Organizer organizer) {
        eventHandler.createNewVenue(100, "My custom venue");
        try {
            eventHandler.createNewSeatedEvent("My custom event", organizer.getUserCreatedVenues().get(0), LocalDate.now(), 150);
        } catch (VenueHasNoSeatsException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer("TestUser","x","x","x","x");
        TicketHandler ticketHandler = new TicketHandler(customer);
        try {
            ticketHandler.createTicket(eventHandler.getOrganizer().getEvents().get(0), -1);
        } catch (IllegalTicketCreationException e) {
            e.printStackTrace();
        }
        ticketHandler.payForTicketsWithCreditCard(12324123412341234L, 123);
    }

    private void updateEventList() {
        eventListFX.clear();
        for(Event event : eventHandler.getOrganizer().getEvents()) {
            addEventToFxList(event);
        }
        eventListView.setItems(eventListFX);
    }

    private void updateEventDetails(Event event) {
        eventNameText.setText(event != null ? event.getName() : "");
        VenueNameText.setText(event!= null ? event.getVenue().getName() : "");
        EventDateText.setText(event!= null ? event.getDate().toString() : "");
        TicketPriceText.setText(event != null ? event.getTicketPrice() + ",-" : "");
        EventTicketsLeftText.setText(event  != null ? (String.valueOf(event.getAvailableTickets())) : "");

    }

    public void addEventToFxList(Event event) {
        eventListFX.add(event);
    }

    public void CheckInnCustomers(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/ValidateTicketsWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Check-inn Customers");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(DeleteEventButton.getScene().getWindow());
            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);
            ValidateTicketsController validateTicketsController = fxmlLoader.getController();
            validateTicketsController.setEvent(eventListView.getSelectionModel().getSelectedItem(), eventHandler);
            dialogStage.showAndWait();
            initialize();

        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public void DeleteEvent(MouseEvent mouseEvent) {
        Event event = eventListView.getSelectionModel().getSelectedItem();
        if(event != null)
            eventHandler.deleteEventFromDB(event);
        eventListFX.remove(event);
        updateEventDetails(null);
    }

    public void CreateEvent(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/CreateNewEventWindow.fxml"));
            Parent dialogLayout = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create new Event");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(DeleteEventButton.getScene().getWindow());
            Scene dialogScene = new Scene(dialogLayout);
            dialogStage.setScene(dialogScene);
            CreateNewEventController createNewEventController = fxmlLoader.getController();
            createNewEventController.setEventHandler(eventHandler);
            dialogStage.showAndWait();
            updateEventList();
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public void LogOut(MouseEvent mouseEvent) {
    }
}
