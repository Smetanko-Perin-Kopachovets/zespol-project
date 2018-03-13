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

    public void Updatemarket(Market market){
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(MarketSQL.UPDATE_MARKET_ID);
            preparedStatement.setString(1,market.getName());
            preparedStatement.setString(2,market.getCity());
            preparedStatement.setLong(3,market.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Createmarket(Market market){
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(MarketSQL.CREATE_MARKET);
            preparedStatement.setString(1, market.getName());
            preparedStatement.setString(2,market.getCity());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
