package com.javamaster.dao;

import com.javamaster.config.Connect;
import com.javamaster.dao.sql.JobSQL;
import com.javamaster.model.Job;
import com.javamaster.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO
@Repository
public class JobDao {
    private StoreDao storeDao;
    @Autowired
    public void StoreServise(StoreDao storeDao){
        this.storeDao = storeDao;
    }

    public void create(Job job) {
        try {
//            TODO Autogeneration key
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(JobSQL.CREATE_JOBTYPE);
            preparedStatement.setString(1, job.getType());
            preparedStatement.setFloat(2, job.getPricePerHour());
            preparedStatement.setLong(3, job.getStore().getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Job> getAll() {
        try {
            ArrayList<Job> jobs = new ArrayList<Job>();
            ResultSet rs = Connect.getConnection().prepareStatement(JobSQL.GET_ALL_JOBTYPE).executeQuery();

            while (rs.next()) {
                Store store = storeDao.marketByID(rs.getLong("market_id_fk"));
                jobs.add(new Job(rs.getLong("id"), rs.getFloat("pricePerHour"), rs.getString("type"), store));

            }
            return jobs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




}
