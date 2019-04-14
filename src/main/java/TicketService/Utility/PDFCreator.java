package TicketService.Utility;

import java.io.File;

import java.io.IOException;


import TicketService.Model.Ticket;
import TicketService.Users.Customer;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;


public class PDFCreator {
    public static void initializePdfCreation(Ticket ticket) throws IOException {
        String selectedPath = chooseSaveDestination();

        createPDFToPath(selectedPath, ticket);

    }

    private static String chooseSaveDestination() throws IOException {
        //Blabla file open picker?
        String newPath = "C:\\pleaseWorkDirectory\\helloWorld.pdf";
        File file = new File(newPath);
        file.getParentFile().mkdirs();
        return newPath;
    }

    private static void createPDFToPath(String path, Ticket ticket) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(path));
        try (Document document = new Document(pdf)){
            document.add(new Paragraph("Hello World!"));
            document.add(new Paragraph("Verification code: " + ticket.getVerificationCode()));
            document.add(new Paragraph(ticket.getReceipt()));


        }
    }
}
