package com.javamaster.config;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Connect {
private static final String URL = "jdbc:postgresql://baasu.db.elephantsql.com:5432/iurszlro";
private static final String USER = "iurszlro";
private static final String PASSWORD = "_7-DyTJ7CVZ3qwzI8kDOYIp0DiHAeqBC";

public static Connection connection;

public static Connection getConnection() {
    if (connection != null) {
        return connection;
    }

    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
                e.printStackTrace();
    }

    try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
                e.printStackTrace();
    }

    return connection;
}
}
