package com.javamaster.dao;

import com.javamaster.config.Connect;
import com.javamaster.dao.sql.JobTypeSQL;
import com.javamaster.model.JobType;
import com.javamaster.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class JobTypeDao {
    private StoreDao storeDao;
    @Autowired
    public void StoreServise(StoreDao storeDao){
        this.storeDao = storeDao;
    }

    public void create(JobType jobType) {
        try {
//            TODO Autogeneration key
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(JobTypeSQL.CREATE_JOBTYPE);
            preparedStatement.setString(1, jobType.getType());
            preparedStatement.setFloat(2, jobType.getPricePerHour());
            preparedStatement.setLong(3, jobType.getStore().getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JobType> getAll() {
        try {
            ArrayList<JobType> jobTypes = new ArrayList<JobType>();
            ResultSet rs = Connect.getConnection().prepareStatement(JobTypeSQL.GET_ALL_JOBTYPE).executeQuery();

            while (rs.next()) {
                Store store = storeDao.marketById(rs.getLong("market_id_fk"));
                jobTypes.add(new JobType(rs.getLong("id"), rs.getFloat("pricePerHour"), rs.getString("type"), store));

            }
            return jobTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




}
