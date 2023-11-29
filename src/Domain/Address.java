package src.Domain;

public class Address {
    private String number;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    
    public Address() {
        this.number = "";
        this.street = "";
        this.city = "";
        this.postalCode = "";
        this.country = "";
    }
    public Address(String number, String street) {
        this.number = number;
        this.street = street;
        this.city = "";
        this.postalCode = "";
        this.country = "";
    }


    public Address(String number, String street, String city, String postalCode, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public final String getNumber() {return number;}
    public final String getStreet() {return street;}
    public final String getCity() {return city;}
    public final String getCountry() {return country;}
    public final String getPostalCode() {return postalCode;}    

    public void setNumber(final String number) {this.number = number;}
    public void setStreet(final String street) {this.street = street;}
    public void setCity(final String city) {this.city = city;}
    public void setCountry(final String country) {this.country = country;}
    public void setPostalCode(final String postalCode) {this.postalCode = postalCode;}

    void validateAddress(Address address) {
        
    }
}