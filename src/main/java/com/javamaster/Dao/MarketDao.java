package com.javamaster.Dao;

import com.javamaster.Dao.sql.MarketSQL;
import com.javamaster.config.Connect;
import com.javamaster.model.Market;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class MarketDao {

    public ArrayList<Market> getAllMarket(){
        try {
            ArrayList<Market> markets = new ArrayList<Market>();
            ResultSet rs  = Connect.getConnection().prepareStatement(MarketSQL.GET_ALL_MARKET).executeQuery();

            while (rs.next()){
            markets.add(new Market(rs.getLong("id"), rs.getString("name"),rs.getString("city")));

            }
            return markets;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
