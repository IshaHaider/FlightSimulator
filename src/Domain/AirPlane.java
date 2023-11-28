package src.Domain;

import java.util.ArrayList;
import java.util.List;

public class AirPlane {
    private int flightID;
    private String aircraftName;
    private boolean meal;
    private List<Seat> seats;

    public AirPlane() {
        this.flightID = 0;
        this.aircraftName = "";
        this.meal = false;
        this.seats = new ArrayList<>();
        createSeats(16, 34);
    }

    public AirPlane(int flightID, String aircraftName, boolean meal, List<Seat> seats) {
        this.flightID = flightID;
        this.aircraftName = aircraftName;
        this.meal = meal;
        this.seats = seats;
    }

    public final int getFlightID() {return flightID;}
    public final String getAircraftName() {return aircraftName;}
    public final boolean getMeal() {return meal;}
    public final List<Seat> getSeats() {return seats;}

    public void setFlightID(final int flightID) {this.flightID = flightID;}
    public void setAircraftName(final String aircraftName) {this.aircraftName = aircraftName;}
    public void setMeal(final boolean meal) {this.meal = meal;}
    public void setSeats(final List<Seat> seats) {this.seats = seats;}

    private void createSeats(int economy, int business) {
        for (int i = 1; i <= economy; i++) {
            Seat seat = new Seat();
            seat.setSeatNum(Integer.toString(i));
            seat.setIsAvailable(true);
            seat.setBaggage(true);
            seat.setSeatclass(2);
            seats.add(seat);
        }for (int i = 1; i <= business; i++) {
            Seat seat = new Seat();
            seat.setSeatNum(Integer.toString(i));
            seat.setIsAvailable(true);
            seat.setBaggage(true);
            seat.setSeatclass(1);
            seats.add(seat);
        }
    }
}