package src.Domain;

import java.util.ArrayList;
import java.util.List;

public class AirPlane {
    private int aircraftID;
    private String aircraftName;

    public AirPlane() {
        this.aircraftID = 0;
        this.aircraftName = "";
        // this.seats = new ArrayList<>();
        // createSeats(16, 34);
    }

    public AirPlane(String name) {
        this.aircraftName = name;
        // this.seats = seats;
    }

    public final int getAircraftID() {return aircraftID;}
    public final String getAircraftName() {return aircraftName;}
    // public final List<Seat> getSeats() {return seats;}

    public void setAircraftID(final int aircraftID) {this.aircraftID = aircraftID;}
    public void setAircraftName(final String aircraftName) {this.aircraftName = aircraftName;}
    // public void setSeats(final List<Seat> seats) {this.seats = seats;}

    // private void createSeats(int economy, int business) {
    //     for (int i = 1; i <= economy; i++) {
    //         Seat seat = new Seat();
    //         seat.setSeatNum(Integer.toString(i));
    //         seat.setIsAvailable(true);
    //         seat.setBaggage(true);
    //         seat.setSeatclass(2);
    //         seats.add(seat);
    //     }for (int i = 1; i <= business; i++) {
    //         Seat seat = new Seat();
    //         seat.setSeatNum(Integer.toString(i));
    //         seat.setIsAvailable(true);
    //         seat.setBaggage(true);
    //         seat.setSeatclass(1);
    //         seats.add(seat);
    //     }
    // }
}