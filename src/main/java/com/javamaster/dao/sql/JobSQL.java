package com.javamaster.dao.sql;

public class JobSQL {

    public static final String CREATE_JOB = "INSERT INTO test(store,title) VALUES (?, ?);";

    public static final String SHOW_JOB = "SELECT * FROM test;";

}
