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
        this.birthDate = new DDate(); 
        this.phoneNumber = "";  
        this.balance = 0.0f;
    }

    public RegisteredUser (int promotionID, Name name, Address address, String email, String password, DDate birthDate, String phoneNumber, float balance)  {
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

    
    public final int getPromotionID() {return promotionID;}
    public final String getPassword() {return password;}
    public final float getBalance() {return balance;}

    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setPassword(final String password) {this.password = password;}
    public void setBalance(final float balance) {this.balance = balance;}
    
    @Override
    public ArrayList<Flight> getFlights(final String destination) {return new ArrayList<Flight>(); };
    @Override
    public void manageReservation(final Flight flight) {};  


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