package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnect {
        /*
     * This class is used to connect to the SQL database
     * and add rows to the database
     * @author Abdulrahman Bekhit
     */
    private static Connection flightConnect;
    private static ResultSet flightResult;

    /*
     * empty constructor
     */
    public SQLConnect(){
    }

    /**
     * Establishes a connection with SQL DataBase 
     * has no inputs and no return
     * @throws SQLException
     */
    public void createConnection(){
        try{
            flightConnect = DriverManager.getConnection("jdbc:mysql://localhost/FS","oop", "password");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    
}
