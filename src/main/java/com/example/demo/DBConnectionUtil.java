package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectionUtil {



    public static Connection getConnection(){


        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConnectionInfo.URL,ConnectionInfo.USERNAME,ConnectionInfo.PASSWORD);

            log.info("connection = {} " ,connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;

    }


}
