package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DBController<T> implements Subject {
    private static final String SQL_URL = "jdbc:mysql://localhost:3306/FS";
    private static final String USER = "oop";
    private static final String PASS = "password";

    private static DBController onlyInstance;
    private static Connection flightConnect;
    private ResultSet flightResult;
    private PreparedStatement flightQuery;

    private ArrayList<Observer> observers;

    private DBController() {
        createConnection(SQL_URL, USER, PASS);
        observers = new ArrayList<Observer>();
    }

    public static DBController getOnlyInstance() {
        if (onlyInstance == null) {
            onlyInstance = new DBController();
        }
        return onlyInstance;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
        o.update();
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
        o.update();
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer o = observers.get(i);
            o.update();
        }
    }

    public void createConnection(String url, String user, String pw) {
        try {
            flightConnect = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getFlightConnect() {
        return flightConnect;
    }

    public void close() {
        try {
            flightResult.close();
            flightConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* INSERT FUNCTIONS */

    public void insertAircraft(AirPlane aircraft) {
        try {
            if (aircraft.getAircraftID() != 0) {
                String statement = "INSERT INTO AIRCRAFT (aircraftID, name) VALUES (?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, aircraft.getAircraftID());
                flightQuery.setString(2, aircraft.getAircraftName());
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO AIRCRAFT (name) VALUES (?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setString(1, aircraft.getAircraftName());
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGuestUser(GuestUser gUser) {
        try {
            if (gUser.getUserID() != 0) {
                String statement = "INSERT INTO ALLUSERS (userID, accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, gUser.getUserID());
                flightQuery.setInt(2, 1); // fixed access level
                flightQuery.setNull(3, java.sql.Types.INTEGER);
                flightQuery.setString(4, gUser.getName().getFirstName());
                flightQuery.setString(5, gUser.getName().getLastName());
                flightQuery.setString(6, (gUser.getAddress().getNumber() + " " + gUser.getAddress().getStreet()));
                flightQuery.setString(7, gUser.getEmail());
                flightQuery.setNull(8, java.sql.Types.INTEGER);
                flightQuery.setDate(9, java.sql.Date.valueOf(gUser.getBirthDate()));
                flightQuery.setString(10, gUser.getPhoneNumber());
                flightQuery.setNull(11, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, 1); // fixed access level
                flightQuery.setNull(2, java.sql.Types.INTEGER);
                flightQuery.setString(3, gUser.getName().getFirstName());
                flightQuery.setString(4, gUser.getName().getLastName());
                flightQuery.setString(5, (gUser.getAddress().getNumber() + " " + gUser.getAddress().getStreet()));
                flightQuery.setString(6, gUser.getEmail());
                flightQuery.setNull(7, java.sql.Types.INTEGER);
                flightQuery.setDate(8, java.sql.Date.valueOf(gUser.getBirthDate()));
                flightQuery.setString(9, gUser.getPhoneNumber());
                flightQuery.setNull(10, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRegisteredUser(RegisteredUser rUser) {
        try {
            if (rUser.getUserID() != 0) {
                String statement = "INSERT INTO ALLUSERS (userID, accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, rUser.getUserID());
                flightQuery.setInt(2, 2); // fixed access level
                flightQuery.setInt(3, rUser.getPromotionID());
                flightQuery.setString(4, rUser.getName().getFirstName());
                flightQuery.setString(5, rUser.getName().getLastName());
                flightQuery.setString(6, (rUser.getAddress().getNumber() + " " + rUser.getAddress().getStreet()));
                flightQuery.setString(7, rUser.getEmail());
                flightQuery.setString(8, rUser.getPassword());
                flightQuery.setDate(9, java.sql.Date.valueOf(rUser.getBirthDate()));
                flightQuery.setString(10, rUser.getPhoneNumber());
                flightQuery.setFloat(11, rUser.getBalance());
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, 2); // fixed access level
                flightQuery.setInt(2, rUser.getPromotionID());
                flightQuery.setString(3, rUser.getName().getFirstName());
                flightQuery.setString(4, rUser.getName().getLastName());
                flightQuery.setString(5, (rUser.getAddress().getNumber() + " " + rUser.getAddress().getStreet()));
                flightQuery.setString(6, rUser.getEmail());
                flightQuery.setString(7, rUser.getPassword());
                flightQuery.setDate(8, java.sql.Date.valueOf(rUser.getBirthDate()));
                flightQuery.setString(9, rUser.getPhoneNumber());
                flightQuery.setFloat(10, rUser.getBalance());
                flightQuery.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCrewUser(Crew cUser) {
        try {
            if (cUser.getUserID() != 0) {
                String statement = "INSERT INTO ALLUSERS (userID, accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, cUser.getUserID());
                flightQuery.setInt(2, 3); // fixed access level
                flightQuery.setNull(3, java.sql.Types.INTEGER);
                flightQuery.setString(4, cUser.getName().getFirstName());
                flightQuery.setString(5, cUser.getName().getLastName());
                flightQuery.setString(6, (cUser.getAddress().getNumber() + " " + cUser.getAddress().getStreet()));
                flightQuery.setString(7, cUser.getEmail());
                flightQuery.setString(8, cUser.getPassword());
                flightQuery.setDate(9, java.sql.Date.valueOf(cUser.getBirthDate()));
                flightQuery.setString(10, cUser.getPhoneNumber());
                flightQuery.setNull(11, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, 3); // fixed access level
                flightQuery.setNull(2, java.sql.Types.INTEGER);
                flightQuery.setString(3, cUser.getName().getFirstName());
                flightQuery.setString(4, cUser.getName().getLastName());
                flightQuery.setString(5, (cUser.getAddress().getNumber() + " " + cUser.getAddress().getStreet()));
                flightQuery.setString(6, cUser.getEmail());
                flightQuery.setString(7, cUser.getPassword());
                flightQuery.setDate(8, java.sql.Date.valueOf(cUser.getBirthDate()));
                flightQuery.setString(9, cUser.getPhoneNumber());
                flightQuery.setNull(10, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAdminUser(Admin aUser) {
        try {
            if (aUser.getUserID() != 0) {
                String statement = "INSERT INTO ALLUSERS (userID, accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, aUser.getUserID());
                flightQuery.setInt(2, 4); // fixed access level
                flightQuery.setNull(3, java.sql.Types.INTEGER);
                flightQuery.setString(4, aUser.getName().getFirstName());
                flightQuery.setString(5, aUser.getName().getLastName());
                flightQuery.setString(6, (aUser.getAddress().getNumber() + " " + aUser.getAddress().getStreet()));
                flightQuery.setString(7, aUser.getEmail());
                flightQuery.setString(8, aUser.getPassword());
                flightQuery.setDate(9, java.sql.Date.valueOf(aUser.getBirthDate()));
                flightQuery.setString(10, aUser.getPhoneNumber());
                flightQuery.setNull(11, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO ALLUSERS (accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, 4); // fixed access level
                flightQuery.setNull(2, java.sql.Types.INTEGER);
                flightQuery.setString(3, aUser.getName().getFirstName());
                flightQuery.setString(4, aUser.getName().getLastName());
                flightQuery.setString(5, (aUser.getAddress().getNumber() + " " + aUser.getAddress().getStreet()));
                flightQuery.setString(6, aUser.getEmail());
                flightQuery.setString(7, aUser.getPassword());
                flightQuery.setDate(8, java.sql.Date.valueOf(aUser.getBirthDate()));
                flightQuery.setString(9, aUser.getPhoneNumber());
                flightQuery.setNull(10, java.sql.Types.FLOAT);
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFlight(Flight flight) {
        try {
            if (flight.getFlightID() != 0) {
                String statement = "INSERT INTO FLIGHT (flightID, aircraftID, departDate, departTime, departLocation, arriveDate, arriveTime, arriveLocation, flightStatus, cost, meal, crewMember1, crewMember2, crewMember3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, flight.getFlightID());
                flightQuery.setInt(2, flight.getAircraftID());
                flightQuery.setDate(3, java.sql.Date.valueOf(flight.getDepartDate()));
                flightQuery.setTime(4, java.sql.Time.valueOf(flight.getDepartTime()));
                flightQuery.setString(5, flight.getDepartLocation());
                flightQuery.setDate(6, java.sql.Date.valueOf(flight.getArriveDate()));
                flightQuery.setTime(7, java.sql.Time.valueOf(flight.getArriveTime()));
                flightQuery.setString(8, flight.getArriveLocation());
                flightQuery.setString(9, flight.getFlightStatus().toString());
                flightQuery.setFloat(10, flight.getCost());
                flightQuery.setBoolean(11, flight.getMeal());

                // flight.getCrewMember1() ? flightQuery.setInt(11, flight.getCrewMember1()); :
                // flightQuery.setObject(11, null);
                if (flight.getCrewMember1() != 0) {
                    flightQuery.setInt(12, flight.getCrewMember1());
                } else {
                    flightQuery.setNull(12, java.sql.Types.INTEGER);
                }

                if (flight.getCrewMember2() != 0) {
                    flightQuery.setInt(13, flight.getCrewMember2());
                } else {
                    flightQuery.setNull(13, java.sql.Types.INTEGER);
                }

                if (flight.getCrewMember3() != 0) {
                    flightQuery.setInt(14, flight.getCrewMember3());
                } else {
                    flightQuery.setNull(14, java.sql.Types.INTEGER);
                }
                flightQuery.executeUpdate();

            } else {
                String statement = "INSERT INTO FLIGHT (aircraftID, departDate, departTime, departLocation, arriveDate, arriveTime, arriveLocation, flightStatus, cost, meal, crewMember1, crewMember2, crewMember3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, flight.getAircraftID());
                flightQuery.setDate(2, java.sql.Date.valueOf(flight.getDepartDate()));
                flightQuery.setTime(3, java.sql.Time.valueOf(flight.getDepartTime()));
                flightQuery.setString(4, flight.getDepartLocation());
                flightQuery.setDate(5, java.sql.Date.valueOf(flight.getArriveDate()));
                flightQuery.setTime(6, java.sql.Time.valueOf(flight.getArriveTime()));
                flightQuery.setString(7, flight.getArriveLocation());
                flightQuery.setString(8, flight.getFlightStatus().toString());
                flightQuery.setFloat(9, flight.getCost());
                flightQuery.setBoolean(10, flight.getMeal());

                // flight.getCrewMember1() ? flightQuery.setInt(11, flight.getCrewMember1()); :
                // flightQuery.setObject(11, null);
                if (flight.getCrewMember1() != 0) {
                    flightQuery.setInt(11, flight.getCrewMember1());
                } else {
                    flightQuery.setNull(11, java.sql.Types.INTEGER);
                }

                if (flight.getCrewMember2() != 0) {
                    flightQuery.setInt(12, flight.getCrewMember2());
                } else {
                    flightQuery.setNull(12, java.sql.Types.INTEGER);
                }

                if (flight.getCrewMember3() != 0) {
                    flightQuery.setInt(13, flight.getCrewMember3());
                } else {
                    flightQuery.setNull(13, java.sql.Types.INTEGER);
                }
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPromotion(Promotions promotion) {
        try {
            if (promotion.getPromotionID() != 0) {
                String statement = "INSERT INTO PROMOTIONS (promotionID, name, discount, startDate, endDate) VALUES (?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, promotion.getPromotionID());
                flightQuery.setString(2, promotion.getName());
                flightQuery.setString(3, promotion.getDiscount());
                flightQuery.setDate(4, java.sql.Date.valueOf(promotion.getStartDate()));
                flightQuery.setDate(5, java.sql.Date.valueOf(promotion.getEndDate()));
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO PROMOTIONS (name, discount, startDate, endDate) VALUES (?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setString(1, promotion.getName());
                flightQuery.setString(2, promotion.getDiscount());
                flightQuery.setDate(3, java.sql.Date.valueOf(promotion.getStartDate()));
                flightQuery.setDate(4, java.sql.Date.valueOf(promotion.getEndDate()));
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSeat(Seat seat) {
        try {
            if (seat.getSeatID() != 0) {
                String statement = "INSERT INTO SEAT (seatID, aircraftID, seatName, class, cost, baggage, available) VALUES (?,?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, seat.getSeatID());
                flightQuery.setInt(2, seat.getAircraftID());
                flightQuery.setString(3, seat.getSeatName());
                flightQuery.setString(4, seat.getSeatClass().toString());
                flightQuery.setFloat(5, seat.getCost());
                flightQuery.setBoolean(6, seat.getBaggage());
                flightQuery.setBoolean(7, seat.getAvailable());
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO SEAT (aircraftID, seatName, class, cost, baggage, available) VALUES (?,?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, seat.getAircraftID());
                flightQuery.setString(2, seat.getSeatName());
                flightQuery.setString(3, seat.getSeatClass().toString());
                flightQuery.setFloat(4, seat.getCost());
                flightQuery.setBoolean(5, seat.getBaggage());
                flightQuery.setBoolean(6, seat.getAvailable());
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTicket(Ticket ticket) {
        try {
            if (ticket.getTicketNumber() != 0) {
                String statement = "INSERT INTO TICKET (ticketNumber, aircraftID, flightID, seatID, userID) VALUES (?,?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, ticket.getTicketNumber());
                flightQuery.setInt(2, ticket.getAircraftID());
                flightQuery.setInt(3, ticket.getFlightID());
                flightQuery.setInt(4, ticket.getSeatID());
                flightQuery.setInt(5, ticket.getUserID());
                flightQuery.executeUpdate();
            } else {
                String statement = "INSERT INTO TICKET (aircraftID, flightID, seatID, userID) VALUES (?,?,?,?)";
                flightQuery = flightConnect.prepareStatement(statement);
                flightQuery.setInt(1, ticket.getAircraftID());
                flightQuery.setInt(2, ticket.getFlightID());
                flightQuery.setInt(3, ticket.getSeatID());
                flightQuery.setInt(4, ticket.getUserID());
                flightQuery.executeUpdate();
            }

            // Change availability of seat
            onlyInstance.updateRow("SEAT", "available", false, ticket.getSeatID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* REMOVE FUNCTIONS */

    public void removeAircraft(int aircraftID) {
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

    public void removeUser(int userID) {
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

    public void removeFlight(int flightID) {
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

    public void removePromotion(int promotionID) {
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

    public void removeSeat(int seatID) {
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

    public String removeTicket(int ticketNumber) {
        try {
            // find the seat ID from the ticketNumber
            String statement = "SELECT seatID FROM ticket WHERE ticketNumber = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, ticketNumber);
            flightResult = flightQuery.executeQuery();

            // change availability of seat
            if (flightResult.next()) { // Check if a row was found
                int seatID = flightResult.getInt("seatID");

                // Change availability of seat
                // String updateStatement = "UPDATE SEAT SET available = 0 WHERE seatID = ?";
                String updateStatement = "UPDATE SEAT SET available = 1 WHERE seatID = ?";
                flightQuery = flightConnect.prepareStatement(updateStatement);
                flightQuery.setInt(1, seatID);
                flightQuery.executeUpdate();
            } else {
                System.out.println("Ticket not found for ticketNumber: " + ticketNumber);
                return "Ticket not found for ticketNumber: " + String.valueOf(ticketNumber);
            }

            statement = "DELETE FROM TICKET WHERE ticketNumber = ?";
            flightQuery = flightConnect.prepareStatement(statement);
            flightQuery.setInt(1, ticketNumber);
            flightQuery.executeUpdate();
            return "Ticket successfully cancelled.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error cancelling ticket.";
        }
    }

    /* SELECT FUNCTIONS */
    public ResultSet selectTable(String tableName) {
        try {
            String statement = "SELECT * FROM " + tableName + ";";
            flightQuery = flightConnect.prepareStatement(statement);
            flightResult = flightQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public ResultSet selectTableFromAttribute(String tableName, String attribute, T value) {
        try {
            String statement = "SELECT * FROM " + tableName + " WHERE " + attribute + " = ?" + ";";

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
            } else if (value instanceof LocalTime) {
                flightQuery.setTime(1, java.sql.Time.valueOf((LocalTime) value));
            } else {
                throw new SQLException("Unsupported data type");
            }

            flightResult = flightQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public ResultSet selectTableFromTwoAttributes(String tableName, String attribute1, T value1, String attribute2, T value2) {
        try {
            // Use a placeholder for the value in the SQL statement
            String statement = "SELECT * FROM " + tableName + " WHERE " + attribute1 + " = ? AND " + attribute2
                    + " = ?;";
            flightQuery = flightConnect.prepareStatement(statement);

            // Set the parameter value1 based on the type
            if (value1 instanceof String) {
                flightQuery.setString(1, (String) value1);
            } else if (value1 instanceof Integer) {
                flightQuery.setInt(1, (Integer) value1);
            } else if (value1 instanceof Boolean) {
                flightQuery.setBoolean(1, (Boolean) value1);
            } else if (value1 instanceof Float) {
                flightQuery.setFloat(1, (Float) value1);
            } else if (value1 instanceof Double) {
                flightQuery.setDouble(1, (Double) value1);
            } else if (value1 instanceof LocalDate) {
                flightQuery.setDate(1, java.sql.Date.valueOf((LocalDate) value1));
            } else if (value1 instanceof LocalTime) {
                flightQuery.setTime(1, java.sql.Time.valueOf((LocalTime) value1));
            } else {
                throw new SQLException("Unsupported data type");
            }

            // Set the parameter value2 based on the type
            if (value2 instanceof String) {
                flightQuery.setString(2, (String) value2);
            } else if (value2 instanceof Integer) {
                flightQuery.setInt(2, (Integer) value2);
            } else if (value2 instanceof Boolean) {
                flightQuery.setBoolean(2, (Boolean) value2);
            } else if (value2 instanceof Float) {
                flightQuery.setFloat(2, (Float) value2);
            } else if (value2 instanceof Double) {
                flightQuery.setDouble(2, (Double) value2);
            } else if (value2 instanceof LocalDate) {
                flightQuery.setDate(2, java.sql.Date.valueOf((LocalDate) value2));
            } else if (value2 instanceof LocalTime) {
                flightQuery.setTime(2, java.sql.Time.valueOf((LocalTime) value2));
            } else {
                throw new SQLException("Unsupported data type");
            }
            flightResult = flightQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightResult;
    }

    public ResultSet selectCurrentPromotions() {
        String sql = "SELECT * FROM Promotions WHERE startDate <= CURRENT_DATE() AND endDate >= CURRENT_DATE()";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = flightConnect.prepareStatement(sql);
            rs = pstmt.executeQuery();

            double maxDiscount = 0.0;
            while (rs.next()) {
                String discountString = rs.getString("discount"); // e.g., "10%"
                double discount = Double.parseDouble(discountString.replace("%", ""));
                if (discount > maxDiscount) {
                    maxDiscount = discount;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        validateAll();
        return rs;
    }

    /* UPDATE FUNCTIONS */

    public void updateRow(String tableName, String attributeToUpdate, T valueToUpdate, int id) {
        try {
            // example: UPDATE AIRCRAFT SET name = "Boeing 500" WHERE aircraftID = 1";
            ResultSet table = selectTable(tableName);
            ResultSetMetaData metadata = table.getMetaData();
            String primaryKeyName = metadata.getColumnName(1);

            // CHECK IF VALID TICKET
            if (tableName == "TICKET") {
                ResultSet tableTest = onlyInstance.selectTableFromAttribute(tableName, primaryKeyName, id);
                Ticket tick = new Ticket();
                while (tableTest.next()) {
                    if (attributeToUpdate == "aircraftID") {
                        tick.setAircraftID((Integer) valueToUpdate);
                        tick.setFlightID(tableTest.getInt("flightID"));
                        tick.setSeatID(tableTest.getInt("seatID"));
                        tick.setUserID(tableTest.getInt("userID"));
                    } else if (attributeToUpdate == "flightID") {
                        tick.setFlightID((Integer) valueToUpdate);
                        tick.setAircraftID(tableTest.getInt("aircraftID"));
                        tick.setSeatID(tableTest.getInt("seatID"));
                        tick.setUserID(tableTest.getInt("userID"));
                    } else if (attributeToUpdate == "seatID") {
                        tick.setSeatID((Integer) valueToUpdate);
                        tick.setAircraftID(tableTest.getInt("aircraftID"));
                        tick.setFlightID(tableTest.getInt("flightID"));
                        tick.setUserID(tableTest.getInt("userID"));
                    } else if (attributeToUpdate == "userID") {
                        tick.setUserID((Integer) valueToUpdate);
                        tick.setAircraftID(tableTest.getInt("aircraftID"));
                        tick.setFlightID(tableTest.getInt("flightID"));
                        tick.setSeatID(tableTest.getInt("seatID"));
                    }
                }
                int validation = validateTicket(tick);
                if (validation == 1) {
                    throw new SQLException("Error Inserting Ticket: the seat or flight are not of the same aircraft.");
                } else if (validation == 2) {
                    throw new SQLException("The seat entered is already booked.");
                } else {
                    updateTicket(tick);
                    return;
                }
            }

            // IF THE TABLE IS NOT THE TICKET TABLE, then continue
            else {
                String updateStatement = "UPDATE " + tableName + " SET " + attributeToUpdate + " = ? WHERE "
                        + primaryKeyName + " = ?;";
                flightQuery = flightConnect.prepareStatement(updateStatement);

                // Set the parameter value based on the type
                if (valueToUpdate instanceof String) {
                    flightQuery.setString(1, (String) valueToUpdate);
                } else if (valueToUpdate instanceof Integer) {
                    flightQuery.setInt(1, (Integer) valueToUpdate);
                } else if (valueToUpdate instanceof Boolean) {
                    flightQuery.setBoolean(1, (Boolean) valueToUpdate);
                } else if (valueToUpdate instanceof Float) {
                    flightQuery.setFloat(1, (Float) valueToUpdate);
                } else if (valueToUpdate instanceof Double) {
                    flightQuery.setDouble(1, (Double) valueToUpdate);
                } else if (valueToUpdate instanceof LocalDate) {
                    flightQuery.setDate(1, java.sql.Date.valueOf((LocalDate) valueToUpdate));
                } else if (valueToUpdate instanceof LocalTime) {
                    flightQuery.setTime(1, java.sql.Time.valueOf((LocalTime) valueToUpdate));
                } else {
                    throw new SQLException("Unsupported data type");
                }
                flightQuery.setInt(2, id);
                flightQuery.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        validateAll();
    }

    public void updateGuestUser(GuestUser user) {
        removeUser(user.getUserID());
        insertGuestUser(user);
        validateAll();
    }

    public void updateAircraft(AirPlane plane) {
        removeAircraft(plane.getAircraftID());
        insertAircraft(plane);
        validateAll();
    }

    public void updateRegisteredUser(RegisteredUser user) {
        removeUser(user.getUserID());
        insertRegisteredUser(user);
        validateAll();
    }

    public void updateCrewUser(Crew user){
        if (!user.getName().getFirstName().equals("")) { onlyInstance.updateRow("ALLUSERS", "firstName", user.getName().getFirstName(), user.getUserID()); }
        if (!user.getName().getLastName().equals("")) { onlyInstance.updateRow("ALLUSERS", "lastName", user.getName().getLastName(), user.getUserID()); }
        if (!user.getAddress().getString().equals("")) { onlyInstance.updateRow("ALLUSERS", "address", user.getAddress().getString(), user.getUserID()); }
        if (!user.getEmail().equals("")) { onlyInstance.updateRow("ALLUSERS", "email", user.getEmail(), user.getUserID()); }
        if (!user.getPassword().equals("")) { onlyInstance.updateRow("ALLUSERS", "password", user.getPassword(), user.getUserID()); }
        if (user.getBirthDate()!=null) { onlyInstance.updateRow("ALLUSERS", "birthDate", user.getBirthDate(), user.getUserID()); }
        if (!user.getPhoneNumber().equals("")) { onlyInstance.updateRow("ALLUSERS", "phoneNumber", user.getPhoneNumber(), user.getUserID()); }
        validateAll();
    }

    public void updateAdminUser(Admin user) {
        removeUser(user.getUserID());
        insertAdminUser(user);
        validateAll();
    }

    public void updateFlight(Flight flight) {
        removeFlight(flight.getFlightID());
        insertFlight(flight);
        validateAll();
    }

    public void updatePromotion(Promotions promotion) {
        removePromotion(promotion.getPromotionID());
        insertPromotion(promotion);
        validateAll();
    }

    public void updateSeat(Seat seat) {
        removeSeat(seat.getSeatID());
        insertSeat(seat);
        validateAll();
    }

    public void updateTicket(Ticket ticket) {
        removeTicket(ticket.getTicketNumber());
        insertTicket(ticket);
        validateAll();
    }

    /* VALIDATE FUNCTIONS */
    private int validateTicket(Ticket ticket) {
        try {
            ResultSet seatResult = onlyInstance.selectTableFromAttribute("SEAT", "seatID", ticket.getSeatID());
            ResultSet flightResult = onlyInstance.selectTableFromAttribute("FLIGHT", "flightID", ticket.getFlightID());
            int seatAircraftID = 0;
            boolean seatAvailability = false;
            int flightAircraftID = 0;
            while (seatResult.next()) {
                seatAircraftID = seatResult.getInt("aircraftID");
                seatAvailability = seatResult.getBoolean("available");
            }
            while (flightResult.next()) {
                flightAircraftID = flightResult.getInt("aircraftID");
            }
            if (seatAircraftID != flightAircraftID || seatAircraftID != ticket.getAircraftID()
                    || flightAircraftID != ticket.getAircraftID()) {
                return 1;
            }
            if (!seatAvailability) {
                return 2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void validateSeats() {
        try {
            String updateStatement = "UPDATE SEAT SET available = false WHERE seatID IN (SELECT seatID FROM ticket);";
            flightQuery = flightConnect.prepareStatement(updateStatement);
            flightQuery.executeUpdate();

            updateStatement = "UPDATE SEAT SET available = true WHERE seatID NOT IN (SELECT seatID FROM ticket);";
            flightQuery = flightConnect.prepareStatement(updateStatement);
            flightQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validateUsers() {
        try {
            ResultSet userResult = onlyInstance.selectTable("ALLUSERS");
            while (userResult.next()) {
                int userID = userResult.getInt("userID");
                int accessLevel = userResult.getInt("accessLevel");
                if (accessLevel == 1) {
                    if ((userResult.getObject("promotionID") != null) || (userResult.getObject("balance") != null)
                            || (userResult.getObject("password") != null)) {
                        String updateStatement = "UPDATE ALLUSERS SET promotionID = null, balance = null, password = null WHERE userID = ?";
                        flightQuery = flightConnect.prepareStatement(updateStatement);
                        flightQuery.setInt(1, userResult.getInt("userID"));
                        flightQuery.executeUpdate();

                        throw new SQLException("guest user: " + userID
                                + " has incorrect data (promotion, password, and balance must be null).");
                    }
                } else if (accessLevel == 3 || accessLevel == 4) {
                    if ((userResult.getObject("promotionID") != null) || (userResult.getObject("balance") != null)) {
                        String updateStatement = "UPDATE ALLUSERS SET promotionID = null, balance = null WHERE userID = ?";
                        flightQuery = flightConnect.prepareStatement(updateStatement);
                        flightQuery.setInt(1, userResult.getInt("userID"));
                        flightQuery.executeUpdate();

                        throw new SQLException(
                                "admin or crew user: " + userID + " cannot have a promotion or an account balance.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validatePromotions() { // ensure promotions startdate is not after enddate
        try {
            ResultSet promoResult = onlyInstance.selectTable("PROMOTIONS");
            while (promoResult.next()) {
                int promoID = promoResult.getInt("promotionID");
                Date startDate = promoResult.getDate("startDate");
                Date endDate = promoResult.getDate("endDate");
                if (startDate.after(endDate)) {
                    // switch the two dates
                    String updateStatement = "UPDATE PROMOTIONS SET startDate = ? WHERE promotionID = ?";
                    flightQuery = flightConnect.prepareStatement(updateStatement);
                    flightQuery.setDate(1, endDate);
                    flightQuery.setInt(2, promoID);
                    flightQuery.executeUpdate();

                    updateStatement = "UPDATE PROMOTIONS SET endDate = ? WHERE promotionID = ?";
                    flightQuery = flightConnect.prepareStatement(updateStatement);
                    flightQuery.setDate(1, startDate);
                    flightQuery.setInt(2, promoID);
                    flightQuery.executeUpdate();

                    throw new SQLException(
                            "Error: the promotion: " + promoID + " has a start date that is after the end date.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validateFlights() { // ensure flight has only crew members assigned
        try {
            ResultSet allCrewMembers = onlyInstance.selectTableFromAttribute("ALLUSERS", "accessLevel", 3);
            List<Integer> allCrewIDs = new ArrayList<>();
            while (allCrewMembers.next()) {
                allCrewIDs.add(allCrewMembers.getInt("userID"));
            }

            ResultSet flighResult = onlyInstance.selectTable("FLIGHT");
            List<Integer> flightCrewIDs = new ArrayList<>();
            while (flighResult.next()) {
                int flightID = flighResult.getInt("flightID");
                flightCrewIDs.add(flighResult.getInt("crewMember1"));
                flightCrewIDs.add(flighResult.getInt("crewMember2"));
                flightCrewIDs.add(flighResult.getInt("crewMember3"));

                Date departDate = flighResult.getDate("departDate");
                Date arriveDate = flighResult.getDate("arriveDate");
                if (departDate.after(arriveDate)) {
                    // switch the two dates
                    String updateStatement = "UPDATE FLIGHT SET departDate = ? WHERE flightID = ?";
                    flightQuery = flightConnect.prepareStatement(updateStatement);
                    flightQuery.setDate(1, arriveDate);
                    flightQuery.setInt(2, flightID);
                    flightQuery.executeUpdate();

                    updateStatement = "UPDATE FLIGHT SET arriveDate = ? WHERE flightID = ?";
                    flightQuery = flightConnect.prepareStatement(updateStatement);
                    flightQuery.setDate(1, departDate);
                    flightQuery.setInt(2, flightID);
                    flightQuery.executeUpdate();

                    throw new SQLException(
                            "Error: the flight: " + flightID + " has a departure date that is after the arrival date.");
                }
                // Check if all IDs in flightCrew are present in crewIDs
                List<Integer> remainingCrewIDs = new ArrayList<>(flightCrewIDs);
                remainingCrewIDs.removeAll(allCrewIDs);

                if (!remainingCrewIDs.isEmpty()) { // remove all crew member id's that are not actually crew members
                    for (int id : remainingCrewIDs) {
                        String statement = "UPDATE FLIGHT SET crewMember1 = null WHERE crewMember1 = ?";
                        flightQuery = flightConnect.prepareStatement(statement);
                        flightQuery.setInt(1, id);
                        flightQuery.executeUpdate();

                        statement = "UPDATE FLIGHT SET crewMember2 = null WHERE crewMember2 = ?";
                        flightQuery = flightConnect.prepareStatement(statement);
                        flightQuery.setInt(1, id);
                        flightQuery.executeUpdate();

                        statement = "UPDATE FLIGHT SET crewMember2 = null WHERE crewMember2 = ?";
                        flightQuery = flightConnect.prepareStatement(statement);
                        flightQuery.setInt(1, id);
                        flightQuery.executeUpdate();
                    }
                    throw new SQLException("The following users assigned to flight: " + flightID
                            + " are not crew members: " + remainingCrewIDs);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validateAll() {
        validateSeats();
        validateUsers();
        validateFlights();
        validatePromotions();
        notifyObservers();
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
        } else {
            System.out.println("Connection unsuccessful");
        }

        // // ------------ TESTING DBController REMOVE FUNCTIONS ------------
        // temp.removeAircraft(1);
        // temp.removeFlight(4);
        // temp.removePromotion(2);
        // temp.removeSeat(50);
        // temp.removeTicket(1);
        // temp.removeUser(41);

        // // ------------ TESTING DBController SELECT FUNCTIONS ------------
        // temp.selectTableFromAttribute("FLIGHT", "departLocation", "New York");
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("AIRCRAFT", "aircraftID", 1);
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("SEAT", "available", false);
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromAttribute("ALLUSERS", "balance", 75.5);
        // temp.printResultSet(temp.flightResult);

        // LocalDate newDate = LocalDate.of(2023, 06, 01);
        // temp.selectTableFromAttribute("PROMOTIONS", "startDate", newDate);
        // temp.printResultSet(temp.flightResult);

        // LocalTime newTime = LocalTime.of(10, 0, 0);
        // temp.selectTableFromAttribute("FLIGHT", "arriveTime", newTime);
        // temp.printResultSet(temp.flightResult);

        // temp.selectTableFromTwoAttributes("ALLUSERS", "firstName",
        // "John","lastName","Doe");
        // temp.printResultSet(temp.flightResult);

        // // ------------ TESTING DBController INSERT FUNCTIONS ------------
        // AirPlane newPlane = new AirPlane("test");
        // temp.insertAircraft(newPlane);
        // ResultSet check = temp.selectTableFromAttribute("AIRCRAFT", "name", "test");
        // temp.printResultSet(check);

        // Ticket newTicket = new Ticket(1, 1, 5, 26);
        // temp.insertTicket(newTicket);

        // Name guestUserName = new Name("Isha", "Haider");
        // Address guestUserAddress = new Address("213", "Sherwood Gate");
        // LocalDate guestUserDate = LocalDate.of(2002, 06, 01);
        // System.out.println(guestUserDate);
        // GuestUser newGuestUser = new GuestUser(guestUserName, guestUserAddress,
        // "isha.haider@ucalgary.ca", guestUserDate , "587-890-8532");
        // temp.insertGuestUser(newGuestUser);

        // Name regUserName = new Name("Abdel", "Rahman");
        // Address regUserAddress = new Address("527", "Springbank Circle");
        // LocalDate regUserDate = LocalDate.of(2004, 04, 29);
        // RegisteredUser newRegUser = new RegisteredUser(2, regUserName,
        // regUserAddress, "abdel.rahman@ucalgary.ca", "abdel1256", regUserDate ,
        // "403-532-2309", 73.5f);
        // temp.insertRegisteredUser(newRegUser);

        // Name crewUserName = new Name("Eric", "Mei");
        // Address crewUserAddress = new Address("402", "Everhollow Crest");
        // LocalDate crewUserDate = LocalDate.of(2001, 03, 27);
        // Crew newCrewUser = new Crew(crewUserName, crewUserAddress,
        // "eric.mei@ucalgary.ca", "eric1234", crewUserDate , "587-098-1234");
        // temp.insertCrewUser(newCrewUser);

        // Name adminUserName = new Name("Aksh", "Singh");
        // Address adminUserAddress = new Address("12", "Somerset Bridlewood");
        // LocalDate adminUserDate = LocalDate.of(2001, 07, 13);
        // Admin newAdminUser = new Admin(adminUserName, adminUserAddress,
        // "aksh.singh@gmail.com", "akshSingh34", adminUserDate , "403-678-0345");
        // temp.insertAdminUser(newAdminUser);

        // Flight newFlight = new Flight(1, adminUserDate, LocalTime.of(3, 23, 00),
        // "Calgary", adminUserDate, LocalTime.of(7, 40, 00), "Vancouver",
        // Status.OnTime, 250.0f, true, 41, 42, 43);
        // temp.insertFlight(newFlight);

        // Promotions newPromotion = new Promotions("Valentine's Sale", "13%",
        // crewUserDate, regUserDate);
        // temp.insertPromotion(newPromotion);

        // Seat newSeat = new Seat(4, "17A", AirplaneClass.Economy, 200.0f, true, true);
        // temp.insertSeat(newSeat);

        // // ------------ TESTING FlightController FUNCTIONS ------------
        // FlightController flight = new FlightController();

        // ArrayList<Flight> currentFlights = new ArrayList<Flight>();
        // ArrayList<Crew> currentCrew = new ArrayList<Crew>();
        // ArrayList<User> currentPassengers = new ArrayList<User>();
        // ArrayList<AirPlane> airplanes = new ArrayList<AirPlane>();

        // currentFlights = flight.browseFlights();
        // currentCrew = flight.browseCrew(newFlight);
        // airplanes = flight.browseAircrafts();
        // currentPassengers = flight.retrievePassengerList(2);

        // // ------------ TESTING LoginController FUNCTIONS ------------
        // LoginController login = new LoginController();
        // login.createLogin("04 03 2002", "pass", "testemail@gmail.com", "hello",
        // "bye", "214 Southcrest Dr", "587-890-8432");

        // // ------------ TESTING PromotionController FUNCTIONS ------------
        // PromotionController promotions = new PromotionController();
        // Promotions prom = promotions.getPromotion(2);

        // ------------ TESTING SeatController FUNCTIONS ------------
        // SeatController seats = new SeatController();

    }

}