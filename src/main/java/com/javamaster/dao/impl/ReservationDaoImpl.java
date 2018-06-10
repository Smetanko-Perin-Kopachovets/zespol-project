package com.javamaster.dao.impl;


import com.javamaster.model.Reservation;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    private NamedParameterJdbcTemplate jdbcTemplate;
    private ReservationExtractor reservationExtractor;
    private UserService userService;
    private JobService jobService;


    @Autowired
    public ReservationDaoImpl(DataSource dataSource, JobService jobService, UserService userService){
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.userService = userService;
        this.jobService = jobService;
       this.reservationExtractor = new ReservationExtractor();
    }


    public List<Reservation> getReservationOnUser(Integer id){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("users_id_fk", id);
        return  jdbcTemplate.query(" SELECT * FROM reservation r " +
                "INNER JOIN users u " +
                "ON r.users_id_fk = u.id " +
                "INNER JOIN job j " +
                "ON r.job_id_fk = j.id " +
                "WHERE r.users_id_fk = :users_id_fk ", param, reservationExtractor);

//        ArrayList<Reservation> reservations = new ArrayList<>();
//        try {
//           PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM reservation r " +
//                   "INNER JOIN users u " +
//                   "ON u.id = r.users_id_fk " +
//                   "WHERE u.id = ? ");
//           preparedStatement.setLong(1, id);
//           ResultSet resultSet = preparedStatement.executeQuery();
//
//           while (resultSet.next()){
//               Reservation reservation = new Reservation();
//
//               reservation.setId(resultSet.getLong("id"));
//               reservation.setUser(userService.getById(resultSet.getLong("users_id_fk")));
//               reservation.setJob(jobService.getById(resultSet.getLong("job_id_fk")));
//               reservations.add(reservation);
//           }
//           preparedStatement.close();
//           dataSource.getConnection().close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return reservations;
    }

    public List<Reservation> acceptReservationOnUser(Reservation reservation){

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("users_id_fk",reservation.getUser().getId())
                .addValue("job_id_fk", reservation.getJob().getId());
        return jdbcTemplate.query(" UPDATE job " +
                "SET users_id_fk = :users_id_fk " +
                "WHERE id = :id ",param, reservationExtractor);
//        try {
//            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("UPDATE job " +
//                    "SET users_id_fk = ? " +
//                    "WHERE id = ? ");
//            preparedStatement.setLong(1, reservation.getUser().getId());
//            preparedStatement.setLong(2,reservation.getJob().getId());
//            preparedStatement.execute();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
    }

//accept user and all delete
    public Long deleteAllReservationByJobId(Long id){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("job_id_fk", id);
        return Long.valueOf(jdbcTemplate.update("DELETE FROM reservation WHERE job_id_fk = :job_id_fk", param));
//        try {
//            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM reservation WHERE job_id_fk = ? ");
//            preparedStatement.setLong(1,id);
//            preparedStatement.execute();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public Long deleteReservationById(Long id){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        return Long.valueOf(jdbcTemplate.update("DELETE FROM reservation WHERE id = :id", param));
//        try {
//            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM reservation WHERE id = ?");
//            preparedStatement.setLong(1,id);
//            preparedStatement.execute();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    private class ReservationExtractor implements ResultSetExtractor<List<Reservation>>{

        @Override
        public List<Reservation> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getLong("id"));
                reservation.setUser(userService.getById(resultSet.getLong("users_id_fk")));
                reservation.setJob(jobService.getById(resultSet.getLong("job_id_fk")));
                reservations.add(reservation);
            }
            return reservations;
        }
    }

}
