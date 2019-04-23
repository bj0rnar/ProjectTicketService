import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Utility.InputValidator;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static TicketService.Controller.ShopWindowController.customer;

public class InputValidationTest {

    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    Organizer organizer;
    TicketHandler ticketHandler;
    Venue oneSpotVenue, manySpotVenue;

    @BeforeEach
    public void setUp() throws VenueHasNoSeatsException {
        manySpotVenue = new Venue(100, "Hall 42");
    }

    @Test
    public void eventCreationInputIsValidated(){
        Assertions.assertTrue(InputValidator.validateSeatedEventInput("ManyspotEvent", manySpotVenue, LocalDate.now(), 100));
        Assertions.assertTrue(InputValidator.validateSeatedEventInput("ManyspotEvent", manySpotVenue, LocalDate.now(), 100));

        Assertions.assertFalse(InputValidator.validateSeatedEventInput("", manySpotVenue, LocalDate.now(), 100));
        Assertions.assertFalse(InputValidator.validateSeatedEventInput("rere", manySpotVenue, LocalDate.now(), 0));
        Assertions.assertFalse(InputValidator.validateSeatedEventInput("rere", null, LocalDate.now(), 0));
        Assertions.assertFalse(InputValidator.validateSeatedEventInput("rere", manySpotVenue, LocalDate.of(1990, 1,1), 0));
    }
}
