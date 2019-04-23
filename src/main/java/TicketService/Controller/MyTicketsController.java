package TicketService.Controller;

import TicketService.Model.Ticket;
import TicketService.Users.Customer;
import TicketService.Utility.PDFCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class MyTicketsController {

    @FXML
    ListView myTicketsList;

    @FXML
    Button downloadTicketButton;

    @FXML
    public void initialize() {
        downloadTicketButton.setDisable(true);
    }

    public void downloadTicket(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(myTicketsList.getScene().getWindow());


        if(myTicketsList.getSelectionModel().getSelectedItem() != null && selectedFile != null) {
            try {
                PDFCreator.createPDFToPath(selectedFile.getAbsolutePath(), (Ticket)myTicketsList.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCustomer(Customer customer) {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        if(customer.getTicketList().size() > 0) {
            for (Ticket ticket : customer.getTicketList()) {
                list.add(ticket);
            }
            myTicketsList.setItems(list);
        }
    }

    public void CheckIfItemIsSelected(MouseEvent mouseEvent) {
        if(myTicketsList.getSelectionModel().getSelectedItem() != null) {
            downloadTicketButton.setDisable(false);
        } else {
            downloadTicketButton.setDisable(true);
        }
    }
}
