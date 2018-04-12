package com.javamaster.dao.sql;

import com.sun.istack.internal.FinalArrayList;

public class JobTypeSQL {

    public static final String CREATE_JOBTYPE = "INSERT INTO jobtype(type, pricePerHour, market_id_fk) VALUES (?, ?, ?);";

    public static final String GET_SHOP_JOBTYPE_ID = "SELECT * FROM market m INNER JOIN jobtype j" +
            "ON m.id = j.market_id_fk" +
            "WHERE m.id = ?;";

    public static final String GET_ALL_JOBTYPE = "SELECT * FROM jobtype;";

    public static final String DELETE_BY_ID = "DELETE FROM jobtype WHERE id = ?;";

}
