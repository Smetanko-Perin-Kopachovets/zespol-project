package com.javamaster.Dao;

import com.javamaster.Dao.sql.JobSQL;
import com.javamaster.config.Connect;
import com.javamaster.model.Job;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        public ArrayList<Job> getById(){
            ArrayList<Job> arrayList = new ArrayList<Job>();
            try {
            ResultSet rs = Connect.getConnection().prepareStatement(JobSQL.SHOW_JOB).executeQuery();

            while (rs.next()){
                arrayList.add(new Job(rs.getLong("id"), rs.getString("store"), rs.getString("title")));
            }
            return arrayList;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
