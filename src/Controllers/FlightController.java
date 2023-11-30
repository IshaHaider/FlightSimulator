package src.Controllers;

import src.Domain.*;
import src.Presentation.*;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory.Result;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class FlightController implements Observer {
    private Gui mainFrame; 
    
    private UserSession userInstance;
    private AirlineAgentPanel airlineAgentPanel;
    private AdminPanel adminPanel;
    // private CrewPanel crewPanel;
    private DBController db = DBController.getOnlyInstance();
    
    private ArrayList<AirPlane> airplanes = new ArrayList<AirPlane>();
    private ArrayList<Flight> currentFlights = new ArrayList<Flight>();
    private ArrayList<Crew> currentCrew = new ArrayList<Crew>();
    private ArrayList<User> currentPassengers = new ArrayList<User>();
    private ArrayList<RegisteredUser> currentRegisteredUsers = new ArrayList<RegisteredUser>();

    private Subject subject;

    public FlightController (Subject s) { 
        this.userInstance = UserSession.getInstance(); 
        subject = s;
        subject.register(this);
    }

    public FlightController (Gui mainFrame, Subject s) {
        this.mainFrame = mainFrame;
        this.userInstance = UserSession.getInstance();
        subject = s;
        subject.register(this);
    }

    @Override
    public void update(){
        this.airplanes = browseAircrafts();
        this.currentFlights = browseFlights();
    }

    public ArrayList<Flight> browseFlights(){
        ArrayList<Flight> currentFlights = new ArrayList<>();
        Status[] statusValues = Status.values(); // Check if flightStatus is in the Status enum
        try {
            ResultSet listedFlights = db.selectTable("FLIGHT");
            while (listedFlights.next()) {
                int flightID = listedFlights.getInt("flightID");
                int aircraftID = listedFlights.getInt("aircraftID");
                LocalDate departDate = listedFlights.getDate("departDate").toLocalDate();
                LocalTime departTime = listedFlights.getTime("departTime").toLocalTime();
                String departLocation = listedFlights.getString("departLocation");
                LocalDate arrivalDate = listedFlights.getDate("arriveDate").toLocalDate();
                LocalTime arrivalTime = listedFlights.getTime("arriveTime").toLocalTime();
                String arrivalLocation = listedFlights.getString("arriveLocation");
                String flightStatusString = listedFlights.getString("flightStatus");
                float cost = listedFlights.getFloat("cost");
                boolean meal = listedFlights.getBoolean("meal");
                int CM1 = listedFlights.getInt("crewMember1");
                int CM2 = listedFlights.getInt("crewMember2");
                int CM3 = listedFlights.getInt("crewMember3");

                Status flightStatus = Status.OnTime;
                for (Status status : statusValues) {
                    if (flightStatusString.equals(status.toString())) {
                        flightStatus = status;
                        break;
                    }
                }
        
                Flight flight = new Flight(flightID, aircraftID, departDate, departTime, departLocation, arrivalDate, arrivalTime, arrivalLocation, flightStatus, cost, meal, CM1, CM2, CM3);
                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentFlights;
    }

    public List<Integer> getFlightCrew(final int flightID) {
        List<Integer> flightCrew = new ArrayList<>();
        
        try {
            ResultSet requiredFlight = db.selectTableFromAttribute("FLIGHT","flightID", flightID);

            while (requiredFlight.next()) {
                flightCrew.add(requiredFlight.getInt("crewMember1"));
                flightCrew.add(requiredFlight.getInt("crewMember2"));
                flightCrew.add(requiredFlight.getInt("crewMember3"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightCrew;
    }

    public List<Integer> getFlightCrew(final Flight flight) {
        List<Integer> flightCrew = new ArrayList<>();
        
        try {
            ResultSet requiredFlight = db.selectTableFromAttribute("FLIGHT","flightID", flight);

            while (requiredFlight.next()) {
                flightCrew.add(requiredFlight.getInt("crewMember1"));
                flightCrew.add(requiredFlight.getInt("crewMember2"));
                flightCrew.add(requiredFlight.getInt("crewMember3"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightCrew;
    }

    public ArrayList<Crew> browseCrew(final int flightID){
        // ArrayList<Flight> assignedFlight = new ArrayList<>();
        List<Integer> flightCrewIDs = getFlightCrew(flightID);
        try {
            ResultSet listedCrew = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 3);
            while (listedCrew.next()) {
                int crewID = listedCrew.getInt("userID");
                if(flightCrewIDs.contains(crewID)){
                    Name crewName = new Name(listedCrew.getString("firstName"), listedCrew.getString("lastName"));
                    Address crewAddress = new Address(listedCrew.getString("address"));
                    String crewEmail = listedCrew.getString("email");
                    String password = listedCrew.getString("password");
                    LocalDate birthDate = listedCrew.getDate("birthDate").toLocalDate();
                    String phoneNumber = listedCrew.getString("phoneNumber");
                    float balance = listedCrew.getFloat("balance");
                    // assignedFlight.add(flight);
                    Crew crew = new Crew(crewID, crewName, crewAddress, crewEmail, password, birthDate, phoneNumber);
                    currentCrew.add(crew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCrew;
    }
    

    public ArrayList<Crew> browseCrew(final Flight flight){
        // ArrayList<Flight> assignedFlight = new ArrayList<>();
        List<Integer> flightCrewIDs = getFlightCrew(flight);
        try {
            ResultSet listedCrew = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 3);
            while (listedCrew.next()) {
                int crewID = listedCrew.getInt("userID");
                if(flightCrewIDs.contains(crewID)){
                    Name crewName = new Name(listedCrew.getString("firstName"), listedCrew.getString("lastName"));
                    Address crewAddress = new Address(listedCrew.getString("address"));
                    String crewEmail = listedCrew.getString("email");
                    String password = listedCrew.getString("password");
                    LocalDate birthDate = listedCrew.getDate("birthDate").toLocalDate();
                    String phoneNumber = listedCrew.getString("phoneNumber");
                    float balance = listedCrew.getFloat("balance");
                    // assignedFlight.add(flight);
                    Crew crew = new Crew(crewID, crewName, crewAddress, crewEmail, password, birthDate, phoneNumber);
                    currentCrew.add(crew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCrew;
    }
    
    public ArrayList<AirPlane> browseAircrafts(){
        try {
            ResultSet listedAircrafts = db.selectTable("AIRCRAFT");
            while (listedAircrafts.next()) {
                int aircraftID = listedAircrafts.getInt("aircraftID");
                String name = listedAircrafts.getString("name");
                AirPlane airplane = new AirPlane(aircraftID, name);
                airplanes.add(airplane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airplanes;
    }

    public ArrayList<User> retrievePassengerList(final Flight flight) {
        try {
            ResultSet usersInFlight = db.selectTableFromAttribute("TICKET", "flightID", flight.getFlightID());
            ResultSet allGuestUsers = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 1);
            ResultSet allRegUsers = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 2);

            while (usersInFlight.next()) {
                int userID = usersInFlight.getInt("userID");

                while(allGuestUsers.next()){
                    if (allGuestUsers.getInt("userID") == userID){
                        Name guestUserName = new Name(allGuestUsers.getString("firstName"), allGuestUsers.getString("lastName"));
                        Address guestAddress = new Address(allGuestUsers.getString("address"));
                        String email = allGuestUsers.getString("email");
                        LocalDate birthDate = allGuestUsers.getDate("birthDate").toLocalDate();
                        String phoneNum = allGuestUsers.getString("phoneNumber");  
                        GuestUser guest = new GuestUser(userID, guestUserName, guestAddress, email, birthDate, phoneNum);
                        currentPassengers.add(guest);    
                    }
                }

                while(allRegUsers.next()){
                    if (allRegUsers.getInt("userID") == userID){
                        int promotionID = allRegUsers.getInt("promotionID");
                        Name regUserName = new Name(allRegUsers.getString("firstName"), allRegUsers.getString("lastName"));
                        Address userAddress = new Address(allRegUsers.getString("address"));
                        String email = allRegUsers.getString("email");
                        String pass = allRegUsers.getString("password");
                        LocalDate birthDate = allRegUsers.getDate("birthDate").toLocalDate();
                        String phoneNum = allRegUsers.getString("phoneNumber");  
                        Float balance = allRegUsers.getFloat("balance");  
                        RegisteredUser regUser = new RegisteredUser(userID, promotionID, regUserName, userAddress, email, pass, birthDate, phoneNum, balance);
                        currentPassengers.add(regUser);    
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentPassengers;
        // return userList.toArray(new String[0]);
    }

    public ArrayList<User> retrievePassengerList(final int flightID) {
        try {
            ResultSet usersInFlight = db.selectTableFromAttribute("TICKET", "flightID", flightID);
            ResultSet allGuestUsers = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 1);
            ResultSet allRegUsers = db.selectTableFromAttribute("ALLUSERS", "accessLevel", 2);

            while (usersInFlight.next()) {
                int userID = usersInFlight.getInt("userID");

                while(allGuestUsers.next()){
                    if (allGuestUsers.getInt("userID") == userID){
                        Name guestUserName = new Name(allGuestUsers.getString("firstName"), allGuestUsers.getString("lastName"));
                        Address guestAddress = new Address(allGuestUsers.getString("address"));
                        String email = allGuestUsers.getString("email");
                        LocalDate birthDate = allGuestUsers.getDate("birthDate").toLocalDate();
                        String phoneNum = allGuestUsers.getString("phoneNumber");  
                        GuestUser guest = new GuestUser(userID, guestUserName, guestAddress, email, birthDate, phoneNum);
                        currentPassengers.add(guest);    
                    }
                }

                while(allRegUsers.next()){
                    if (allRegUsers.getInt("userID") == userID){
                        int promotionID = allRegUsers.getInt("promotionID");
                        Name regUserName = new Name(allRegUsers.getString("firstName"), allRegUsers.getString("lastName"));
                        Address userAddress = new Address(allRegUsers.getString("address"));
                        String email = allRegUsers.getString("email");
                        String pass = allRegUsers.getString("password");
                        LocalDate birthDate = allRegUsers.getDate("birthDate").toLocalDate();
                        String phoneNum = allRegUsers.getString("phoneNumber");  
                        Float balance = allRegUsers.getFloat("balance");  
                        RegisteredUser regUser = new RegisteredUser(userID, promotionID, regUserName, userAddress, email, pass, birthDate, phoneNum, balance);
                        currentPassengers.add(regUser);    
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentPassengers;
        // return userList.toArray(new String[0]);
    }

    public ArrayList<RegisteredUser> browseRegisteredUsers(){
        try {
            ResultSet listedRegUser = db.selectTable("ALLUSERS");
            while (listedRegUser.next()) {
                int accessLevel = listedRegUser.getInt("accessLevel");

                if(accessLevel ==2){
                    int usrID = listedRegUser.getInt("userID");
                    int promotionID = listedRegUser.getInt("promotionID");
                    Name regUserName = new Name(listedRegUser.getString("firstName"), listedRegUser.getString("lastName"));
                    Address userAddress = new Address(listedRegUser.getString("address"));
                    String email = listedRegUser.getString("email");
                    String password = listedRegUser.getString("password");
                    LocalDate birthDate = listedRegUser.getDate("birthDate").toLocalDate();
                    String phoneNumber = listedRegUser.getString("phoneNumber");
                    float balance = listedRegUser.getFloat("balance");
                    RegisteredUser regUser = new RegisteredUser(usrID, promotionID, regUserName, userAddress, email, password, birthDate, phoneNumber, balance);
                    currentRegisteredUsers.add(regUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentRegisteredUsers;
    }

    public void addCrew(final Crew crew){ db.insertCrewUser(crew);}
    public void removeCrew(final Crew crew){ db.removeUser(crew.getUserID());}
    public void addAirCraft(final AirPlane airplane){ db.insertAircraft(airplane);}
    public void removeAirCraft(final AirPlane airplane){ db.removeAircraft(airplane.getAircraftID()); }
    public void addFlight(final Flight flight){ db.insertFlight(flight); }
    public void removeFlight(final Flight flight){ db.removeFlight(flight.getFlightID()); }
    public void removeFlight(final int flightID){ db.removeFlight(flightID); }
    
    /* SETTERS AND GETTERS */
    public void setAirlineAgentPanel(AirlineAgentPanel panel) { this.airlineAgentPanel = panel;}
    public void setAdminPanel(AdminPanel panel) { this.adminPanel = panel; }
    // public void setCrewPanel(CrewPanel panel) { this.crewPanel = panel; }
    public void setAirplanes(ArrayList<AirPlane> ap) { this.airplanes = ap; }
    public void setCurrentFlights(ArrayList<Flight> cf) { this.currentFlights = cf; }
    public void setCurrentCrew(ArrayList<Crew> cc) { this.currentCrew = cc; }
    public void setCurrentPassengers(ArrayList<User> cp) { this.currentPassengers = cp; }

    public AirlineAgentPanel getAirlineAgentPanel() { return this.airlineAgentPanel;}
    public AdminPanel getAdminPanel() { return this.adminPanel; }
    public ArrayList<AirPlane>  getAirplanes() { return this.airplanes; }
    public ArrayList<Flight> getCurrentFlights() { return this.currentFlights; }
    public ArrayList<Crew>  getCurrentCrew() { return this.currentCrew; }
    public ArrayList<User> getCurrentPassengers() { return this.currentPassengers; }

}