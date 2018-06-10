package com.javamaster.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javamaster.model.Job;
import com.javamaster.model.Store;
import com.javamaster.model.User;
import org.springframework.stereotype.Component;

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
    private User user;

    public void generateStorePDF(List<Job> jobs, Store store) {
        this.jobs = new ArrayList<>();
        document = new Document();
        this.store = store;
        this.jobs = jobs.stream()
                .filter(Job -> Job.getJobType().getStore().getName().equals(store.getName()))
                .collect(Collectors.toList());

        generatePDF();

    }

    public void generateAllParamPDF(List<Job> jobs, Store store, User user) {
        this.jobs = new ArrayList<>();
        document = new Document();
        this.store = store;
        this.user = user;
        for (Job job : jobs) {
            if (job.getUser() != null) {
                if (job.getUser().getId().equals(Integer.parseInt(user.getId().toString()))
                        &&
                        job.getJobType().getStore().getId().equals(store.getId())) {
                    this.jobs.add(job);
                }
            }
        }

        generatePDF();
    }

    public void generateWithoutParamPDF(List<Job> jobs) {
        this.jobs = new ArrayList<>();
        document = new Document();
        this.jobs = jobs;
        generatePDF();
    }

    public void generateUserPDF(List<Job> jobs, User user) {
        this.jobs = new ArrayList<>();
        document = new Document();
        this.user = user;
        for (Job job : jobs) {
            if (job.getUser() != null) {
                if (job.getUser().getId().equals(user.getId())) {
                    System.out.println("add job");
                    this.jobs.add(job);
                }
            }
        }
        generatePDF();
    }

    private void generatePDF() {
        try {
            PdfWriter.getInstance(document, new FileOutputStream("D:\\PDFName.pdf"));
            document.open();
            if (!jobs.isEmpty()) {
                addParagraph("PDF generated for " + jobs.get(0).getJobType().getStore().getName(), Element.ALIGN_CENTER);
                addParagraph("All count jobs " + jobs.size(), Element.ALIGN_LEFT);
                addParagraph("Available jobs " + jobs.size(), Element.ALIGN_LEFT);
                addParagraph("Salary: " + getSalary(), Element.ALIGN_RIGHT);
                addParagraph("Jobs", Element.ALIGN_CENTER);
                addParagraph(" ", Element.ALIGN_RIGHT);
                document.add(new Paragraph());
                addTable();
            } else {
                addParagraph("No results", Element.ALIGN_CENTER);
            }
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();

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
