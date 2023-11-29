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

// public class SeatController implements Observer<T>{
public class SeatController{

    // private ArrayList<Flight> currentFlights;
    private ArrayList<Seat> currentSeats = new ArrayList<Seat>();
    private DBController db = DBController.getOnlyInstance();
    private SearchFlightPanel searchFlightPanel;
    private CreditCardPanel creditCardPanel;
    private CancelFlightPanel cancelFlightPanel;
    private Gui mainFrame;

    public SeatController () { 
        // currentFlights = new FlightController().browseFlights(); 
    } 

    public SeatController (Gui mainFrame) { 
        this.mainFrame = mainFrame;
        // currentFlights = new FlightController().browseFlights(); 
    } 

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

    public ArrayList<Flight> getCertainFlights(String departLocation) {
        // ArrayList<Flight> tmp = new ArrayList<Flight>();
        // loadFlights();
        // for (Flight flight : currentFlights) {
        //     if (flight.getDepartLocation().equals(destination)) {
        //         tmp.add(flight);
        //     }
        // }
        // return tmp;

        ArrayList<Flight> currentFlights = new ArrayList<>();
        Status[] statusValues = Status.values(); // Check if flightStatus is in the Status enum
        try {
            ResultSet listedFlights = db.selectTableFromAttribute("FLIGHT", "destination", departLocation);
            while (listedFlights.next()) {
                int flightID = listedFlights.getInt("flightID");
                int aircraftID = listedFlights.getInt("aircraftID");
                LocalDate departDate = listedFlights.getDate("departDate").toLocalDate();
                LocalTime departTime = listedFlights.getTime("departTime").toLocalTime();
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

    public ArrayList<Seat> getCertainSeats(int seatID) {
        // ArrayList<Seat> tmp = new ArrayList<Seat>();
        // loadSeats();
        // for (Seat seat : currentSeats) {
        //     if (seat.getSeatID() == seatID) {
        //         tmp.add(seat);
        //     }
        // }
        // return tmp;
            
        ArrayList<Seat> tempSeats = new ArrayList<Seat>();
        AirplaneClass[] classValues = AirplaneClass.values(); // Check if class is in the AirplaneClass enum
        try {
            currentSeats.clear();
            ResultSet givenFlight = db.selectTableFromAttribute("SEAT","seatID", seatID);
            while (givenFlight.next()) {
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
                tempSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tempSeats;
    }

    public void purchaseSeat( int flightID, int aircraftID, int userID, int seatID ) {
        Ticket tmp = new Ticket(flightID, aircraftID, userID, seatID);
        db.insertTicket(tmp);
        loadSeats(aircraftID);
    }

    public void cancelFlight(int ticketNum, int flightID, int seatID) {
        db.removeTicket(ticketNum);
        // loadFlights();
    }


    /* SETTERS AND GETTERS */
    public void setSearchFlightPanel(SearchFlightPanel panel) { this.searchFlightPanel = panel; }
    public void setCreditCardPanel(CreditCardPanel panel) { this.creditCardPanel = panel; }
    public void setCancelFlightPanel(CancelFlightPanel panel) { this.cancelFlightPanel = panel; }
    // public void setCurrentFlights(ArrayList<Flight> cf) { this.currentFlights = cf; }
    public void setCurrentSeats(ArrayList<Seat> cs) { this.currentSeats = cs; }
    
    public SearchFlightPanel getSearchFlightPanel() { return this.searchFlightPanel; }
    public CreditCardPanel getCreditCardPanel() { return this.creditCardPanel; }
    public CancelFlightPanel getCancelFlightPanel() { return this.cancelFlightPanel; }
    // public ArrayList<Flight> getCurrentFlights() { return this.currentFlights; }
    public ArrayList<Seat> getCurrentSeats() { return this.currentSeats; }
    

}