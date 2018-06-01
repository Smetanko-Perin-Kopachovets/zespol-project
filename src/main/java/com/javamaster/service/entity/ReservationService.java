package com.javamaster.service.entity;

import com.javamaster.model.Reservation;

import java.util.List;

public interface ReservationService{
    Reservation create(Reservation reservation);
    List<Reservation> getReservation();
    List<Reservation> getReservationOnUsers(Integer id);
    Reservation getById(Long id);
    Reservation acceptReservation(Long id);
    Reservation rejectReservation(Long id);
}
