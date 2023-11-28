package Domain;

public interface User {
    
    public abstract void viewFlight();
    public abstract void viewFlightInformation(Flight flight);
    public abstract void manageReservation(Flight flight);

    
}