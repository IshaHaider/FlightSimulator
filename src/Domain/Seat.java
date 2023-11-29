package src.Domain;

public class Seat {
    private int seatID;
    private int aircraftID;
    private String seatName;
    private AirplaneClass seatClass;
    private float cost;
    private boolean baggage;
    private boolean available;
    
    public Seat() {
        this.seatID = 0;
        this.aircraftID = 0;
        this.seatName = "";
        this.seatClass = AirplaneClass.Economy;
        this.cost = 0.0f;
        this.baggage = false;
        this.available = false;
    }
   
    public Seat(int seatID, int aircraftID, String seatName, AirplaneClass seatClass, float cost, boolean baggage, boolean available) {
        this.seatID = seatID;
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
    public final AirplaneClass getSeatClass() {return seatClass;}
    public final float getCost() {return cost;}
    public final boolean getBaggage() {return baggage;}
    public final boolean getAvailable() {return available;}

    public void setSeatID(final int seatID) {this.seatID = seatID;}
    public void setAircraftID(final int aircraftID) {this.aircraftID = aircraftID;}
    public void setSeatName(final String seatName) {this.seatName = seatName;}
    public void setSeatClass(final AirplaneClass seatClass) {this.seatClass = seatClass;}
    public void setCost(final float cost) {this.cost = cost;}
    public void setBaggage(final boolean baggage) {this.baggage = baggage;}
    public void setAvailable(final boolean available) {this.available = available;}
}