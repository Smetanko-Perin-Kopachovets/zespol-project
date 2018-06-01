package com.javamaster.service.entity.impl;

import com.javamaster.dao.ReservationRepository;
import com.javamaster.dao.impl.ReservationDaoImpl;
import com.javamaster.model.Reservation;
import com.javamaster.service.entity.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationDaoImpl reservationDao;


    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepository.saveAndFlush(reservation);
    }

    @Override
    public List<Reservation> getReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationOnUsers(Integer id) {
        return reservationDao.getReservationOnUser(id);
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.findOne(id);
    }

    @Override
    public Reservation acceptReservation(Long id) {
        Reservation reservation = getById(id);
        if(checkReservation(reservation)){
            reservationDao.deleteAllReservationByJobId(reservation.getJob().getId());
            return reservationDao.acceptReservationOnUser(reservation);
        }
        return null;
    }

    @Override
    public Reservation rejectReservation(Long id) {
        Reservation reservation = getById(id);
        if(checkReservation(reservation)){
            reservationDao.deleteReservationById(reservation.getId());
        }
        return reservation;
    }

    private boolean checkReservation(Reservation reservation){
        for (Reservation reservations : getReservation()) {
            if(reservations.getId().equals(reservation.getId())){
                return true;
            }
        }
        return false;
    }

}
