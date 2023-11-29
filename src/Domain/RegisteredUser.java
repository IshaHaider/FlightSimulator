package src.Domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import src.Controllers.DBController;

public class RegisteredUser extends User{
    private int promotionID;
    private String password;  
    private float balance; 

    public RegisteredUser() {
        this.accessLevel = 2;
        this.promotionID = 0;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";       
        this.password = "";   
        this.birthDate = new Date(); 
        this.phoneNumber = "";  
        this.balance = 0.0f;
    }

    public RegisteredUser (Name name, Address address, String email, Date birthDate, String phoneNumber, String password, float balance, int promotionID)  {
        this.accessLevel = 2;
        this.promotionID = promotionID;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.password = password;   
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;  
        this.balance = balance;
    }

    public final int getUserID() {return userID;}
    public final int getAccessLevel() {return accessLevel;}
    public final int getPromotionID() {return promotionID;}
    public final Name getName() {return name;}
    public final Address getAddress() {return address;}
    public final String getEmail() {return email;}
    public final String getPassword() {return password;}
    public final Date getBirthDate() {return birthDate;}
    public final String getPhoneNumber() {return phoneNumber;}
    public final float getBalance() {return balance;}

    public void setUserID(final int userID) {this.userID = userID;}
    public void setAccessLevel(final int accessLevel) {this.accessLevel = accessLevel;}
    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setName(final Name name) {this.name = name;}
    public void setAddress(final Address address) {this.address = address;}
    public void setEmail(final String email) {this.email = email;}
    public void setPassword(final String password) {this.password = password;}
    public void setBirthDate(final Date birthDate) {this.birthDate = birthDate;}
    public void setPhoneNumber(final String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setBalance(final float balance) {this.balance = balance;}
    
    

//     // public final float getDiscountPercentage() {return discountPercentage;}
//     public final ArrayList<Flight> getRegisteredFlights() {return registeredFlights;}
//     public final float getBalance() {return balance;}

//     public void setRegisteredFlights(final ArrayList<Flight> registeredFlights) {this.registeredFlights = registeredFlights;}
//     public void setBalance(final float balance) {this.balance = balance;}

//     void reserveFlight(final Flight flight, final Seat seat) {
//         assignSeat(RegisteredUser.getUserID(), seat);
//         registeredFlights.add(flight);
//     }

//     public ArrayList<Flight> viewReservations() throws SQLException {
//         ArrayList<Flight> flights = new ArrayList<>();
//         String sql = "SELECT * FROM Flights WHERE flightId IN (SELECT flightId FROM Reservations WHERE userId = ?)";
//         try (Connection conn = dbController.getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql)) {
//             pstmt.setInt(1, this.userId);
//             try (ResultSet rs = pstmt.executeQuery()) {
//                 while (rs.next()) {
//                     Flight flight = new Flight(rs);
//                     flights.add(flight);
//                 }
//             }
//         }
//         return flights;
//     }
 
//     public void receivePromotion() throws SQLException {
//         String sql = "SELECT * FROM Promotions WHERE startDate <= CURRENT_DATE() AND endDate >= CURRENT_DATE()";
//         try (Connection conn = dbController.getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql);
//              ResultSet rs = pstmt.executeQuery()) {

//             double maxDiscount = 0.0;
//             while (rs.next()) {
//                 String discountString = rs.getString("discount"); // e.g., "10%"
//                 double discount = Double.parseDouble(discountString.replace("%", ""));
//                 if (discount > maxDiscount) {
//                     maxDiscount = discount;
//                 }
//             }
//             this.discountPercentage = maxDiscount;
//         }
//     }

//     public double applyDiscountToSeatPrice(double seatPrice) {
//         if (this.discountPercentage > 0) {
//             double discountAmount = seatPrice * this.discountPercentage / 100.0;
//             return seatPrice - discountAmount;
//         }
//         return seatPrice;
//     }

//     @Override
//     public void manageReservation(Flight flight, Seat seat){
//         if(flight.getSeat(RegisteredUser.getUserID())){
//             assignSeat(null, flight.getSeat(this.userId) , flight); // unassign the seat
//             flight.getSeat().setavailability(true); // change the seat to available
//             assignSeat(RegisteredUser.getUserID(), seat, flight); // assign the seat

//             // update the arraylist of registered flights
//             for(int i = 0; i < registeredFlights.size(); i++){
//                 if(registeredFlights.get(i).getFlightNumber() == flight.getFlightNumber()){
//                     registeredFlights.set(i, flight);
//                 }
//             }
//         }
//     }

//     @Override
//    public void update(Observable o, Object arg){
//         if(o instanceof Flight){
//             Flight updatedFlight = (Flight) o;
//         }
//     }

}