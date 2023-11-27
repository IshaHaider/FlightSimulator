package Domain;

import java.sql.Time;
import java.util.ArrayList;

public class Flight implements Subject {
    String flightNumber;
    Date departureDate;
    Date arrivalDate;
    Time departureTime;
    Time arrivalTime;
    String departureLocation;
    String arrivalLocation;
    Status flightStatus;
    float cost;
    int flightID;
    ArrayList<User> getPassengerList() { return new ArrayList<User>(); }
    public void addObserver(Observer o) {}
    public void removeObserver(Observer o) {}
    public void notifyAllObservers() {}
}