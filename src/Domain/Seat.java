package src.Domain;

public class Seat {
    private int seatID;
    private int aircraftID;
    private String seatName;
    private String seatClass;
    private double cost;
    private boolean baggage;
    private boolean available;
    
    public Seat() {
        this.seatID = 0;
        this.aircraft = 0;
        this.seatName = "";
        this.seatClass = "2";
        this.cost = 0.0;
        this.baggage = false;
        this.available = false;
    }
   
    public Seat(int seatId, int aircraftID, String seatName, String seatClass, double cost, boolean baggage, boolean available) {
        this.seatID = seatId;
        this.aircraftID = aircraftID;
        this.seatName = seatName;
        this.seatClass = seatClass;
        this.cost = cost;
        this.baggage = baggage;
        this.available = available;
    }

    public final int getSeatID() {return seatID;}
    public final int getAircraftID() {return aircraftID;}
    public final String getSeatName() {return seatName;}
    public final String getSeatClass() {return seatClass;}
    public final double getCost() {return cost;}
    public final boolean getBaggage() {return baggage;}
    public final boolean getAvailable() {return available;}

    public void setSeatID(final int seatID) {this.seatID = seatID;}
    public void setAircraftID(final int aircraftID) {this.aircraftID = aircraftID;}
    public void setSeatName(final String seatName) {this.seatName = seatName;}
    public void setSeatClass(final String seatClass) {this.seatClass = seatClass;}
    public void setCost(final double cost) {this.cost = cost;}
    public void setBaggage(final boolean baggage) {this.baggage = baggage;}
    public void setAvailable(final boolean available) {this.available = available;}
}