package Domain;

public interface User {
    String firstName = ""; // Initialize the firstName field
    String lastName = ""; // Initialize the lastName field
    Email email = new Email(); // Initialize the email field
    Address address = new Address(); // Initialize the address field
    Date dateOfBirth = new Date(); // Initialize the dateOfBirth field
    String phoneNumber = ""; // Initialize the phoneNumber field
}
