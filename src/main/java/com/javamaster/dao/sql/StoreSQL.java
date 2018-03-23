package com.javamaster.dao.sql;

public class StoreSQL {

    public static final String CREATE_MARKET = "INSERT INTO market " +
            " (name, city) " + " VALUES(?, ?);";

    public static final String GET_ALL_MARKET = "SELECT * FROM market;";

    public static final String UPDATE_MARKET_ID = "UPDATE market SET name = ?, city = ? WHERE id = ?;";

    public static final String DELETE_MARKET_BY_ID = "DELETE FROM market WHERE id = ?;";
}
