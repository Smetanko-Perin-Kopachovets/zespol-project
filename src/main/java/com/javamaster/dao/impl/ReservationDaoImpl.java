package com.javamaster.dao.impl;


import com.javamaster.model.Reservation;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDaoImpl {

    private DataSource dataSource;
    private JobService jobService;
    private UserService userSevice;


    @Autowired
    public ReservationDaoImpl(DataSource dataSource, JobService jobService, UserService userSevice){
        this.dataSource = dataSource;
        this.jobService = jobService;
        this.userSevice = userSevice;
    }


    public List<Reservation> getReservationOnUser(Integer id){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
           PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM reservation r " +
                   "INNER JOIN users u " +
                   "ON u.id = r.users_id_fk " +
                   "WHERE u.id = ? ");
           preparedStatement.setLong(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               Reservation reservation = new Reservation();

               reservation.setId(resultSet.getLong("id"));
               reservation.setUser(userSevice.getById(resultSet.getLong("users_id_fk")));
               reservation.setJob(jobService.getById(resultSet.getLong("job_id_fk")));
               reservations.add(reservation);
           }
           preparedStatement.close();
           dataSource.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation acceptReservationOnUser(Reservation reservation){
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("UPDATE job " +
                    "SET users_id_fk = ? " +
                    "WHERE id = ? ");
            preparedStatement.setLong(1, reservation.getUser().getId());
            preparedStatement.setLong(2,reservation.getJob().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//accept user and all delete
    public Long deleteAllReservationByJobId(Long id){
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM reservation WHERE job_id_fk = ? ");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long deleteReservationById(Long id){
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM reservation WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
