import TicketService.Utility.PDFCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PDFCreationTest {
    @Test
    public void cmonBaby(){
        try {
            PDFCreator.initializePdfCreation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
