package com.javamaster.dao;

import com.javamaster.config.Connect;
import com.javamaster.dao.sql.StoreSQL;
import com.javamaster.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class StoreDao {

    public void update(Store store) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(StoreSQL.UPDATE_MARKET_ID);
            preparedStatement.setString(1, store.getName());
            preparedStatement.setString(2, store.getCity());
            preparedStatement.setLong(3, store.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void create(Store store) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(StoreSQL.CREATE_MARKET);
            preparedStatement.setString(1, store.getName());
            preparedStatement.setString(2, store.getCity());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Store> getAll() {
        try {
            ArrayList<Store> stores = new ArrayList<Store>();
            ResultSet rs = Connect.getConnection().prepareStatement(StoreSQL.GET_ALL_MARKET).executeQuery();

            while (rs.next()) {
                stores.add(new Store(rs.getLong("id"), rs.getString("name"), rs.getString("city")));

            }
            return stores;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteById(Long id) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(StoreSQL.DELETE_MARKET_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Store marketById(Long id) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(StoreSQL.GET_MARKET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Store store = null;
            while (rs.next()) {
                store = new Store(rs.getLong("id"), rs.getString("name"), rs.getString("city"));
            }
            return store;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


