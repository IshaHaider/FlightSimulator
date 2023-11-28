package src.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import src.Domain.*;


public class DBController {
    private static final String SQL_URL = "jdbc:mysql://localhost:3306/FS";
    private static final String USER = "oop";
    private static final String PASS = "password";

    private static DBController onlyInstance;
    private static Connection flightConnect;
    private ResultSet flightResult;
    private PreparedStatement flightQuery;

    private DBController(){
        createConnection(SQL_URL, USER, PASS);
    }

    public static DBController getOnlyInstance(){
        if (onlyInstance == null){
            onlyInstance = new DBController();
        }
        return onlyInstance;
    }

    public void createConnection(String url, String user, String pw){
        try{
            flightConnect = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getFlightConnect(){ return flightConnect; }


    // public void insertAircraft(Airplane aircraft){
    //     try {
    //         String statement = "INSERT INTO AIRCRAFT (name) VALUES (?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setString(1, aircraft.gegetAircraftNametFname());     
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    public void removeAircraft(int aircraftID){
        try {
            String statement = "DELETE FROM TICKET WHERE aircraftID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, aircraftID);
            flightQuery.executeUpdate();

            statement = "DELETE FROM FLIGHT WHERE aircraftID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, aircraftID);
            flightQuery.executeUpdate();

            statement = "DELETE FROM SEAT WHERE aircraftID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, aircraftID);
            flightQuery.executeUpdate();

            statement = "DELETE FROM AIRCRAFT WHERE aircraftID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, aircraftID);
            flightQuery.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // public void insertGuestUser(GuestUser gUser){
    //     try {
    //         String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, 1); //fixed access level
    //         flightQuery.setInt(2, null);
    //         flightQuery.setString(3, gUser.getFirstName());
    //         flightQuery.setString(4, gUser.getLastName());
    //         flightQuery.setString(5, gUser.getAddress());
    //         flightQuery.setInt(6, gUser.getEmail()); 
    //         flightQuery.setInt(7, null);
    //         flightQuery.setDate(8, gUser.getBirthDate());
    //         flightQuery.setString(9, gUser.getPhoneNumber());
    //         flightQuery.setFloat(10, null);      
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void insertRegisteredUser(RegisteredUser rUser){
    //     try {
    //         String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, 2); //fixed access level
    //         flightQuery.setInt(2, rUser.getPromotionID());
    //         flightQuery.setString(3, rUser.getFirstName());
    //         flightQuery.setString(4, rUser.getLastName());
    //         flightQuery.setString(5, rUser.getAddress());
    //         flightQuery.setInt(6, rUser.getEmail()); 
    //         flightQuery.setInt(7, rUser.getPassword());
    //         flightQuery.setDate(8, rUser.getBirthDate());
    //         flightQuery.setString(9, rUser.getPhoneNumber());
    //         flightQuery.setFloat(10, rUser.getBalance());      
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // } 
    
    // public void insertCrewUser(Crew cUser){
    //     try {
    //         String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, 3); //fixed access level
    //         flightQuery.setInt(2, null);
    //         flightQuery.setString(3, cUser.getFirstName());
    //         flightQuery.setString(4, cUser.getLastName());
    //         flightQuery.setString(5, cUser.getAddress());
    //         flightQuery.setInt(6, cUser.getEmail()); 
    //         flightQuery.setInt(7, cUser.getPassword());
    //         flightQuery.setDate(8, cUser.getBirthDate());
    //         flightQuery.setString(9, cUser.getPhoneNumber());
    //         flightQuery.setFloat(10, null);      
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void insertAdminUser(Admin aUser){
    //     try {
    //         String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, 4); //fixed access level
    //         flightQuery.setInt(2, null);
    //         flightQuery.setString(3, aUser.getFirstName());
    //         flightQuery.setString(4, aUser.getLastName());
    //         flightQuery.setString(5, aUser.getAddress());
    //         flightQuery.setInt(6, aUser.getEmail()); 
    //         flightQuery.setInt(7, aUser.getPassword());
    //         flightQuery.setDate(8, aUser.getBirthDate());
    //         flightQuery.setString(9, aUser.getPhoneNumber());
    //         flightQuery.setFloat(10, null);      
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    public void removeUser(int userID){
        try {
            String statement = "DELETE FROM TICKET WHERE userID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, userID);
            flightQuery.executeUpdate();


            statement = "DELETE FROM ALLUSERS WHERE userID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, userID);
            flightQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // public void insertFlight(Flight flight){
    //     try {
    //         String statement = "INSERT INTO FLIGHT (aircraftID, departDate, departTime, departLocation, arriveDate, arriveTime, arriveLocation, flightStatus, cost, meal, crewMember1, crewMember2, crewMember3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, flight.getAirCraftID()); //fixed access level
    //         flightQuery.setDate(2, flight.getDepartureDate());
    //         flightQuery.setTime(3, flight.getDepartureTime());
    //         flightQuery.setString(4, flight.getDepartureLocation());
    //         flightQuery.setDate(5, flight.getArrivalDate());
    //         flightQuery.setTime(6, flight.getArrivalTime()); 
    //         flightQuery.setString(7, flight.getArrivalLocation());
    //         flightQuery.setString(8, flight.getFlightStatus());
    //         flightQuery.setFloat(9, flight.getCost());
    //         flightQuery.setBoolean(10, flight.getMeal());    
    //         flightQuery.setInt(11, flight.getCrewMember1());
    //         flightQuery.setInt(12, flight.getCrewMember2());
    //         flightQuery.setInt(13, flight.getCrewMember3());     
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    // public void removeFlight(int flightID){
    //     try {
    //         String statement = "DELETE FROM FLIGHT WHERE flightID = ?";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, flightID);
    //         flightQuery.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }


    // public void insertPromotion(Promotions promotion){
    //     try {
    //         String statement = "INSERT INTO PROMOTION (name, discount, startDate, endDate) VALUES (?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setString(1, promotion.getName()); //fixed access level
    //         flightQuery.setString(2, promotion.getDiscountPercentage());
    //         flightQuery.setDate(3, promotion.getStartDate());
    //         flightQuery.setDate(4, promotion.getEndDate());  
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    public void removePromotion(int promotionID){
        try {
            String statement = "DELETE FROM ALLUSERS WHERE promotionID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, promotionID);
            flightQuery.executeUpdate();
            
            statement = "DELETE FROM PROMOTIONS WHERE promotionID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, promotionID);
            flightQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // public void insertSeat(Seat seat){
    //     try {
    //         String statement = "INSERT INTO SEAT (aircraftID, seatName, class, cost, baggage, available) VALUES (?,?,?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, seat.getAircraftID()); //fixed access level
    //         flightQuery.setString(2, seat.getSeatName());
    //         flightQuery.setString(3, seat.getClass());
    //         flightQuery.setFloat(4, seat.getCost());  
    //         flightQuery.setBoolean(5, seat.getBaggage());  
    //         flightQuery.setBoolean(6, seat.getAvailable());  
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void removeSeat(int seatID){
    //     try {
    //         String statement = "DELETE FROM SEAT WHERE seatID = ?";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, seatID);
    //         flightQuery.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }


    // public void insertTicket(Ticket ticket){
    //     try {
    //         String statement = "INSERT INTO TICKET (aircraftID, flightID, seatID, userID) VALUES (?,?,?,?)";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, ticket.getAircraftID()); //fixed access level
    //         flightQuery.setInt(2, ticket.getFlightID());
    //         flightQuery.setInt(3, ticket.getSeatID());
    //         flightQuery.setInt(4, ticket.getUserID());   
    //         flightQuery.executeUpdate();
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void removeTicket(int ticketNumber){
    //     try {
    //         String statement = "DELETE FROM TICKET WHERE ticketNumber = ?";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         flightQuery.setInt(1, ticketNumber);
    //         flightQuery.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    
    public void close(){
        try {
            flightResult.close();
            flightConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectTable(String tableName) {
        try {
            String statement = "SELECT * FROM " + tableName + ";";
            flightQuery = flightConnect.prepareStatement(statement);
            flightResult = flightQuery.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public ResultSet selectSeatsFromAircraftID(int acID) {
        try {
            String statement = "SELECT * FROM SEAT WHERE aircraftID=" + Integer.toString(acID) + ";";
            flightQuery = flightConnect.prepareStatement(statement);
            flightResult = flightQuery.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public ResultSet selectUSER(String email, String password) {
        try {
            String statement = "SELECT * FROM ALLUSERS WHERE email=" + email + " AND " + "password=" + password + ";";
            flightQuery = flightConnect.prepareStatement(statement);
            flightResult = flightQuery.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public static void main(String[] args) {
        DBController temp = DBController.getOnlyInstance();
        if (temp.getFlightConnect() != null) {
            System.out.println("Connection successful");
        }
        else {System.out.println("Connection unsuccessful");}
        temp.removeAircraft(1);
        temp.removePromotion(2);
        // temp.removeUser(2);
    }




}