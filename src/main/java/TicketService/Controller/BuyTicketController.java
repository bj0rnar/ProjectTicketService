package TicketService.Controller;

import TicketService.Model.Ticket;
import TicketService.Model.TicketHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;


public class BuyTicketController {

    @FXML
    private ListView<Ticket> ticketsListView;

    @FXML
    private Text priceField;



    @FXML
    private void initialize() {

    }

    public void AddTicketsToCartList(TicketHandler ticketHandler) {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        for(Ticket ticket : ticketHandler.getTickets()) {
            list.add(ticket);
        }
        ticketsListView.setItems(list);
        updatePrice();
    }

    private void updatePrice() {
        int totalPrice = 0;
        for(Ticket ticket : ticketsListView.getItems()) {
            priceField.setText("Total price: " + (totalPrice += ticket.getEvent().getTicketPrice()) + ",-");
        }
    }


}
