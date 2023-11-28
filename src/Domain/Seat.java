package src.Domain;

public class Seat {
    private int seatclass;
    private boolean baggage;
    private boolean isAvailable;
    private String seatNum;

    public Seat() {
        this.seatclass = 2;
        this.baggage = false;
        this.seatNum = "";
        this.isAvailable = true;
    }

    public Seat(int seatclass, boolean baggage, String seatNum, boolean isAvailable) {
        this.seatclass = seatclass;
        this.baggage = baggage;
        this.seatNum = seatNum;
        this.isAvailable = isAvailable;
    }

    public final int getSeatclass() {return seatclass;}
    public final boolean getBaggage() {return baggage;}
    public final String getSeatNum() {return seatNum;}
    public final boolean getIsAvailable() {return isAvailable;}

    public void setSeatclass(final int seatclass) {this.seatclass = seatclass;}
    public void setBaggage(final boolean baggage) {this.baggage = baggage;}
    public void setSeatNum(final String seatNum) {this.seatNum = seatNum;}
    public void setIsAvailable(final boolean isAvailable) {this.isAvailable = isAvailable;}
}