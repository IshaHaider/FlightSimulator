package src.Domain;

public abstract class User {
    protected Name name;
    protected Date dateOfBirth;
    protected Address address;
    protected int phoneNumber;
    protected Email email;
    protected String password;    
    protected int accessLevel;
    protected int userID;

    public abstract ArrayList<Flight> viewFlights(final String destination);
    public abstract void viewFlightInformation(final Flight flight);
    public abstract void manageReservation(final Flight flight);   
}