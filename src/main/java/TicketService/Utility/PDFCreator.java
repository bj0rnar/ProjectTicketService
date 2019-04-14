package TicketService.Utility;

import java.io.File;

import java.io.IOException;


import TicketService.Model.Ticket;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;

import javax.swing.*;


public class PDFCreator {

    public static void createPDFToPath(String path, Ticket ticket) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(path));
        try (Document document = new Document(pdf)){
            document.add(new Paragraph("Hello World!"));
            document.add(new Paragraph("Verification code: " + ticket.getVerificationCode()));
            document.add(new Paragraph(ticket.getReceipt()));


        }
    }
}
