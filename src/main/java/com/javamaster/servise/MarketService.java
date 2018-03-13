package com.javamaster.servise;


import com.javamaster.Dao.MarketDao;
import com.javamaster.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarketService {
    private MarketDao marketDao;

    @Autowired
    public MarketService(MarketDao marketDao){
        this.marketDao = marketDao;
    }

    public void createMarket(String name, String city){
        Market market = new Market(name, city);
        marketDao.Createmarket(market);

    }

    public void updateMarket(Market market){
        marketDao.Updatemarket(market);
    }


    public ArrayList<Market> getAllById(){
        return marketDao.getAllMarket();
    }
}
