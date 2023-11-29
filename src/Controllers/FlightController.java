package src.Controllers;

import src.Controllers.DBController;
import src.Controllers.Gui;
import src.Domain.*;
import src.Presentation.*;

import java.sql.Date;
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
        ArrayList<Flight> currentFlights = new ArrayList<>();
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

    public ArrayList<Crew> browseCrew(final Flight flight){
        ArrayList<Flight> assignedFlight = new ArrayList<>();
        List<Integer> flightCrewIDs = getFlightCrew(flight);
        try {
            ResultSet listedCrew = db.selectTable("ALLUSERS");
            while (listedCrew.next()) {
                int accessLevel = listedCrew.getInt("accessLevel");
                int crewID = Integer.parseInt(listedCrew.getInt("userID"));
                if(accessLevel == 3 && flightCrewIDs.contains(crewID)){
                    Name crewName = new Name(listedCrew.getString("firstName"), listedCrew.getString("lastName"));
                    Address crewAddress = new Address(listedCrew.getString("address"));
                    String crewEmail = listedCrew.getString("email");
                    String password = listedCrew.getString("password");
                    Date birthDate = listedCrew.getDate("birthDate");
                    String phoneNumber = listedCrew.getString("phoneNumber");
                    float balance = listedCrew.getFloat("balance");
                    assignedFlight.add(flight);
                    Crew crew = new Crew(crewID, accessLevel, crewName, crewAddress, crewEmail, password, birthDate, phoneNumber, assignedFlight);
                    currentCrew.add(crew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCrew;
    }
    public List<Integer> getFlightCrew(final Flight flight) {
        List<Integer> flightCrew = new ArrayList<>();

        try {
            ResultSet listedCrew = db.selectTable("FLIGHT");

            while (listedCrew.next()) {
                int flightID = listedCrew.getInt("flightID");

                if (flightID == flight.getFlightID()) {
                    flightCrew.add(listedCrew.getInt("crewMember1"));
                    flightCrew.add(listedCrew.getInt("crewMember2"));
                    flightCrew.add(listedCrew.getInt("crewMember3"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightCrew;
    }

    public ArrayList<Airplane> browseAircrafts(){
        ArrayList<Airplane> airplanes = new ArrayList<>();

        try {
            ResultSet listedCrew = db.selectTable("AIRCRAFT");
            while (listedCrew.next()) {
                int aircraftID = listedCrew.getInt("aircraftID");
                int name = listedCrew.getString("name");
                Airplane airplane = new Airplane(aircraftID, name);
                airplanes.add(airplane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airplanes;
    }

    public void addCrew(final Crew crew){
        try {
            db.insertCrewUser(crew);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCrew(final Crew crew){
        try {
            db.removeUser(crew.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }

    public void addAirCraft(final Airplane airplane){
        try {
            db.insertAircraft(airplane);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAirCraft(final Airplane airplane){
        try {
            db.removeAircraft(airplane.getAircraftID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFlight(final Flight flight){
        try {
            db.insertFlight(flight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFlight(final Flight flight){
        try {
            db.removeFlight(flight.getFlightID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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