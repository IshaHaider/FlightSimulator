package src.Domain;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
        validateDate(this);
    }

    public final String getDate() {
        return this.day + "-" + this.month + "-" + this.year;
    }

    public void setDate(final Date date) {
        validateDate(date);
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }


    void validateDate(Date date) {
        if (date.day < 1 || date.day > 31 || date.month < 1 || date.month > 12 || date.year < 2015 || date.year > 2030) {
            throw new IllegalArgumentException("Date must be between 01-01-1900 and 31-12-2030");
        }
    }
}