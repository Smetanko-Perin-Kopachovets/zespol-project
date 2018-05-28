package com.javamaster.controller;

import com.javamaster.model.Role;
import com.javamaster.model.User;
import com.javamaster.service.PDFGenerator;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.RoleService;
import com.javamaster.service.entity.UserService;
import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String loadPage() {
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("message", "Invalid login or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You've been logged out successfully");
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("address") String address,
                               RedirectAttributes redirectAttributes) {

        if (userService.getUserByEmail(email) == null) {

            Role role = roleService.getById(2L);
            User user = new User(firstName, lastName, email, password, address, role);
            User createdUser = userService.create(user);

            if (createdUser != null) {
                sendMailRegistration(createdUser);
                String message = "" + createdUser.getFirstName() + ", your account created";
                redirectAttributes.addFlashAttribute("message", message);

            } else {
                String message = "Something went wrong";
                redirectAttributes.addFlashAttribute("message", message);
            }

        } else {
            String message = "User with this email already exist";
            redirectAttributes.addFlashAttribute("message", message);
        }
        return "redirect:/login";
    }

    private void sendMailRegistration(User createdUser) {
        mailService.sendEmail(createdUser.getEmail(),
                "Registration in service",
                "Your account successfully created");
    }
}
