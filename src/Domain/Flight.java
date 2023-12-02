package src.Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight{
    private int flightID;
    private int aircraftID;
    private LocalDate departDate;
    private LocalTime departTime;
    private String departLocation;
    private LocalDate arriveDate;
    private LocalTime arriveTime;
    private String arriveLocation;
    private Status flightStatus;
    private float cost;
    private boolean meal;
    private int crewMember1;
    private int crewMember2;
    private int crewMember3;

    public Flight(){
        this.flightID = 0;
        this.aircraftID = 0;
        this.departLocation = "";
        this.arriveLocation = "";
        this.flightStatus = Status.OnTime;
        this.cost = 0.0f;
        this.meal = false;
        this.crewMember1 = 0;
        this.crewMember2 = 0;
        this.crewMember3 = 0;
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the flightID is auto-increment)
    public Flight(int aircraftID, LocalDate departDate, LocalTime departTime, String departLocation, LocalDate arriveDate, LocalTime arriveTime,
    String arriveLocation, Status flightStatus, float cost, boolean meal, int crewMember1, int crewMember2, int crewMember3){
        this.flightID = 0;
        this.aircraftID = aircraftID;
        this.departDate = departDate;
        this.departTime = departTime;
        this.departLocation = departLocation;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.arriveLocation = arriveLocation;
        this.flightStatus = flightStatus;
        this.cost = cost;
        this.meal = meal;
        this.crewMember1 = crewMember1;
        this.crewMember2 = crewMember2;
        this.crewMember3 = crewMember3;
    }

    // used when we don't want to add crew yet but create a flight
    public Flight(int aircraftID, LocalDate departDate, LocalTime departTime, String departLocation, LocalDate arriveDate, LocalTime arriveTime,
    String arriveLocation, Status flightStatus, float cost, boolean meal){
        this.flightID = 0;
        this.aircraftID = aircraftID;
        this.departDate = departDate;
        this.departTime = departTime;
        this.departLocation = departLocation;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.arriveLocation = arriveLocation;
        this.flightStatus = flightStatus;
        this.cost = cost;
        this.meal = meal;
        this.crewMember1 = 0;
        this.crewMember2 = 0;
        this.crewMember3 = 0;
    }

    // used when we don't want to add crew yet but update a flight
    public Flight(int flightID, int aircraftID, LocalDate departDate, LocalTime departTime, String departLocation, LocalDate arriveDate, LocalTime arriveTime,
    String arriveLocation, Status flightStatus, float cost, boolean meal){
        this.flightID = flightID;
        this.aircraftID = aircraftID;
        this.departDate = departDate;
        this.departTime = departTime;
        this.departLocation = departLocation;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.arriveLocation = arriveLocation;
        this.flightStatus = flightStatus;
        this.cost = cost;
        this.meal = meal;
        this.crewMember1 = 0;
        this.crewMember2 = 0;
        this.crewMember3 = 0;
    }

    // USE THIS FOR LOCAL STORAGE (to manually add flightID)
    public Flight(int flightID, int aircraftID, LocalDate departDate, LocalTime departTime, String departLocation, LocalDate arriveDate, LocalTime arriveTime,
    String arriveLocation, Status flightStatus, float cost, boolean meal, int crewMember1, int crewMember2, int crewMember3){
        this.flightID = flightID;
        this.aircraftID = aircraftID;
        this.departDate = departDate;
        this.departTime = departTime;
        this.departLocation = departLocation;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.arriveLocation = arriveLocation;
        this.flightStatus = flightStatus;
        this.cost = cost;
        this.meal = meal;
        this.crewMember1 = crewMember1;
        this.crewMember2 = crewMember2;
        this.crewMember3 = crewMember3;
    }


    public final int getFlightID() {return flightID;}
    public final int getAircraftID() {return aircraftID;}
    public final LocalDate getDepartDate() {return departDate;}
    public final LocalDate getArriveDate() {return arriveDate;}
    public final LocalTime getDepartTime() {return departTime;}
    public final LocalTime getArriveTime() {return arriveTime;}
    public final String getDepartLocation() {return departLocation;}
    public final String getArriveLocation() {return arriveLocation;}
    public final Status getFlightStatus() {return flightStatus;}
    public final float getCost() {return cost;}
    public final boolean getMeal() {return meal;}
    public final int getCrewMember1() {return crewMember1;}
    public final int getCrewMember2() {return crewMember2;}
    public final int getCrewMember3() {return crewMember3;}

    public void setFlightID(final int flightID) {this.flightID = flightID;}
    public void setAircraftID(final int aircraftID) {this.aircraftID = aircraftID;}
    public void setDepartDate(final LocalDate departDate) {this.departDate = departDate;}
    public void setArriveDate(final LocalDate arriveDate) {this.arriveDate = arriveDate;}
    public void setDepartTime(final LocalTime departTime) {this.departTime = departTime;}
    public void setArriveTime(final LocalTime arriveTime) {this.arriveTime = arriveTime;}
    public void setDepartLocation(final String departLocation) {this.departLocation = departLocation;}
    public void setArriveLocation(final String arriveLocation) {this.arriveLocation = arriveLocation;}
    public void setFlightStatus(final Status flightStatus) {this.flightStatus = flightStatus;}
    public void setCost(final float cost) {this.cost = cost;}
    public void setMeal(final boolean meal) {this.meal = meal;}
    public void setCrewMember1(final int crewMember1) {this.crewMember1 = crewMember1;}
    public void setCrewMember2(final int crewMember2) {this.crewMember2 = crewMember2;}
    public void setCrewMember3(final int crewMember3) {this.crewMember3 = crewMember3;}

    // public final ArrayList<User> getPassengerList() {
    //     ArrayList<User> passengerList = new ArrayList<>();
    //     for (Map.Entry<Seat, User> entry : assignedSeats.entrySet()) {
    //         passengerList.add(entry.getValue());
    //     }
    //     return passengerList;
    // }

    // public final Seat getSeat(final int userID) {
    //     for (Map.Entry<Seat, User> entry : assignedSeats.entrySet()) {
    //         if (entry.getValue().getUserID() == userID) {
    //             return entry.getKey();
    //         }
    //     }
    //     return null;
    // }


    // public void assignSeat(final int userID, final Seat seat) {
    //     seat.setUserID(userID);
    //     seat.setIsAvailable(false);
    // }

    // @Override
    // public void addObserver(Observer o) {
    //     if(!observers.contains(o)) {
    //         observers.add(o);
    //     }
    // }

    // @Override
    // public void removeObserver(Observer o) {
    //     observers.remove(o);
    // }

    // @Override
    // public void notifyAllObservers() {
    //     for(Observer o : observers) {
    //         o.update(this, null);
    //     }
    // }
}