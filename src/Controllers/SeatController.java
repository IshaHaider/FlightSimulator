package src.Controllers;
import Domain.*;
import Presentation.*;

public class SeatController {

    private ArrayList<Flight> currentFlights = new ArrayList<Flight>();
    private ArrayList<Seat> currentSeats = new ArrayList<Seat>();
    private DBController db;
    private SearchFlightPanel searchFlightPanel;

    public SeatController (DBController db) {
        this.db = db;
        loadFlights();
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

    private ArrayList<Flight> getAllFlights() {
        return currentFlights;
    }

    public void setSearchFlightPanel(SearchFlightPanel panel) {
        this.loginPanel = panel;
    }

    private void loadSeats(int givenAircraftID) {
        try {
            ResultSet givenFlight = db.selectSeatsFromAircraftID(givenAircraftID);
            while (givenFlight.next()) {
                int seatID = listedFlights.getInt("flightID");
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

    private ArrayList<Seat> getAllSeat() {
        return currentSeats;
    }
}