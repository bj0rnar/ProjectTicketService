package TicketService.Utility;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;



import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;


public class PDFCreator {
    public static void initializePdfCreation() throws IOException {
        chooseSaveDestination();
    }

    private static void chooseSaveDestination() throws IOException {
        //Blabla file open picker?
        String path = "C:\\pleaseWorkDirectory\\helloWorld.pdf";
        File file = new File(path);
        file.getParentFile().mkdirs();
        createPDFToPath(path);
    }

    private static void createPDFToPath(String path) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(path));
        try (Document document = new Document(pdf)){
            document.add(new Paragraph("Hello World!"));
        }
    }
}
