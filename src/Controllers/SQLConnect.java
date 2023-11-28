package src.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnect {
        /*
     * This class is used to connect to the SQL database
     * and add rows to the database
     */
    private static Connection flightConnect;
    private static ResultSet flightResult;

    private static final String SQL_URL = "jdbc:mysql://localhost:3306/FS";
    private static final String USER = "oop";
    private static final String PASS = "password";

    /*
     * empty constructor
     */
    public SQLConnect(){
        createConnection(SQL_URL,USER, PASS);
    }

    /**
     * Establishes a connection with SQL DataBase 
     * has no inputs and no return
     * @throws SQLException
     */
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
        SQLConnect temp = new SQLConnect();
        if (temp.getFlightConnect() != null) {
            System.out.println("Connection successful");
        }
        else {System.out.println("Connection unsuccessful");}
    }

    
}
