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

    public ArrayList<Market> getAllById(){
        return marketDao.getAllMarket();
    }
}
