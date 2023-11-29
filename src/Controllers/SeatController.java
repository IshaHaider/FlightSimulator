package src.Controllers;

import src.Domain.*;
import src.Presentation.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class SeatController implements Observer<T>{

    private ArrayList<Flight> currentFlights = new ArrayList<Flight>();
    private ArrayList<Seat> currentSeats = new ArrayList<Seat>();
    private DBController db;
    private SearchFlightPanel searchFlightPanel;
    private CreditCardPanel creditCardPanel;
    private CancelFlightPanel cancelFlightPanel;
    private Gui mainFrame;

    public SeatController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
        loadFlights();
    } 

    public void setSearchFlightPanel(SearchFlightPanel panel) {
        this.searchFlightPanel = panel;
    }

    public void setCreditCardPanel(CreditCardPanel panel) {
        this.creditCardPanel = panel;
    }

    public void setCancelFlightPanel(CancelFlightPanel panel) {
        this.cancelFlightPanel = panel;
    }

    private void loadFlights() {
        try {
            ResultSet listedFlights = db.selectTable("FLIGHT");

            while (listedFlights.next()) {
                int flightID = listedFlights.getInt("flightID");
                int aircraftID = listedFlights.getInt("aircraftID");
                Date departDate = listedFlights.getString("departDate");
                String departTime = listedFlights.getString("departTime");
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

    private void loadSeats(int givenAircraftID) {
        try {
            ResultSet givenFlight = db.selectTableFromAttribute("SEAT","aircraftID",givenAircraftID);
            while (givenFlight.next()) {
                int seatID = listedFlights.getInt("seatID");
                int aircraftID = listedFlights.getInt("aircraftID");
                String seatName = listedFlights.getString("seatName");
                String typeOfSeat = listedFlights.getString("class");
                float cost = listedFlights.getStrign("cost");
                boolean baggage = listedFlights.getStrign("baggage");
                boolean avaliable = listedFlights.getStrign("avaliable");
                Seat seat = new Seat( seatID, aircraftID,  seatName,  typeOfSeat,  cost,  baggage,  avaliable);
                currentSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getCertainFlights(String destination) {
        ArrayList<Flight> tmp = new ArrayList<Flight>();
        for (Flight flight : currentFlights) {
            if (flight.getDestination().equals(destination)) {
                tmp.add(flight);
            }
        }
        return tmp;
    }

    public ArrayList<Seat> getCertainSeats(int seatID) {
        ArrayList<Seat> tmp = new ArrayList<Seat>();
        for (Seat seat : currentSeats) {
            if (seat.getSeatID() == seatID) {
                tmp.add(seat);
            }
        }
        return tmp;
    }

    public void purchaseSeat( int flightID, int aircraftID, int userID, int seatID ) {
        Ticket tmp = new Ticket(flightID, aircraftID, userID, seatID);
        db.insertTicket(tmp);
    }

    public void cancelFlight(int ticketNum, int flightID, int seatID) {
        db.removeTicket(ticketNum);
    }

}