package src.Domain;

public class Email {
    private String name;
    private String domain;
    private String tld;

    public Email(String name, String domain, String tld) {
        this.name = name;
        this.domain = domain;
        this.tld = tld;
        validateEmail(this);
    }

    public final String getEmail() {
        return this.name + "@" + this.domain + "." + this.tld;
    }

    public void setEmail(final Email email) {
        validateEmail(email);
        this.name = email.name;
        this.domain = email.domain;
        this.tld = email.tld;
    }

    void validateEmail(Email email) {
        if (email.name == null || email.domain == null || email.tld == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (email.domain.matches(".*\\d.*") || email.tld.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Email domain/tld cannot contain numbers or special characters");
        }
    }
}