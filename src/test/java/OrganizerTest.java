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
    private int currentStateOfDB, currentStateOfLocal;

    @BeforeEach
    public void setup(){
        organizer = new Organizer("James", "Bond", "007@MI6.UK");
        eventHandler = new EventHandler(organizer);
        v = new Venue(48, "Sjoa samvirkelag");
        currentStateOfDB = FakeDB.uploadedEvents.size();
        currentStateOfLocal = organizer.getEvents().size();
    }

    @Test
    public void verifyThatTheSameEventIsUploadedInBothLocalListandDB(){
        eventHandler.createNewEvent("rere", v , LocalDate.now(), 123, false);
        Assertions.assertEquals(organizer.getEvents().get(currentStateOfLocal), FakeDB.uploadedEvents.get(currentStateOfDB));
    }

    @Test
    public void removeEventFromBothLocalAndDB(){
        eventHandler.createNewEvent("Tjohei", v, LocalDate.now(), 123, true);
        currentStateOfDB++;
        currentStateOfLocal++;
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        eventHandler.removeArrangementFromDB("Tjohei");
        currentStateOfDB--;
        currentStateOfLocal--;
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
    }
}
