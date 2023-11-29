package src.Controllers;

import src.Controllers.DBController;
import src.Controllers.Gui;
import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;


public class FlightController {
    private Gui mainFrame; 
    private DBController db;
    private UserSession userInstance;
    private AirlineAgentPanel airlineAgentPanel;
    private AdminPanel adminPanel;

    private ArrayList<User> currentPassangerList = new ArrayList<User>();

    private ArrayList<Flight> currentFlights = new ArrayList<Flight>();
    private ArrayList<Flight> currentCrew = new ArrayList<Crew>();
    private ArrayList<AirPlane> currentAircrafts = new ArrayList<AirPlane>();
    private ArrayList<RegisteredUser> currentRegisteredUsers = new ArrayList<RegisteredUser>();
    
    public FlightController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
        this.userInstance = UserSession.getInstance();
    }

    public void setAirlineAgentPanel(AirlineAgentPanel panel) {
        this.airlineAgentPanel = panel;
    }
    
    public void setAdminPanel(AdminPanel panel) {
        this.adminPanel = panel;
    }

    public ArrayList<Flight> browseFlights(){
        try {
            ResultSet listedFlights = db.selectTable("FLIGHT");
            while (listedFlights.next()) {
                int flightID = listedFlights.getInt("flightID");
                int aircraftID = listedFlights.getInt("aircraftID");
                
                LocalDate departDate = listedFlights.getDate("departDate").toLocalDate();
                LocalTime departTime = listedFlights.getTime("departTime").toLocalTime();
                LocalDate arrivalDate = listedFlights.getDate("arrivalDate").toLocalDate();
                LocalTime arrivalTime = listedFlights.getTime("arrivalTime").toLocalTime();
                String arrivalLocation = listedFlights.getString("arrivalLocation");
                Status flightStatus = listedFlights.getString("flightStatus");
                float cost = listedFlights.getString("cost");

                Flight flight = new Flight(flightID, aircraftID, departDate, departTime, arrivalDate, arrivalTime, arrivalLocation, flightID, cost);
                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentFlights;
    }

    public ArrayList<Flight> browseCrew(){
        try {
            ResultSet listedCrew = db.selectTable("ALLUSERS");
            while (listedCrew.next()) {
                int accessLevel = listedCrew.getInt("accessLevel");
                if(accessLevel == 3){
                    String crewID = listedCrew.getInt("userID");
                    Name crewName = new Name(listedCrew.getString("firstName"), listedCrew.getString("lastName"));
                    Address crewAddress = new Address(listedCrew.getString("street"), listedCrew.getString("city"), 
                                                        listedCrew.getString("state"), listedCrew.getString("zip"));
                    String crewEmail = listedCrew.getString("email");
                    
                    Crew crew = new Crew(crewID, crewName, crewType, crewStatus, flightID);
                    currentCrew.add(crew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCrew;
    }
    
    public ArrayList<Flight> browseAircrafts(){
        try {
            ResultSet listedAircrafts = db.selectTable("AIRCRAFT");
            while (listedAircrafts.next()) {
                int aircraftID = listedCrew.getInt("aircraftID");
                String airplaneName = new Name(listedCrew.getString("name"), listedCrew.getString("lastName"));
                AirPlane airPlane = new AirPlane(airplaneName);                 
                currentAircrafts.add(airPlane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentAircrafts;
    }

    public void addCrew(final Flight flight, final Crew crew){
        
    }

    public void addFlight(final Flight flight){
        
    }

    public void addAircraft(final AirPlane aircraft){

    }

    public void removeCrew(final Flight flight, final Crew crew){

    }

    public void removeFlight(final Flight flight){

    }

    public void removeAircraft(final AirPlane aircraft){

    }

    public ArrayList<RegisteredUser> browseRegisteredUsers(){
        try {
            ResultSet listedRegUser = db.selectTable("ALLUSERS");
            while (listedRegUser.next()) {
                int accessLevel = allUsers.getInt("accessLevel");

                if(accessLevel ==2){
                    int userID = allUsers.getInt("userID");
                    int promotionID = allUsers.getInt("promotionID");
                    String firstName = allUsers.getString("firstName");
                    String lastName = allUsers.getString("lastName");
                    String address = allUsers.getString("address");
                    String email = allUsers.getString("email");
                    String password = allUsers.getString("password");
                    LocalDate birthDate = allUsers.getDate("birthDate").toLocalDate();
                    String phoneNumber = allUsers.getString("phoneNumber");
                    float balance = allUsers.getFloat("balance");
                    
                    RegisteredUser regUser = new RegisteredUser(userID, accessLevel, promotionID, firstName, lasttName, address, email, password, birthDate, phoneNumber, balance);
                    currentRegisteredUsers.addUser(admin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentRegisteredUsers;
    }

    public String[] retrivePassangerList() {
        List<String> userList = new ArrayList<>();
        try {
            ResultSet listUsers;

            // NOTE: This is the SQL query you need to use to get the list of users for a flight
            // SELECT ALLUSERS.*
            // FROM TICKET
            // JOIN ALLUSERS ON TICKET.userID = ALLUSERS.userID
            // WHERE TICKET.flightID = [YourFlightID];

            while (listUsers.next()) {
                int userID = listUsers.getInt("userID");
                String firstName = listUsers.getString("firstName");
                String lastName = listUsers.getString("lastName");
                String address = listUsers.getString("address");
                String email = listUsers.getString("email");
                LocalDate birthDate = listUsers.getDate("birthDate").toLocalDate();
                String phoneNum = listUsers.getString("phoneNumber");

                String userDetails = userID + ": " + firstName + " " + lastName + ", " + address + ", " + email + ", " + birthDate + ", " + phoneNum;
                userList.add(userDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.toArray(new String[0]);
    }
}