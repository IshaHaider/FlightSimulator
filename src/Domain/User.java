package src.Domain;
import java.util.ArrayList;

public abstract class User {
    protected int userID;
    protected int accessLevel;
    protected Name name;
    protected Address address;
    protected String email;
    protected Date birthDate;
    protected String phoneNumber;
     
    
    
     
    
    public abstract ArrayList<Flight> getFlights(final String destination);
    public abstract void manageReservation(final Flight flight);   
}