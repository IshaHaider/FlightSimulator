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
    }
    
    public ArrayList<Flight> browseAircrafts(){

    }

    public void addCrew(final Flight flight, final Crew crew){

    }

    public String[] retrivePassangerList() {
        List<String> userList = new ArrayList<>();
        try {
            ResultSet listUsers = db.selectTable("FLIGHT");
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