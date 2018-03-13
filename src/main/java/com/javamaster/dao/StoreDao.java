package com.javamaster.dao;

import com.javamaster.dao.sql.MarketSQL;
import com.javamaster.config.Connect;
import com.javamaster.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class StoreDao {


    public void create(Store store){
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(MarketSQL.CREATE_MARKET);
            preparedStatement.setString(1, store.getName());
            preparedStatement.setString(2, store.getCity());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Store> getAll(){
        try {
            ArrayList<Store> stores = new ArrayList<Store>();
            ResultSet rs  = Connect.getConnection().prepareStatement(MarketSQL.GET_ALL_MARKET).executeQuery();

            while (rs.next()){
            stores.add(new Store(rs.getLong("id"), rs.getString("name"),rs.getString("city")));

            }
            return stores;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
