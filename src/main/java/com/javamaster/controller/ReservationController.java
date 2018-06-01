package com.javamaster.controller;


import com.javamaster.model.Job;
import com.javamaster.model.Reservation;
import com.javamaster.model.User;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.ReservationService;
import com.javamaster.service.entity.RoleService;
import com.javamaster.service.entity.UserService;
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

    @Autowired
    public ReservationController(UserService userService, JobService jobService, ReservationService reservationService){
        this.userService = userService;
        this.jobService = jobService;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String loadpage(Principal principal,
                           Model model){

        User user = getUser(principal);
        if(user.getRole().getName().equals("ROLE_MANAGER")){
            model.addAttribute("reservationList", reservationService.getReservation());
        } else {
            model.addAttribute("acceptListJobOnUser", jobService.jobOnUser(user.getId()));
           model.addAttribute("reservationListOnUser",reservationService.getReservationOnUsers(user.getId()));
        }
        return "reservation";
    }

    @RequestMapping (value = "/reservation/{jobId}", method = RequestMethod.GET)
    public String createReservation(@PathVariable("jobId") Long jobId,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal){

        User user = getUser(principal);
        Job job = jobService.getById(jobId);

        Reservation reservation = new Reservation(user, job);

        reservationService.create(reservation);

        redirectAttributes.addFlashAttribute("message", "Reservation with id " + reservation.getId() + "succesed");
        return "redirect:/jobs";
    }

    public User getUser (Principal principal){
        return userService.getById(Long.parseLong(userService.getUserByEmail(principal.getName()).getId().toString()));
    }


    @RequestMapping(value = "/reservation/reject/{reservationId}", method = RequestMethod.GET)
    public String rejectReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.rejectReservation(reservationId);
        return "redirect:/reservation";
    }

    @RequestMapping(value = "/reservation/accept/{reservationJobId}", method = RequestMethod.GET)
    public String acceptReservation(@PathVariable("reservationJobId") Long reservationJobId){
        reservationService.acceptReservation(reservationJobId);
        return "redirect:/reservation";
    }
}
