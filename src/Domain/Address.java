package src.Domain;

public class Address {
    private String number;
    private String street;
    
    public Address() {
        this.number = "";
        this.street = "";
    }
    public Address(String number, String street) {
        this.number = number;
        this.street = street;
    }

    public Address(String address) {
        String[] parts = address.split("\\s+");
        
        if (parts.length >= 1) {
            this.number = parts[0];
        }if (parts.length >= 2) {
            this.street = parts[1];
        }
    }

    public final String getNumber() {return number;}
    public final String getStreet() {return street;}
    public void setNumber(final String number) {this.number = number;}
    public void setStreet(final String street) {this.street = street;}

}