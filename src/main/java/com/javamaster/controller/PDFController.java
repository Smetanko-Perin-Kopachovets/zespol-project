package com.javamaster.controller;

import com.javamaster.model.Job;
import com.javamaster.model.Store;
import com.javamaster.model.User;
import com.javamaster.service.PDFGenerator;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.StoreService;
import com.javamaster.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PDFController {


    private PDFGenerator pdfGenerator;
    @Autowired
    private StoreService storeService;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;

    @RequestMapping("/generatePDF")
    public String loadPage(Model model) {
        model.addAttribute("stores", storeService.getAll());
        List<User> users = userService.getAll().stream()
                .filter(User -> User.getRole().getName().equals("ROLE_WORKER"))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "PDFGenerator";
    }

    @RequestMapping(value = "/generatePDF/download/{fileName:.+}", method = RequestMethod.POST)
    public String downloadPDFResource(HttpServletResponse response,
                                      RedirectAttributes redirectAttributes,
                                      @RequestParam("storeId") Long storeId,
                                      @RequestParam("userId") Long userId,
                                      @RequestParam("dateFrom") String dateFrom,
                                      @RequestParam("dateTo") String dateTo,
                                      @PathVariable("fileName") String fileName) {

        pdfGenerator = new PDFGenerator();
        List<Job> jobs = jobService.getWithTime(dateFrom, dateTo);
        if (!jobs.isEmpty()) {
            if(userId == 0 && storeId == 0) {
                System.out.println("without param pdf");
                pdfGenerator.generateWithoutParamPDF(jobs);
            } if(userId != 0 && storeId == 0) {
                System.out.println("user pdf");
                pdfGenerator.generateUserPDF(jobs, userService.getById(userId));
            } if(storeId != 0 && userId == 0) {
                System.out.println("store pdf");
                pdfGenerator.generateStorePDF(jobs, storeService.getById(storeId));
            } if(storeId != 0 && userId != 0) {
                System.out.println("all param pdf");
                pdfGenerator.generateAllParamPDF(jobs, storeService.getById(storeId), userService.getById(userId));
            }

            String dataDirectory = "D:";
            Path file = Paths.get(dataDirectory, fileName);
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                    return null;

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        redirectAttributes.addFlashAttribute("message", "This configuration not have results");
        return "redirect:/generatePDF";

    }

}
