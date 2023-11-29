package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;


public class DBController <T>{
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


    public void insertAircraft(AirPlane aircraft){
        try {
            String statement = "INSERT INTO AIRCRAFT (name) VALUES (?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setString(1, aircraft.getAircraftName());     
            flightQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
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


    public void insertGuestUser(GuestUser gUser){
        try {
            String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, 1); //fixed access level
            flightQuery.setNull(2, java.sql.Types.INTEGER);
            flightQuery.setString(3, gUser.getName().getFirstName());
            flightQuery.setString(4, gUser.getName().getLastName());
            flightQuery.setString(5, (gUser.getAddress().getNumber() + " " + gUser.getAddress().getStreet()));
            flightQuery.setString(6, gUser.getEmail()); 
            flightQuery.setNull(7, java.sql.Types.INTEGER);
            flightQuery.setDate(8, java.sql.Date.valueOf((LocalDate) gUser.getBirthDate().getDate()));
            flightQuery.setString(9, gUser.getPhoneNumber());
            flightQuery.setNull(10, java.sql.Types.FLOAT);      
            flightQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRegisteredUser(RegisteredUser rUser){
        try {
            String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, 2); //fixed access level
            flightQuery.setInt(2, rUser.getPromotionID());
            flightQuery.setString(3, rUser.getName().getFirstName());
            flightQuery.setString(4, rUser.getName().getLastName());
            flightQuery.setString(5, (rUser.getAddress().getNumber() + " " + rUser.getAddress().getStreet()));
            flightQuery.setString(6, rUser.getEmail()); 
            flightQuery.setString(7, rUser.getPassword());
            flightQuery.setDate(8, java.sql.Date.valueOf((LocalDate) rUser.getBirthDate().getDate()));
            flightQuery.setString(9, rUser.getPhoneNumber());
            flightQuery.setFloat(10, rUser.getBalance());      
            flightQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCrewUser(Crew cUser){
        try {
            String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, 3); //fixed access level
            flightQuery.setNull(2, java.sql.Types.INTEGER);
            flightQuery.setString(3, cUser.getName().getFirstName());
            flightQuery.setString(4, cUser.getName().getLastName());
            flightQuery.setString(5, (cUser.getAddress().getNumber() + " " + cUser.getAddress().getStreet()));
            flightQuery.setString(6, cUser.getEmail()); 
            flightQuery.setString(7, cUser.getPassword());
            flightQuery.setDate(8, java.sql.Date.valueOf((LocalDate) cUser.getBirthDate().getDate()));
            flightQuery.setString(9, cUser.getPhoneNumber());
            flightQuery.setNull(10, java.sql.Types.FLOAT);     
            flightQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAdminUser(Admin aUser){
        try {
            String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, 4); //fixed access level
            flightQuery.setNull(2, java.sql.Types.INTEGER);
            flightQuery.setString(3, aUser.getName().getFirstName());
            flightQuery.setString(4, aUser.getName().getLastName());
            flightQuery.setString(5, (aUser.getAddress().getNumber() + " " + aUser.getAddress().getStreet()));
            flightQuery.setString(6, aUser.getEmail()); 
            flightQuery.setString(7, aUser.getPassword());
            flightQuery.setDate(8, java.sql.Date.valueOf((LocalDate) aUser.getBirthDate().getDate()));
            flightQuery.setString(9, aUser.getPhoneNumber());
            flightQuery.setNull(10, java.sql.Types.FLOAT);     
            flightQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeUser(int userID){
        try {
            String statement = "DELETE FROM TICKET WHERE userID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, userID);
            flightQuery.executeUpdate();

            statement = "UPDATE FLIGHT SET crewMember1 = null WHERE crewMember1 = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, userID);
            flightQuery.executeUpdate();

            statement = "UPDATE FLIGHT SET crewMember2 = null WHERE crewMember2 = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, userID);
            flightQuery.executeUpdate();

            statement = "UPDATE FLIGHT SET crewMember2 = null WHERE crewMember2 = ?";
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
    
    public void removeFlight(int flightID){
        try {
            String statement = "DELETE FROM TICKET WHERE flightID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, flightID);
            flightQuery.executeUpdate();
            
            statement = "DELETE FROM FLIGHT WHERE flightID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, flightID);
            flightQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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
            String statement = "UPDATE ALLUSERS SET promotionID = null WHERE promotionID = ?";
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

    public void removeSeat(int seatID){
        try {
            String statement = "UPDATE TICKET SET seatID = null WHERE seatID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, seatID);
            flightQuery.executeUpdate();

            statement = "DELETE FROM SEAT WHERE seatID = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, seatID);
            flightQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTicket(Ticket ticket){
        try {
            String statement = "INSERT INTO TICKET (aircraftID, flightID, seatID, userID) VALUES (?,?,?,?)";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, ticket.getAircraftID());
            flightQuery.setInt(2, ticket.getFlightID());
            flightQuery.setInt(3, ticket.getSeatID());
            flightQuery.setInt(4, ticket.getUserID());   
            flightQuery.executeUpdate();

            // Change availability of seat
            String updateStatement = "UPDATE SEAT SET available = 1 WHERE seatID = ?";
            flightQuery = flightConnect.prepareStatement(updateStatement);
            flightQuery.setInt(1, ticket.getSeatID());
            flightQuery.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTicket(int ticketNumber){
        try {
            //find the seat ID from the ticketNumber
            String statement = "SELECT seatID FROM ticket WHERE ticketNumber = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, ticketNumber);
            flightResult = flightQuery.executeQuery();

            // change availability of seat
            if (flightResult.next()) { // Check if a row was found
                int seatID = flightResult.getInt("seatID");

                // Change availability of seat
                String updateStatement = "UPDATE SEAT SET available = 0 WHERE seatID = ?";
                flightQuery = flightConnect.prepareStatement(updateStatement);
                flightQuery.setInt(1, seatID);
                flightQuery.executeUpdate();
            } else {
                System.out.println("Ticket not found for ticketNumber: " + ticketNumber);
            }
            
            statement = "DELETE FROM TICKET WHERE ticketNumber = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, ticketNumber);
            flightQuery.executeUpdate();
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

    public ResultSet selectTableFromAttribute(String tableName, String attributeName, T value){
        try {
            String statement = "SELECT * FROM " + tableName + " WHERE " + attributeName + " = ?";
            flightQuery = flightConnect.prepareStatement(statement);

            // Set the parameter value based on the type
            if (value instanceof String) {
                flightQuery.setString(1, (String) value);
            } else if (value instanceof Integer) {
                flightQuery.setInt(1, (Integer) value);
            } else if (value instanceof Boolean) {
                flightQuery.setBoolean(1, (Boolean) value);
            } else if (value instanceof Float) {
                flightQuery.setFloat(1, (Float) value);
            } else if (value instanceof Double) {
                flightQuery.setDouble(1, (Double) value);
            } else if (value instanceof LocalDate) {
                flightQuery.setDate(1, java.sql.Date.valueOf((LocalDate) value));
            } else if (value instanceof Time) {
                flightQuery.setTime(1, (Time) value);
            } else {
                throw new SQLException("Unsupported data type");
            }

            flightResult = flightQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    // public ResultSet selectTableFromTwoAttributes(String tableName, String attribute1, T value1, String attribute2, T value2){
    //     try {
    //         // Use a placeholder for the value in the SQL statement
    //         String statement = "SELECT * FROM " + tableName + " WHERE " + attribute1 + " = ? AND A";
    //         flightQuery = flightConnect.prepareStatement(statement);
    //         // Set the parameter value based on the type
    //         if (value instanceof String) {
    //             flightQuery.setString(1, (String) value);
    //         } else if (value instanceof Integer) {
    //             flightQuery.setInt(1, (Integer) value);
    //         } else if (value instanceof Boolean) {
    //             flightQuery.setBoolean(1, (Boolean) value);
    //         } else if (value instanceof Float) {
    //             flightQuery.setFloat(1, (Float) value);
    //         } else if (value instanceof Double) {
    //             flightQuery.setDouble(1, (Double) value);
    //         } else if (value instanceof LocalDate) {
    //             flightQuery.setDate(1, java.sql.Date.valueOf((LocalDate) value));
    //         } else if (value instanceof Time) {
    //             flightQuery.setTime(1, (Time) value);
    //         } else {
    //             throw new SQLException("Unsupported data type");
    //         }
    //         flightResult = flightQuery.executeQuery();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return flightResult;
    // }


    public ResultSet selectUser(String email, String password) {
        try {
            String statement = "SELECT * FROM ALLUSERS WHERE email=" + email + " AND " + "password=" + password + ";";
            flightQuery = flightConnect.prepareStatement(statement);
            flightResult = flightQuery.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }


    public void close(){
        try {
            flightResult.close();
            flightConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printResultSet(ResultSet resultSet) {
    try {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print rows
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public static void main(String[] args) {
        DBController temp = DBController.getOnlyInstance();
        if (temp.getFlightConnect() != null) {
            System.out.println("Connection successful");
        }
        else {System.out.println("Connection unsuccessful");}

        // ------------ TESTING REMOVE FUNCTIONS ------------
        // temp.removeAircraft(1);
        // temp.removeFlight(4);
        // temp.removePromotion(2);
        // temp.removeSeat(50);
        // temp.removeTicket(1);
        // temp.removeUser(41);

        // ------------ TESTING SELECT FUNCTIONS ------------
        // temp.selectTableFromAttribute("FLIGHT", "departLocation", "New York");
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("AIRCRAFT", "aircraftID", 1);
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("SEAT", "available", true);
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("ALLUSERS", "balance", 75.5);
        // temp.printResultSet(temp.flightResult);

        // Date newDate = new Date(01,06,2023);
        // temp.selectTableFromAttribute("PROMOTIONS", "startDate", newDate.getDate());
        // temp.printResultSet(temp.flightResult);

        // TTime newtime = new TTime(10, 0, 0);
        // temp.selectTableFromAttribute("FLIGHT", "arriveTime", newtime.getTime());
        // temp.printResultSet(temp.flightResult);

        // ------------ TESTING INSERT FUNCTIONS ------------
        AirPlane newPlane = new AirPlane("test");
        temp.insertAircraft(newPlane);

        Ticket newTicket = new Ticket(1, 1, 5, 26);
        temp.insertTicket(newTicket);

        Name userName = new Name("Isha", "Haider");
        Address userAddress = new Address("213", "Sherwood Gate");
        Date userDate = new Date(01,06,2002);
        GuestUser newGuestUser = new GuestUser(userName, userAddress,  "isha.haider@ucalgary.ca", userDate , "587-890-8532");
        temp.insertGuestUser(newGuestUser);
    }
}