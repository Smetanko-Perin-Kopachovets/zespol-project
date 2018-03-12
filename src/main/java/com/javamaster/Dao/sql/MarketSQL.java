package com.javamaster.Dao.sql;

public class MarketSQL {
    private static final String CREATE_MARKET = "INSERT INTO market " +
            " (name, city) " + " VALUES(?, ?);";

    public static final String GET_ALL_MARKET = "SELECT * FROM market;";
}
