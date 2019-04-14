package TicketService.Controller;

import TicketService.DataAccess.Bank;
import TicketService.Model.Ticket;
import TicketService.Model.TicketHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BuyTicketController {

    @FXML
    private ListView<Ticket> ticketsListView;

    @FXML
    private Text priceField;

    @FXML
    private TextField accountNumberField, csvField;

    private TicketHandler ticketHandler;

    public void AddTicketsToCartList(TicketHandler ticketHandler) {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        this.ticketHandler = ticketHandler;
        for(Ticket ticket : ticketHandler.getTickets()) {
            list.add(ticket);
        }
        ticketsListView.setItems(list);
        updatePrice();
    }

    private void updatePrice() {
        for(Ticket ticket : ticketsListView.getItems()) {
            priceField.setText("Total price: " + ticketHandler.calculatedTotalPrice() + ",-");
        }
    }

    public void PayTickets(MouseEvent mouseEvent) {
        long accountNmber = Long.parseLong(accountNumberField.getText());
        int csv = Integer.parseInt(csvField.getText());
        if(Bank.PayTotalPrice(accountNmber, csv, ticketHandler.calculatedTotalPrice())) {
            ticketHandler.giveTicketToCustomer();
            Stage stage = (Stage)priceField.getScene().getWindow();
            stage.close();
        }
    }

}
