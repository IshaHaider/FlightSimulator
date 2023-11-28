package src.Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class Flight implements Subject {
    private String flightNumber;
    private Airplane assignedPlane;
    private Map<Seat, User> assignedSeats;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private Status flightStatus;
    private float cost;
    private int flightID;
    private List<Observer> observers = new ArrayList<>();

    public Flight() {
        this.flightNumber = "";
        this.assignedPlane = new Airplane();
        this.assignedSeats = new Map<Seat, User>();
        this.departureDate = new Date();
        this.arrivalDate = new Date();
        this.departureTime = new Time(0);
        this.arrivalTime = new Time(0);
        this.departureLocation = "";
        this.arrivalLocation = "";
        this.flightStatus = Status.SCHEDULED;
        this.cost = 0.0f;
        this.flightID = 0;
    }

    public Flight(final String flightNumber, final Airplane assignedPlane, final Map<Seat, User> assignedSeats, final Date departureDate, 
    final Date arrivalDate, final Time departureTime, final Time arrivalTime, final String departureLocation, final String arrivalLocation, 
    final Status flightStatus, final float cost, final int flightID) {
        this.flightNumber = flightNumber;
        this.assignedPlane = assignedPlane;
        this.assignedSeats = assignedSeats;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.flightStatus = flightStatus;
        this.cost = cost;
        this.flightID = flightID;
    }

    public final String getFlightNumber() {return flightNumber;}
    public final Date getDepartureDate() {return departureDate;}
    public final Date getArrivalDate() {return arrivalDate;}
    public final Time getDepartureTime() {return departureTime;}
    public final Time getArrivalTime() {return arrivalTime;}
    public final String getDepartureLocation() {return departureLocation;}
    public final String getArrivalLocation() {return arrivalLocation;}
    public final Status getFlightStatus() {return flightStatus;}
    public final float getCost() {return cost;}
    public final int getFlightID() {return flightID;}
    public final Airplane getAssignedPlane() {return assignedPlane;}
    public final Map<Seat, User> getAssignedSeats() {return assignedSeats;}
    
    public void setFlightNumber(final String flightNumber) {this.flightNumber = flightNumber;}
    public void setDepartureDate(final Date departureDate) {this.departureDate = departureDate;}
    public void setArrivalDate(final Date arrivalDate) {this.arrivalDate = arrivalDate;}
    public void setDepartureTime(final Time departureTime) {this.departureTime = departureTime;}
    public void setArrivalTime(final Time arrivalTime) {this.arrivalTime = arrivalTime;}
    public void setDepartureLocation(final String departureLocation) {this.departureLocation = departureLocation;}
    public void setArrivalLocation(final String arrivalLocation) {this.arrivalLocation = arrivalLocation;}
    public void setFlightStatus(final Status flightStatus) {this.flightStatus = flightStatus;}
    public void setCost(final float cost) {this.cost = cost;}
    public void setFlightID(final Flight flight) {this.assignedPlane = assignedPlane;}
    public void setAssignedSeats(final Map<Seat, User> assignedSeats) {this.assignedSeats = assignedSeats;}
    public void setAssignedPlane(final Airplane assignedPlane) {this.assignedPlane = assignedPlane;}

    public final ArrayList<User> getPassengerList() {
        ArrayList<User> passengerList = new ArrayList<>();
        for (Map.Entry<Seat, User> entry : assignedSeats.entrySet()) {
            passengerList.add(entry.getValue());
        }
        return passengerList;
    }

    public final Seat getSeat(final int userID) {
        for (Map.Entry<Seat, User> entry : assignedSeats.entrySet()) {
            if (entry.getValue().getUserID() == userID) {
                return entry.getKey();
            }
        }
        return null;
    }


    public void assignSeat(final int userID, final Seat seat) {
        seat.setUserID(userID);
        seat.setIsAvailable(false);
    }

    @Override
    public void addObserver(Observer o) {
        if(!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer o : observers) {
            o.update(this, null);
        }
    }
}