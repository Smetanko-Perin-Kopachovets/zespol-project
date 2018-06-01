package com.javamaster.dao.impl;

import com.javamaster.model.Job;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.JobTypeService;
import com.javamaster.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobDaoImpl {
    private DataSource dataSource;
    private JobTypeService jobTypeService;

    @Autowired
    public JobDaoImpl(DataSource dataSource, JobTypeService jobTypeService){
        this.dataSource = dataSource;
        this.jobTypeService = jobTypeService;
    }

    public List<Job> getJobByUser(Integer id){
ArrayList<Job> arrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM job j " +
                    "INNER JOIN users u " +
                    "ON j.users_id_fk = u.id " +
                    "WHERE j.users_id_fk = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Job job = new Job();

                job.setId(resultSet.getLong("id"));
                job.setJobType(jobTypeService.getById(resultSet.getLong("jobtype_id_fk")));
                job.setSalary(resultSet.getFloat("priceperjob"));
                job.setDate(resultSet.getDate("date").toLocalDate());
                job.setDateTimeFrom(resultSet.getTime("timefrom").toLocalTime());
                job.setDateTimeTo(resultSet.getTime("timeto").toLocalTime());

                arrayList.add(job);
            }
            preparedStatement.close();
            dataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
