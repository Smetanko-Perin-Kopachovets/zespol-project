package com.javamaster.dao.sql;

public class MarketSQL {

    public static final String CREATE_MARKET = "INSERT INTO market " +
            " (name, city) " + " VALUES(?, ?);";

    public static final String GET_ALL_MARKET = "SELECT * FROM market;";
}
