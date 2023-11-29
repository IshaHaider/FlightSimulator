package src.Domain;

import java.util.ArrayList;
 
public class Crew extends User{
    ArrayList<Flight> assignedFlights;
    private String password;  

    public Crew() {
        this.accessLevel = 3;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";  
        this.password = "";  
        this.birthDate = new DDate(); 
        this.phoneNumber = ""; 
        this.assignedFlights = new ArrayList<>();
    }

    public Crew (Name name, Address address, String email, String password, DDate birthDate, String phoneNumber){
        this.accessLevel = 3;
        this.name = name;     
        this.address = address;      
        this.email = email;    
        this.password = password; 
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }
    

    public Crew (Name name, Address address, String email, String password, DDate birthDate, String phoneNumber, ArrayList<Flight> assignedFlight){
        this.accessLevel = 3;
        this.name = name;     
        this.address = address;      
        this.email = email; 
        this.password = password;      
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
        this.assignedFlights = assignedFlight;
    }

    public String getPassword() {return password;}
    public ArrayList<Flight> getAssignedFlights() {return assignedFlights;}

    public void setPassword(final String password) {this.password = password;}
    public void setAssignedFlights(final ArrayList<Flight> assignedFlights) {this.assignedFlights = assignedFlights;}

    @Override
    public ArrayList<Flight> getFlights(final String destination) {return new ArrayList<Flight>(); };
    @Override
    public void manageReservation(final Flight flight) {};  

}