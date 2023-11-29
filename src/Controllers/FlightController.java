package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

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
                
                Date departDate1 = listedFlights.getString("departDate");
                DDate departDate = new DDate(departDate1);

                Time departTime1 = listedFlights.getString("departTime");
                TTime 
                Time arrivalDate = listedFlights.getString("arrivalDate");
                Time arrivalTime = listedFlights.getStrign("arrivalTime");
                String arrivalLocation = listedFlights.getStrign("arrivalLocation");
                Status flightStatus = listedFlights.getStrign("flightStatus");
                float cost = listedFlights.getStrign("cost");

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
        String [] tmp;
        try {
            ResultSet listUsers = db.selectTable("FLIGHT");
            while (listUsers.next()) {
                int userID = listUsers.getInt("userID");
                String firstName = listUsers.getString("firstName");
                String lastName = listUsers.getString("lastName");
                String address = listUsers.getString("address");
                String email = listUsers.getString("email");
                String birthDate = listUsers.getString("birthDate");
                String phoneNum = listUsers.getString("phoneNumber");
                
                currentPassangerList.add(tmpUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }
}