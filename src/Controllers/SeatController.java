package src.Controllers;
  
import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;

public class SeatController implements Observer {
    private Gui mainFrame;
    private ArrayList<Seat> currentSeats = new ArrayList<Seat>();
    private DBController db = DBController.getOnlyInstance();
    private SearchFlightPanel searchFlightPanel;
    private CreditCardPanel creditCardPanel;
    private CancelFlightPanel cancelFlightPanel;

    private Subject subject;

    public SeatController (Subject s) {
        subject = s;
        subject.register(this);
    }

    public SeatController (Gui mainFrame, Subject s) {  
        this.mainFrame = mainFrame; 
        subject = s;
        subject.register(this);
    } 

    @Override
    public void update(){ db = DBController.getOnlyInstance(); }

    private void loadSeats(int givenAircraftID) {
        AirplaneClass[] classValues = AirplaneClass.values(); // Check if class is in the AirplaneClass enum
        try {
            currentSeats.clear();
            ResultSet givenFlight = db.selectTableFromAttribute("SEAT","aircraftID", givenAircraftID);
            while (givenFlight.next()) {
                int seatID = givenFlight.getInt("seatID");
                int aircraftID = givenFlight.getInt("aircraftID");
                String seatName = givenFlight.getString("seatName");
                String seatClassString = givenFlight.getString("class");
                float cost = givenFlight.getFloat("cost");
                boolean baggage = givenFlight.getBoolean("baggage");
                boolean available = givenFlight.getBoolean("available");
                
                AirplaneClass seatClass = AirplaneClass.Economy; //default
                for (AirplaneClass airClass : classValues) {
                    if (seatClassString.equals(airClass.toString())) {
                        seatClass = airClass;
                        break;
                    }
                }

                Seat seat = new Seat( seatID, aircraftID,  seatName,  seatClass,  cost,  baggage,  available);
                currentSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getCertainFlights(String arrivalLoc) {
        ArrayList<Flight> currentFlights = new ArrayList<>();
        Status[] statusValues = Status.values(); // Check if flightStatus is in the Status enum
        try {
            ResultSet listedFlights = db.selectTableFromAttribute("FLIGHT", "arriveLocation", arrivalLoc);
            while (listedFlights.next()) {
                int flightID = listedFlights.getInt("flightID");
                int aircraftID = listedFlights.getInt("aircraftID");
                LocalDate departDate = listedFlights.getDate("departDate").toLocalDate();
                LocalTime departTime = listedFlights.getTime("departTime").toLocalTime();
                LocalDate arrivalDate = listedFlights.getDate("arriveDate").toLocalDate();
                LocalTime arrivalTime = listedFlights.getTime("arriveTime").toLocalTime();
                String departLocation = listedFlights.getString("departLocation");
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
        
                Flight flight = new Flight(flightID, aircraftID, departDate, departTime, departLocation, arrivalDate, arrivalTime, arrivalLoc, flightStatus, cost, meal, CM1, CM2, CM3);
                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentFlights;
    }

    public ArrayList<Seat> getCertainSeats(int aircraftID) {
        ArrayList<Seat> tempSeats = new ArrayList<Seat>();
        AirplaneClass[] classValues = AirplaneClass.values(); // Check if class is in the AirplaneClass enum
        try {
            currentSeats.clear();
            ResultSet givenFlight = db.selectTableFromAttribute("SEAT","aircraftID", aircraftID);
            while (givenFlight.next()) {
                int seatID = givenFlight.getInt("seatID");
                String seatName = givenFlight.getString("seatName");
                String seatClassString = givenFlight.getString("class");
                float cost = givenFlight.getFloat("cost");
                boolean baggage = givenFlight.getBoolean("baggage");
                boolean available = givenFlight.getBoolean("available");
                
                AirplaneClass seatClass = AirplaneClass.Economy; //default
                for (AirplaneClass airClass : classValues) {
                    if (seatClassString.equals(airClass.toString())) {
                        seatClass = airClass;
                        break;
                    }
                }

                Seat seat = new Seat( seatID, aircraftID,  seatName,  seatClass,  cost,  baggage,  available);
                tempSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tempSeats;
    }
    
    // This function is for purchasing a seat without having to be loggedIn
    public void purchaseSeat( int flightID, int aircraftID, GuestUser tmpGuestUser, int seatID ) {
        db.insertGuestUser(tmpGuestUser);
        String email = tmpGuestUser.getEmail();
        try {
            ResultSet retrieveUserID = db.selectTableFromAttribute("ALLUSERS", "email", email);
            while (retrieveUserID.next()) {
                int userID = retrieveUserID.getInt("userID");
                Ticket tmp = new Ticket(aircraftID, flightID, userID, seatID);
                db.insertTicket(tmp); 
                loadSeats(aircraftID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void purchaseSeat( int flightID, int aircraftID, int userID, int seatID ) {
        Ticket tmp = new Ticket( aircraftID, flightID, userID, seatID);
        db.insertTicket(tmp);
        loadSeats(aircraftID);
    }
 
    public String cancelFlight(int ticketNum, int flightID, int seatID) { return db.removeTicket(ticketNum); }
    
    
    public String cancelFlight(int ticketNum) { 
        try {
            String returnStatement = db.removeTicket(ticketNum); 
            ResultSet ticketSet = db.selectTableFromAttribute("TICKET", "ticketNumber", ticketNum);
            while (ticketSet.next()) {
                int seatID = ticketSet.getInt("seatID");
                System.out.println("Seat ID: " + seatID);
                db.updateRow("SEAT", "available", true, seatID);
                System.out.println("Ticket found, Seat Avaliable Changed");
                return returnStatement + " Seat " + seatID + " is now available";
            }
            System.out.println("Seat for ticket not found");
            return returnStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ticket not found";
        }
    }


    public void cancelFlight(int flightID, int seatID) { 
        try{
            ResultSet cancelTicket  = db.selectTableFromTwoAttributes("TICKET", "flightID", flightID, "seatID", seatID);
            while (cancelTicket.next()){
                db.removeTicket(cancelTicket.getInt("ticketNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    /* SETTERS AND GETTERS */
    public void setSearchFlightPanel(SearchFlightPanel panel) { this.searchFlightPanel = panel; }
    public void setCreditCardPanel(CreditCardPanel panel) { this.creditCardPanel = panel; }
    public void setCancelFlightPanel(CancelFlightPanel panel) { this.cancelFlightPanel = panel; }
    public void setCurrentSeats(ArrayList<Seat> cs) { this.currentSeats = cs; }
    
    public SearchFlightPanel getSearchFlightPanel() { return this.searchFlightPanel; }
    public CreditCardPanel getCreditCardPanel() { return this.creditCardPanel; }
    public CancelFlightPanel getCancelFlightPanel() { return this.cancelFlightPanel; }
    public ArrayList<Seat> getCurrentSeats() { return this.currentSeats; }
    public DBController getDBController() { return this.db; }
    
}