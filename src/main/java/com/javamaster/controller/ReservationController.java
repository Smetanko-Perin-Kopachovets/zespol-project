package com.javamaster.controller;


import com.javamaster.model.Job;
import com.javamaster.model.Reservation;
import com.javamaster.model.User;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.ReservationService;
import com.javamaster.service.entity.RoleService;
import com.javamaster.service.entity.UserService;
import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.security.Principal;
import java.sql.PreparedStatement;


@Controller
public class ReservationController {

    private UserService userService;
    private JobService jobService;
    private ReservationService reservationService;
    private MailService mailService;

    @Autowired
    public ReservationController(UserService userService,
                                 JobService jobService,
                                 ReservationService reservationService,
                                 MailService mailService) {
        this.userService = userService;
        this.jobService = jobService;
        this.reservationService = reservationService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String loadPage(Principal principal,
                           Model model) {

        User user = getUser(principal);
        if (user.getRole().getName().equals("ROLE_MANAGER")) {
            model.addAttribute("reservationList", reservationService.getReservation());
        } else {
            model.addAttribute("acceptListJobOnUser", jobService.jobOnUser(user.getId()));
            model.addAttribute("reservationListOnUser", reservationService.getReservationOnUsers(user.getId()));
        }
        return "reservation";
    }

    @RequestMapping(value = "/reservation/{jobId}", method = RequestMethod.GET)
    public String createReservation(@PathVariable("jobId") Long jobId,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        User user = getUser(principal);
        Job job = jobService.getById(jobId);
        Reservation reservation = new Reservation(user, job);
        reservation = reservationService.create(reservation);

        if (reservation != null) {
            String body = "" + reservation.getUser().getFirstName() + " " +
                    reservation.getUser().getLastName() + " reserved " +
                    job.getJobType().getType() + " in " +
                    job.getJobType().getStore().getName() + ", " +
                    job.getJobType().getStore().getCity() + " at " +
                    job.getDateTimeFrom();
            mailService.sendEmail(user.getEmail(), "New reservation for " + reservation.getUser().getEmail(), body);
        }

        redirectAttributes.addFlashAttribute("message", "Reservation with id " + reservation.getId() + "succesed");
        return "redirect:/jobs";
    }

    private User getUser(Principal principal) {
        return userService.getById(Long.parseLong(userService.getUserByEmail(principal.getName()).getId().toString()));
    }


    @RequestMapping(value = "/reservation/reject/{reservationId}", method = RequestMethod.GET)
    public String rejectReservation(@PathVariable("reservationId") Long reservationId,
                                    RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.rejectReservation(reservationId);
        if(reservation != null) {
            String body = "Your application was rejected, job details" +
                    reservation.getJob().getJobType().getType() + " in " +
                    reservation.getJob().getJobType().getStore().getName() + ", " +
                    reservation.getJob().getJobType().getStore().getCity() + " at " +
                    reservation.getJob().getDateTimeFrom();
            mailService.sendEmail(reservation.getUser().getEmail(), "Reservation rejected",
                    body);
        }
        redirectAttributes.addFlashAttribute("message", "Reservation " + reservationId + " reject");
        return "redirect:/reservation";
    }

    @RequestMapping(value = "/reservation/accept/{reservationJobId}", method = RequestMethod.GET)
    public String acceptReservation(@PathVariable("reservationJobId") Long reservationJobId,
                                    RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.acceptReservation(reservationJobId);
        if(reservation != null) {
            String body = "Your application accepted, job details" +
                    reservation.getJob().getJobType().getType() + " in " +
                    reservation.getJob().getJobType().getStore().getName() + ", " +
                    reservation.getJob().getJobType().getStore().getCity() + " at " +
                    reservation.getJob().getDateTimeFrom();
            mailService.sendEmail(reservation.getUser().getEmail(), "Reservation accepted",
                    body);
        }
        redirectAttributes.addFlashAttribute("message", "Reservation " + reservationJobId + " accept");
        return "redirect:/reservation";
    }
}
