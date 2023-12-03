package src.Domain;

public class Ticket{
    private int ticketNumber;
    private int flightID;
    private int aircraftID;
    private int userID;
    private int seatID;

    public Ticket() {
        this.ticketNumber = 0;
        this.aircraftID = 0;
        this.flightID = 0;
        this.userID = 0;
        this.seatID = 0;
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the ticketNumber is auto-increment)
    public Ticket( int aircraftID, int flightID, int userID, int seatID) {
        this.ticketNumber = 0;
        this.flightID = flightID;
        this.aircraftID = aircraftID;
        this.userID = userID;
        this.seatID = seatID;
    }

    // USE THIS FOR LOCAL STORAGE (to manually add ticketNumber)
    public Ticket(int ticketNumber, int aircraftID, int flightID, int userID, int seatID) {
        this.ticketNumber = ticketNumber;
        this.flightID = flightID;
        this.aircraftID = aircraftID;
        this.userID = userID;
        this.seatID = seatID;
    }

    /* SETTERS AND GETTERS */
    public final int getTicketNumber() {return ticketNumber;}
    public final int getFlightID() {return flightID;}
    public final int getAircraftID() {return aircraftID;}
    public final int getUserID() {return userID;}
    public final int getSeatID() {return seatID;}

    public final void setTicketNumber( int ticketNumber) {this.ticketNumber = ticketNumber;}
    public final void setFlightID( int flightID) {this.flightID = flightID;}
    public final void setAircraftID( int aircraftID) {this.aircraftID = aircraftID;}
    public final void setUserID( int userID) {this.userID = userID;}
    public final void setSeatID( int seatID) {this.seatID = seatID;}
}
