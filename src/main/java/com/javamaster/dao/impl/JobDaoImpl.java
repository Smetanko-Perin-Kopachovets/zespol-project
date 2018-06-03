package com.javamaster.dao.impl;

import com.javamaster.model.Job;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.JobTypeService;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobDaoImpl {

    private DataSource dataSource;
    private JobTypeService jobTypeService;
    private JobExtractor jobExtractor;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JobDaoImpl(DataSource dataSource, JobTypeService jobTypeService) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jobTypeService = jobTypeService;
        this.jobExtractor = new JobExtractor();

    }

    public List<Job> getJobByUser(Integer id) {

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("users_id_fk", id);
        return jdbcTemplate.query("" +
                "SELECT * FROM job j " +
                "INNER JOIN users u " +
                "ON j.users_id_fk = u.id " +
                "WHERE j.users_id_fk = :users_id_fk", params, jobExtractor);
    }

    public List<Job> getJobByTime(String date) {
        LocalDate localDate = LocalDate.parse(date);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("date", localDate);
        return jdbcTemplate.query("SELECT * FROM job WHERE date > :date", params, jobExtractor);
    }

    private class JobExtractor implements ResultSetExtractor<List<Job>> {

        @Override
        public List<Job> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            ArrayList<Job> list = new ArrayList<>();

            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setJobType(jobTypeService.getById(resultSet.getLong("jobtype_id_fk")));
                job.setSalary(resultSet.getFloat("priceperjob"));
                job.setDate(resultSet.getDate("date").toLocalDate());
                job.setDateTimeFrom(resultSet.getTime("timefrom").toLocalTime());
                job.setDateTimeTo(resultSet.getTime("timeto").toLocalTime());
                list.add(job);
            }
            return list;
        }
    }
}
