package com.javamaster.controller;

import com.javamaster.model.Store;
import com.javamaster.service.PDFGenerator;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.RoleService;
import com.javamaster.service.entity.StoreService;
import com.javamaster.service.entity.UserService;
import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PDFController {

    @Autowired
    private PDFGenerator pdfGenerator;
    @Autowired
    private StoreService storeService;
    @Autowired
    private JobService jobService;

    @RequestMapping("/generatePDF")
    public String loadPage(Model model) {
        model.addAttribute("stores", storeService.getAll());
        return "PDFGenerator";
    }

    @RequestMapping(value = "/generatePDF/download/{fileName:.+}", method = RequestMethod.POST)
    public void downloadPDFResource(HttpServletResponse response,
                                    @RequestParam("storeId") Long storeId,
                                    @PathVariable("fileName") String fileName) {

        Store store = storeService.getById(storeId);
        System.out.println(store.getName());
        pdfGenerator.setData(jobService.getAll(), store);

        String dataDirectory = "D:";
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
