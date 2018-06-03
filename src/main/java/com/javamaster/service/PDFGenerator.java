package com.javamaster.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javamaster.model.Job;
import com.javamaster.model.Store;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PDFGenerator {

    private List<Job> jobs;
    private Document document;
    private Store store;

    public void setData(List<Job> jobs, Store store) {
        this.jobs = new ArrayList<>();
        this.store = store;
        document = new Document();
        this.jobs = jobs.stream()
                .filter(Job -> Job.getJobType().getStore().getName().equals(store.getName()))
                .collect(Collectors.toList());

        generatePDF();

    }

    private void generatePDF() {
        if (!jobs.isEmpty()) {
            try {
                PdfWriter.getInstance(document, new FileOutputStream("D:\\PDFName.pdf"));
                document.open();

                addParagraph("PDF generated for " + jobs.get(0).getJobType().getStore().getName(), Element.ALIGN_CENTER);
                addParagraph("All count jobs " + jobs.size(), Element.ALIGN_LEFT);
                addParagraph("Available jobs " + jobs.size(), Element.ALIGN_LEFT);
                addParagraph("Salary: " + getSalary(), Element.ALIGN_RIGHT);
                addParagraph("Jobs", Element.ALIGN_CENTER);
                addParagraph(" ", Element.ALIGN_RIGHT);
                document.add(new Paragraph());
                addTable();
                document.close();

            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PdfWriter.getInstance(document, new FileOutputStream("D:\\PDFName.pdf"));
            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }
            document.open();
            addParagraph("No results for " + store.getName(), Element.ALIGN_CENTER);
            document.close();
        }
    }


    private Float getSalary() {
        Float salary = 0F;
        for (Job job : jobs) {
            salary += job.getSalary();
        }
        return salary;
    }

    private void addParagraph(String text, Integer align) {
        Font font = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
        Chunk textChunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(textChunk);
        paragraph.setAlignment(align);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addTable() {
        try {
            Paragraph paragraph = new Paragraph();
            PdfPTable table = new PdfPTable(6);

            addTableHeader(table, "Id");
            addTableHeader(table, "Type of job");
            addTableHeader(table, "Localization");
            addTableHeader(table, "Date");
            addTableHeader(table, "Time");
            addTableHeader(table, "Pay");

            for (Job job : jobs) {
                addRow(table, job.getId().toString());
                addRow(table, job.getJobType().getType());
                addRow(table, job.getJobType().getStore().getCity());
                addRow(table, job.getDate().toString());
                addRow(table, job.getDateTimeFrom().toString() + " - " + job.getDateTimeTo().toString());
                addRow(table, job.getSalary().toString());
            }
            paragraph.add(table);
            document.add(paragraph);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void addRow(PdfPTable table, String text) {
        table.addCell(text);
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
//        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(headerTitle));
        table.addCell(header);
    }

    public Document getDocument() {
        return document;
    }

}
