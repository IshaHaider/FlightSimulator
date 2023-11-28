package src.Controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.*;
import Presentation.*;
import src.Controllers.DBController;

public class SeatController {

    private ArrayList<Flight> currentFlights = new ArrayList<Flight>();
    private ArrayList<Seat> currentSeats = new ArrayList<Seat>();
    private DBController db;
    private SearchFlightPanel searchFlightPanel;
    private Gui mainFrame;

    public SeatController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
        loadFlights();
    } 

    public void setSearchFlightPanel(SearchFlightPanel panel) {
        this.searchFlightPanel = panel;
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

                Flight flight = new Flight(flightID, aircraftID, departDate, departTime /* other parameters idk yet */);
                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSeats(int givenAircraftID) {
        try {
            ResultSet givenFlight = db.selectSeatsFromAircraftID(givenAircraftID);
            while (givenFlight.next()) {
                int seatID = listedFlights.getInt("seatID");
                int aircraftID = listedFlights.getInt("aircraftID");
                String seatName = listedFlights.getString("seatName");
                String typeOfSeat = listedFlights.getString("class");
                float cost = listedFlights.getStrign("cost");
                boolean baggage = listedFlights.getStrign("baggage");
                boolean avaliable = listedFlights.getStrign("avaliable");

                Seat seat = new Seat(flightID, aircraftID, departDate, departTime /* other parameters idk yet */);
                currentSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Flight> getCertainFlights(String destination) {
        ArrayList<Flight> tmp = new ArrayList<Flight>();
        for (Flight flight : currentFlights) {
            if (flight.getDestination().equals(destination)) {
                tmp.add(flight);
            }
        }
        return tmp;
    }

    private ArrayList<Seat> getCertainSeats(int seatID) {
        ArrayList<Seat> tmp = new ArrayList<Seat>();
        for (Seat seat : currentSeats) {
            if (seat.getSeatID() == seatID) {
                tmp.add(seat);
            }
        }
        return tmp;
    }

    // private ArrayList<Seat> getSeatsFromFlightID(int flightID) {
    //     ArrayList<Seat> seats = new ArrayList<Seat>();
    //     for (Seat seat : currentSeats) {
    //         if (seat.getFlightID() == flightID) {
    //             seats.add(seat);
    //         }
    //     }
    //     return seats;
    // }

    private void purchaseSeat() {
        
    }

}