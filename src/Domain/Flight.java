package src.Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class Flight{
    private int flightID;
    private int aircraftID;
    private Date departDate;
    private Time departTime;
    private String departLocation;
    private Date arriveDate;
    private Time arriveTime;
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
        this.departDate = new Date();
        this.departTime = new Time(0);
        this.departLocation = "";
        this.arriveDate = new Date();
        this.arriveTime = new Time(0);
        this.arriveLocation = "";
        this.flightStatus = Status.OnTime;
        this.cost = 0.0f;
        this.meal = false;
        this.crewMember1 = 0;
        this.crewMember2 = 0;
        this.crewMember3 = 0;
    }

    public Flight(int aircraftID, Date departDate, Time departTime, String departLocation, Date arriveDate, Time arriveTime,
    String arriveLocation, Status flightStatus, float cost, boolean meal, int crewMember1, int crewMember2, int crewMember3){
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
    public final Date getDepartDate() {return departDate;}
    public final Date getArriveDate() {return arriveDate;}
    public final Time getDepartTime() {return departTime;}
    public final Time getArriveTime() {return arriveTime;}
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
    public void setDepartDate(final Date departDate) {this.departDate = departDate;}
    public void setArriveDate(final Date arriveDate) {this.arriveDate = arriveDate;}
    public void setDepartTime(final Time departTime) {this.departTime = departTime;}
    public void setArriveTime(final Time arriveTime) {this.arriveTime = arriveTime;}
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