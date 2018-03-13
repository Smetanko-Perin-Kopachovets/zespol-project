package com.javamaster.dao;

import com.javamaster.config.Connect;
import com.javamaster.dao.sql.JobSQL;
import com.javamaster.model.Job;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//TODO
@Repository
public class JobDao {

    public void create(Job job) {
        try {

            //TODO
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(JobSQL.CREATE_JOB);
            preparedStatement.setLong(1, job.getStore().getId());
            preparedStatement.setString(2, job.getTitle());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
