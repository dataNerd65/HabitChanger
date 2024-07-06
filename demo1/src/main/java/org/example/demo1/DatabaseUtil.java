package org.example.demo1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try{
             return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException e){
            //log and /or rethrow as appropriate
            e.printStackTrace();
            return null;

        }

    }
}
