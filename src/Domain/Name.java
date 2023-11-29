package src.Domain;

public class Name {
    private String firstName;
    private String lastName;

    public Name(){
        firstName = "";
        lastName = "";
    }

    public Name(String fn, String ln){
        firstName = fn;
        lastName = ln;
    }

    public final String getFirstName(){return firstName;}
    public final String getLastName(){return lastName;}

    public void setFirstName(final String firstName){this.firstName = firstName;}
    public void setLastName(final String lastName){this.lastName = lastName;}

    void validateName(Name name) {}
}
