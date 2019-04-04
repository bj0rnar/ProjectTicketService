import TicketService.Model.Event;
import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import TicketService.Utility.FakeDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OrganizerTest {

    private Organizer organizer;
    private EventHandler eventHandler;
    private Venue v;

    @BeforeEach
    public void setup(){
        organizer = new Organizer("James", "Bond", "007@MI6.UK");
        eventHandler = new EventHandler(organizer);
        v = new Venue(48, "Sjoa samvirkelag");
    }

    @Test
    public void verifyThatTheSameEventIsUploadedInBothLocalListandDB(){
        eventHandler.createNewEvent("rere", v , LocalDate.now(), 123, false);
        Assertions.assertEquals(eventHandler.getOrganizerEventList().get(0), FakeDB.uploadedEvents.get(0));
    }

    @Test
    public void removeEventFromBothLocalAndDB(){
        int currentStateOfDB = FakeDB.uploadedEvents.size();
        int currentStateOfLocal = eventHandler.getOrganizerEventList().size();
        eventHandler.createNewEvent("Tjohei", v, LocalDate.now(), 123, true);
        currentStateOfDB++;
        currentStateOfLocal++;
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
        Assertions.assertEquals(currentStateOfLocal, eventHandler.getOrganizerEventList().size());
        eventHandler.removeArrangementFromDB("Tjohei");
        currentStateOfDB--;
        currentStateOfLocal--;
        Assertions.assertEquals(currentStateOfLocal, eventHandler.getOrganizerEventList().size());
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
    }

}
