package src.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBController
 */
public record DBController() {
    private static Connection flightConnect;
    private static ResultSet flightResult;

    private static final String SQL_URL = "jdbc:mysql://localhost:3306/FS";
    private static final String USER = "oop";
    private static final String PASS = "password";


    public DBController(){
        createConnection(SQL_URL,USER, PASS);
    }

    public void createConnection(String url, String user, String pw){
        try{
            flightConnect = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getFlightConnect(){
        return flightConnect;
    }

    public static void main(String[] args) {
        DBController temp = new DBController();
        if (temp.getFlightConnect() != null) {
            System.out.println("Connection successful");
        }
        else {System.out.println("Connection unsuccessful");}
    }

}