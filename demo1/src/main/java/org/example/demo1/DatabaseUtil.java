package org.example.demo1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "DB_URL";
    private static final String USER = "DB_USER";
    private static final String PASSWORD = "DB_PASSWORD";

    public static Connection getConnection(){
        try{
            DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException e){
            //log and /or rethrow as appropriate
            e.printStackTrace();
        }
        return null;
    }
}
