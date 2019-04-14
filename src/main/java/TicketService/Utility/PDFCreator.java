package TicketService.Utility;

import java.io.IOException;
import TicketService.Model.Ticket;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;


public class PDFCreator {

    public static void createPDFToPath(String path, Ticket ticket) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(path));
        PdfPage page = pdf.addNewPage();

        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle rectangle = new Rectangle(35, 600, 400, 100);
        pdfCanvas.rectangle(rectangle);
        pdfCanvas.stroke();

        Canvas canvas = new Canvas(pdfCanvas, pdf, rectangle);

        Paragraph p2 = new Paragraph("Verifiseringskode: " + ticket.getVerificationCode() + "\n" + "Detaljer: " + "\n" +  ticket.getReceipt());

        canvas.add(p2);

        pdf.close();
    }
}
