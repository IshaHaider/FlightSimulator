package src.Domain;

import java.util.ArrayList;
import java.util.List;

public class AirPlane {
    private int aircraftID;
    private String aircraftName;

    public AirPlane() {
        this.aircraftID = 0;
        this.aircraftName = "";
    }
    
    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the aircraftID is auto-increment)
    public AirPlane(String name) {
        this.aircraftID = 0;
        this.aircraftName = name;
    }

    // USE THIS FOR LOCAL STORAGE (to manually add aircraftID)
    public AirPlane(int airID, String name) {
        this.aircraftID = airID;
        this.aircraftName = name;
    }

    public final int getAircraftID() {return aircraftID;}
    public final String getAircraftName() {return aircraftName;}
    public void setAircraftID(final int aircraftID) {this.aircraftID = aircraftID;}
    public void setAircraftName(final String aircraftName) {this.aircraftName = aircraftName;}
}