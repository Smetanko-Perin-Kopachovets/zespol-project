package com.javamaster.Dao;

import com.javamaster.Dao.sql.JobSQL;
import com.javamaster.config.Connect;
import com.javamaster.model.Job;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JobDao {

     public void CreateJob(Job job){
         try {
             PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(JobSQL.CREATE_JOB);
             preparedStatement.setString(1,job.getStore());
             preparedStatement.setString(2,job.getTitle());
             preparedStatement.execute();
             preparedStatement.close();

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

        public Job getById(){

            try {
                PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(JobSQL.SHOW_JOB);
                ResultSet resultSet = preparedStatement.executeQuery();
                Job job = new Job();

                while (resultSet.next()){
                    job.setId(resultSet.getInt("id"));
                    job.setStore(resultSet.getString("store"));
                    job.setTitle(resultSet.getString("title"));

                }
                if (job != null){
                    return job;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
