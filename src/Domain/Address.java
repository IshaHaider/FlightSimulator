package Domain;

public class Address {
    private String street;
    private String city;
    private String Country;
    private String zipCode;

    public void setStreet (final String s)  { street = s; }
    public void setCity (final String c)  { city = c; }
    public void setCountry (final String c)  { Country = c; }
    public void setZipCode (final String z)  { zipCode = z; }
    public final String getStreet ()  { return street; }
    public final String getCity ()  { return city; }
    public final String getCountry ()  { return Country; }
    public final String getZipCode ()  { return zipCode; }
}
